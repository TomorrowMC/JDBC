import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Yifei.Hu
 * @create 2021-11--21:15
 */
public class JDBCUtils {
   private static ComboPooledDataSource cpds = new ComboPooledDataSource("c3p0-config");//一定要先弄好xml文件,不然报错的
    public static BasicDataSource dataSource = null;
    static {
        //新建配置文件
        Properties properties = new Properties();
        //创建流,使用Properties读取配置文件
        FileInputStream is= null;
        try {
            is = new FileInputStream("pro");
            properties.load(is);
            //新建BasicDataSourceFactory,传入Properties
             dataSource = BasicDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
public static Connection getC3P0SQL() throws SQLException {
    //获取连接
    Connection connection = cpds.getConnection();
    return connection;
}
public static Connection getDBCPSQL() throws Exception {
    //获取连接
    Connection conn = dataSource.getConnection();

    return conn;
    }


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

}
