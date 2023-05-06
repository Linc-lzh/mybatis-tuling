package cn.tulingxueyuan.tests;

import cn.tulingxueyuan.mapper.EmpMapper;
import cn.tulingxueyuan.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class MyBatisTest {

    SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() {

        // 根据全局配置文件的xml构建成SqlSessionFactory
        String config = "mybatis-config.xml";
        // 将xml构建成输入流
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //构建SqlSessionFactory :将全局配置文件和所有的mapper全部加载到Configuration
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    @Test
    public void test01() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            String databaseProductName = sqlSession.getConnection().getMetaData().getDatabaseProductName();
            System.out.println(databaseProductName);

            // Mybatis在getMapper就会给我们创建jdk动态代理
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            System.out.println(mapper.SelectEmp(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
