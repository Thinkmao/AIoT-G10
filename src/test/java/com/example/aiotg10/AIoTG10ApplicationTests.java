package com.example.aiotg10;

import com.example.aiotg10.mapper.UserMapper;
import com.example.aiotg10.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class AIoTG10ApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }
    @Test
    void dataSource() throws SQLException{
        System.out.println(dataSource.getConnection());
    }
    @Test
    void testGetAll(){
        List<User> list = userMapper.getAll();
        System.out.println(list);
    }

}
