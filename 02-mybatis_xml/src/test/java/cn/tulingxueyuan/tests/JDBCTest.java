package cn.tulingxueyuan.tests;

import cn.tulingxueyuan.pojo.Emp;
import org.junit.Test;

import java.sql.*;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class JDBCTest {

    @Test
    public void testjdbc() throws ClassNotFoundException, SQLException {
        // 1.注册驱动
        Class class1 = Class.forName("com.mysql.jdbc.Driver");
        // 2.创建数据连接 DriverManager.getConnection()方法：获取数据库连接
        String url = "jdbc:mysql://localhost:3306/mybatis";
        String user = "root";
        String password = "123456";
        //3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "SELECT * FROM EMP WHERE id=?";
        //4.使用PreparedStatement 预解析sql语句，
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1,4);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Emp emp = new Emp();
            emp.setId(rs.getInt("id"));
            emp.setUsername(rs.getString("username"));
            System.out.println(emp);
        }
    }
}
