package com.oneler;

import com.oneler.entities.n21.Customer;
import com.oneler.entities.n21.Order;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class SpringwebApplicationTests {


    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private JdbcTemplate jdbcTemplate;

    Session session = null;


    @Before
    public void  init() {
        session = entityManager.unwrap(Session.class);
    }

    @Test
    public void test() {
        System.out.println(session);
    }


    @Test
    public void testJdbcSave() {
        List<Map<String, Object>> maps = this.jdbcTemplate.queryForList("SELECT *FROM customer");


        long start = System.currentTimeMillis();
        for (int i = 0; i <1000 ; i++) {
            Customer c = new Customer();
            jdbcTemplate.update("INSERT INTO customer (customer_name,cutomer_coler) VALUES (?,?)", "name","2313");
        }



        System.out.println((System.currentTimeMillis()-start)/1000);


        System.out.println(maps);

    }

    @Test
    public void testDelete(){
        //在不设定级联关系的情况下, 且 1 这一端的对象有 n 的对象在引用, 不能直接删除 1 这一端的对象
        Customer customer = (Customer) session.get(Customer.class, 1);
        session.delete(customer);
    }

    @Test
    public void testUpdate(){
        Order order = (Order) session.get(Order.class, 1);
        order.getCustomer().setCustomerName("AAA");
    }

    @Test
    public void testMany2OneGet(){
        //1. 若查询多的一端的一个对象, 则默认情况下, 只查询了多的一端的对象. 而没有查询关联的
        //1 的那一端的对象!
        Order order = (Order) session.get(Order.class, 1);
        System.out.println(order.getOrderName());

        System.out.println(order.getCustomer().getClass().getName());

        //session.close();

        //2. 在需要使用到关联的对象时, 才发送对应的 SQL 语句.
        Customer customer = order.getCustomer();
        System.out.println(customer.getCustomerName());

        //3. 在查询 Customer 对象时, 由多的一端导航到 1 的一端时,
        //若此时 session 已被关闭, 则默认情况下
        //会发生 LazyInitializationException 异常

        //4. 获取 Order 对象时, 默认情况下, 其关联的 Customer 对象是一个代理对象!

    }

    @Test
    public void testMany2OneSave(){
        Customer customer = new Customer();
        customer.setCustomerName("BB");

        Order order1 = new Order();
        order1.setOrderName("ORDER-3");

        //执行  save 操作: 先插入 Customer, 再插入 Order, 3 条 INSERT
        //先插入 1 的一端, 再插入 n 的一端, 只有 INSERT 语句.
//		session.save(customer);
//
//		session.save(order1);
//		session.save(order2);

        //先插入 Order, 再插入 Customer. 3 条 INSERT, 2 条 UPDATE
        //先插入 n 的一端, 再插入 1 的一端, 会多出 UPDATE 语句!
        //因为在插入多的一端时, 无法确定 1 的一端的外键值. 所以只能等 1 的一端插入后, 再额外发送 UPDATE 语句.

        //推荐先插入 1 的一端, 后插入 n 的一端

        order1.setCustomer(customer );
        session.save(order1);

        session.save(customer);

    }
}
