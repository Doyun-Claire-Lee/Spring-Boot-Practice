package me.doyun.springbootjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class H2Runner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Connection connection = dataSource.getConnection();

        //접속할 DB의 정보 확인 방법
        System.out.println(connection.getMetaData().getURL());
        System.out.println(connection.getMetaData().getUserName());

        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE USER (ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
        statement.executeUpdate(sql);

//        jdbcTemplate.excute("INSERT INTO USER VALUES (1, ‘keesun’)");

        connection.close();

    }
}
