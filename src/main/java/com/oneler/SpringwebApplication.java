package com.oneler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringwebApplication  {

    /*	@Bean
        public Object testBean(PlatformTransactionManager platformTransactionManager){
            System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
            return new Object();
        }*/
    public static void main(String[] args) {
        SpringApplication.run(SpringwebApplication.class, args);
    }


}
