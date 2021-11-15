package day1112;

import com.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author Yifei.Hu
 * @create 2021-11--17:12
 */
public class Transaction {
    @Test
    public void transactionTest() throws Exception {
        //获取c3p0连接池
        ComboPooledDataSource cpds = new ComboPooledDataSource("c3p0-config");

        //获取连接
        Connection connection = cpds.getConnection();
        System.out.println(connection);

    }
}
