package DBCPTest;



import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author Yifei.Hu
 * @create 2021-11--09:53
 */
public class DBCPTest {

    public void test() throws Exception {

    }

    public static void main(String[] args) throws Exception {
        //新建配置文件
        Properties properties = new Properties();
        //创建流,使用Properties读取配置文件
        FileInputStream is= new FileInputStream("/Users/yifei.hu/NoUpdate/Java/JDBC/day1106/src/DBCPTest/DBCP.properties");
        properties.load(is);
        //新建BasicDataSourceFactory,传入Properties
        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
        //获取连接
        Connection conn = dataSource.getConnection();

        System.out.println(conn);
    }
}
