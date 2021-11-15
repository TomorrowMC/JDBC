package com;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Yifei.Hu
 * @create 2021-11--11:38
 */
public class blobTest {
    @Test
    public void blobTest1() throws Exception {
        Connection connection=JDBCUtils.connectSQL();
        String sql= "insert into customers(name,email,birth,photo)values(?,?,?,?)";
        PreparedStatement prepareStatement=connection.prepareStatement(sql);
        prepareStatement.setObject(1, "胡逸飞121");
        prepareStatement.setObject(2, "huyifei0205");
        prepareStatement.setObject(3, "2002-02-06");
        FileInputStream fis=new FileInputStream("d.jpg");
        prepareStatement.setObject(4,fis);
        prepareStatement.execute();
        JDBCUtils.closeSQl(connection, prepareStatement);
        fis.close();
    }
    @Test
    public void blobTest2()  {
        Connection connection = null;
        PreparedStatement prepareStatement=null;
        ResultSet resultSet = null;
        InputStream fis = null;
        FileOutputStream fos=null;
        try {
            connection = JDBCUtils.connectSQL();
            String sql = "select photo from test.customers where id=? ";
            prepareStatement = connection.prepareStatement(sql);
prepareStatement.setInt(1, 25);
            resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                Blob blob= resultSet.getBlob("photo");
                 fis = blob.getBinaryStream();
                 fos=new FileOutputStream("x.jpg");
                byte[] buffer = new byte[1024];
                int len;
                while ((len=fis.read(buffer))!=-1) {
                    fos.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JDBCUtils.closeSQlQuery(connection, prepareStatement, resultSet);
        }


    }
}
