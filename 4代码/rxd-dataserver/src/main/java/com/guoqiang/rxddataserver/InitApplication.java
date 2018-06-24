package com.guoqiang.rxddataserver;

import com.guoqiang.rxddataserver.entity.rxd.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.Reader;


@SpringBootApplication
public class InitApplication {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        //SpringApplication.run(InitApplication.class, args);
        testMybatis();
    }

    private static void testMybatis() {
        String resource = "mybatis-config.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession session = sqlSessionFactory.openSession();
            User user = session.selectOne("findById", "1");
            session.commit();
            System.out.println(user.getName());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


