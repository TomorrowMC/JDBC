package com;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Yifei.Hu
 * @create 2021-11--10:27
 */
public class exer1_insert {
    @Test
    public void insertTest() throws Exception {
        Connection conn= JDBCUtils.connectSQL();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入姓名");
        String name= scanner.nextLine();
        System.out.print("请输入email");
        String email= scanner.nextLine();
        System.out.print("请输入生日eg:20020205");
        String birth= scanner.nextLine();
        String sql="insert into customers(name,email,birth) values(?,?,?) ";
        PreparedStatement prepareStatement= conn.prepareStatement(sql);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
        Date date = sdf.parse(birth);
        java.sql.Date date1 = new java.sql.Date(date.getTime());
prepareStatement.setString(1,name);
prepareStatement.setString(2,birth);
prepareStatement.setDate(3,date1);
        int i = prepareStatement.executeUpdate();
        System.out.println(i);


    }

}
