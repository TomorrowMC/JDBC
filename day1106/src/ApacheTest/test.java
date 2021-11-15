package ApacheTest;

import com.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @author Yifei.Hu
 * @create 2021-11--11:03
 */
public class test {
    @Test
    public void test() throws Exception {
        //新建QueryRunner 对象
        QueryRunner queryRunner = new QueryRunner();
        //获取连接，新建sql
        Connection connection= JDBCUtils.connectSQL();
        String sql = "";
        //新建 rsh：ResultSetHandler。带泛型的
        BeanListHandler<Cusomer> handler = new BeanListHandler<>(Cusomer.class);
        //调用queryRunner.query(conn,sql,rsh,para)
        List<Cusomer> cusomer=  queryRunner.query(connection,sql,handler,23);

    }
}
