import jdk.xml.internal.JdkXmlUtils;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.SimpleFormatter;

/**
 * @author Yifei.Hu
 * @create 2021-11--20:30
 */
public class PrepateStatement {
    private Date parse;

    @Test
    public void addTest() throws Exception{
//        InputStream in=PrepateStatement.class.getClassLoader().getResourceAsStream("jdbc.properties");
//        Properties properties = new Properties();
//        properties.load(in);
//        String url= properties.getProperty("url");
//        String user= properties.getProperty("user");
//        String password= properties.getProperty("password");
//        String driver = properties.getProperty("driverClass");
//        Class.forName(driver);
//        Connection conn = DriverManager.getConnection(url,user,password);
        Connection conn = JDBCUtils.connectSQL();
        //新建sql语句,需要填充的地方使用占位符
        String sql="insert into customers(name,email,birth) values(?,?,?)";
        //通过连接获取PreparedStatement对象
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //填充占位符,(index,内容)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String birth= "2002-11-05";
        Date parse = simpleDateFormat.parse(birth);
        preparedStatement.setString(1,"万宇昕的儿子hyfsss");
        preparedStatement.setString(2,"yifei.hu20.cn");
        preparedStatement.setDate(3, java.sql.Date.valueOf(birth));
        //执行
        preparedStatement.execute();
        //关闭流,注意trycatch
        JDBCUtils.closeSQl(conn,preparedStatement);
    }
}
