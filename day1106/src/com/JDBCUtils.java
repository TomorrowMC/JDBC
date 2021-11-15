package com;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author Yifei.Hu
 * @create 2021-11--21:15
 */
public class JDBCUtils {

    public static Connection connectSQL() throws Exception {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String drive = properties.getProperty("driverClass");
        try {
            Class.forName(drive);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn == null) {
            throw new Exception("配置文件出错");
        }
        System.out.println("启动成功");
        return conn;
    }



    public static void closeSQl(Connection conn, PreparedStatement stmt)  {
        try {
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("小宝贝关闭成功");
    }
    public static void closeSQlQuery(Connection conn, PreparedStatement stmt, ResultSet rs)  {
        try {
            if (conn != null) {
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("小宝贝关闭成功");
    }

}
