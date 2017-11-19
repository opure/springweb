package com.oneler;

import com.oneler.domain.Book;
import com.oneler.domain.Person;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class SpringwebApplicationTests {


    @PersistenceContext
    private EntityManager entityManager;

    private Session session;

    @Before
    public void before() {
        session = entityManager.unwrap(Session.class);
    }

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            Person person = new Person();
            person.setName("wangmaiz" + i);
            person.setAge(i);
            Book b = new Book();
            b.setName("11");
            b.setPrice("12");
            person.setBook(b);
            b.setPerson(person);
            session.save(person);
        }
    }

    @Test
    public void testDelte() {

        session.delete(session.get(Person.class,5));
    }
}
