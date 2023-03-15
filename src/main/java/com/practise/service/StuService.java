package com.practise.service;

//import com.practise.entity.Student;
//import com.practise.repositoty.StuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Description
 * @Author dan.he
 * @Date 2022/8/20 22:57
 **/

@Service
public class StuService {
//    @Autowired
//    private StuRepository stuRepository;

    @Autowired
    @Qualifier("ds1DataSource")
    private DataSource ds1DataSource;

    public void saveStu() throws SQLException {
//        Student student = new Student();
//        student.setId(3L);
//        student.setAge(10);
//        student.setName("{"dds"}");
//        stuRepository.save(student);

        Connection connection = ds1DataSource.getConnection();
        String querySql = "select *from student where id =1";
        ResultSet resultSet =connection.prepareStatement(querySql).executeQuery();

        String str ="";
        while (resultSet.next()){
            str = resultSet.getString("name");
        }

        String insertSql = "insert into student(id,name,age) value(?,?,?)";

        PreparedStatement pr =connection.prepareStatement(insertSql);
        pr.setLong(1,3L);
        pr.setString(2,str);
        pr.setLong(3,10L);

        pr.executeUpdate();
    }
}
