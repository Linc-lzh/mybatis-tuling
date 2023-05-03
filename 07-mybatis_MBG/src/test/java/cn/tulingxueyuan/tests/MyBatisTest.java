package cn.tulingxueyuan.tests;

import cn.tulingxueyuan.mapper.EmpMapper;
import cn.tulingxueyuan.pojo.Emp;
import cn.tulingxueyuan.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Mybatis3Simple生成调用
     */
    @Test
    public void test01() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // Mybatis在getMapper就会给我们创建jdk动态代理
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

            Emp emp = mapper.selectByPrimaryKey(4);
            System.out.println(emp);
        }
    }



    /**
     * Mybatis3生成调用方式
     */
    @Test
    public void test02() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // Mybatis在getMapper就会给我们创建jdk动态代理
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

            // 使用Example实现动态条件语句
            EmpExample empExample=new EmpExample();
            EmpExample.Criteria criteria = empExample.createCriteria();
            criteria.andUserNameLike("%帅%")
                    .andIdEqualTo(4);

            List<Emp> emps = mapper.selectByExample(empExample);
            System.out.println(emps);
        }
    }



}
