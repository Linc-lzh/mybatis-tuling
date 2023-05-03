package cn.tulingxueyuan.tests;

import cn.tulingxueyuan.mapper.DeptMapper;
import cn.tulingxueyuan.mapper.EmpMapper;
import cn.tulingxueyuan.pojo.Dept;
import cn.tulingxueyuan.pojo.Emp;
import cn.tulingxueyuan.pojo.QueryEmpDTO;
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

    /**
     * 徐庶老师实际开发中的实现方式
     */
    @Test
    public void test01() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            // Mybatis在getMapper就会给我们创建jdk动态代理
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            QueryEmpDTO dto = mapper.QueryEmp(4);
            System.out.println(dto);
        }
    }

    /**
     * 实用嵌套结果实现联合查询  多对一
     */
    @Test
    public void test02() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            // Mybatis在getMapper就会给我们创建jdk动态代理
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            Emp emp = mapper.QueryEmp2(4);
            System.out.println(emp);
        }
    }

    /**
     * 实用嵌套查询实现分步查询 多对一
     *
     * 懒加载
     */
    @Test
    public void test03() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            // Mybatis在getMapper就会给我们创建jdk动态代理
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            Emp emp = mapper.QueryEmp3(4);
           // System.out.println(emp.getDept().getDeptName());
            //System.out.println(emp.getUsername());
        }
    }

    /**
     * 嵌套查询： 一对多   使用部门id查询员工
     */
    @Test
    public void test04() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            // Mybatis在getMapper就会给我们创建jdk动态代理
            DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
            Dept dept = mapper.SelectDeptAndEmps(2);
            System.out.println(dept);
        }
    }


    /**
     *  嵌套查询（异步查询）： 一对多  查询部门及所有员工
     */
    @Test
    public void test05() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            // Mybatis在getMapper就会给我们创建jdk动态代理
            DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
            Dept dept = mapper.SelectDeptAndEmps2(2);

            System.out.println(dept);
            System.out.println(dept.getEmps());
        }
    }
}
