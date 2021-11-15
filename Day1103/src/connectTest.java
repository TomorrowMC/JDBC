import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @author Yifei.Hu
 * @create 2021-11--10:07
 */
public class connectTest {


    @Test
    public void testConnection() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();//新建驱动对象
        String url = "jdbc:mysql://localhost:3306/test";//主机地址
        Properties properties = new Properties();//密码和用户名
        properties.setProperty("user", "root");//设置密码和用户名
        properties.setProperty("password", "ham12lyl");
        Connection c = driver.connect(url, properties);//开启连接
        System.out.println(c);


    }

    @Test
    public void reflTest() throws Exception {
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        String url = "jdbc:mysql://localhost:3306/test";//主机地址
        Properties properties = new Properties();//密码和用户名
        properties.setProperty("user", "root");//设置密码和用户名
        properties.setProperty("password", "ham12lyl");
        Connection c = driver.connect(url, properties);//开启连接
        System.out.println(c);

    }

    @Test
    public void dmTest() throws Exception {
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "ham12lyl";
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

    }

    @Test
    public void dmTest2() throws Exception {
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");//可省略
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "ham12lyl";
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

    }

    @Test
    public void connTest5() throws Exception {


    }

    public static void main(String[] args) throws Exception {
        InputStream in = connectTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
//        FileInputStream in = new FileInputStream("src/jdbc.properties");
        Properties properties = new Properties();
        properties.load(in);
        String url = properties.getProperty("url");
        String password = properties.getProperty("password");
        String user = properties.getProperty("user");
        String drive = properties.getProperty("driverClass");
        Class.forName(drive);
        Connection c = DriverManager.getConnection(url, user, password);
        System.out.println(c);
    }

}
