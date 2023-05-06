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

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class MyBatisTest {

    SqlSessionFactory sqlSessionFactory;
    @Before
    public void before(){

        // 根据全局配置文件的xml构建成SqlSessionFactory
        String config= "mybatis-config.xml";
        // 将xml构建成输入流
        InputStream inputStream= null;
        try {
            inputStream = Resources.getResourceAsStream(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //构建SqlSessionFactory :将全局配置文件和所有的mapper全部加载到Configuration
        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);

    }

    @Test
    public void test01() {
        // SqlSession负责执行具体的数据库操作
        /**
         * 给openSession设置不同的参数会给SqlSession后续的数据库操作造成不同影响
         * boolean autoCommit  ： 可以设置事务为自动提交（因为在执行增删改的时候需要手动提交事务）
         * Connection connection 将由当前环境配置的 DataSource 实例中获取 Connection 对象。
         * TransactionIsolationLevel level 事务隔离级别将会使用驱动或数据源的默认设置。
         * ExecutorType execType 预处理语句是否复用，是否批量处理更新。
         */
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            String databaseProductName = sqlSession.getConnection().getMetaData().getDatabaseProductName();
            System.out.println(databaseProductName);

            // Mybatis在getMapper就会给我们创建jdk动态代理
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            System.out.println(mapper.getClass());
            Emp emp = mapper.SelectEmp(4);
            System.out.println(emp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Logger LOGGER= LoggerFactory.getLogger(this.getClass());
    /**
     * 日志级别
     * TRACE < DEBUG < INFO < WARN < ERROR。
     * 1        2       3      4       5
     */
    @Test
    public  void test02(){
        LOGGER.trace("跟踪级别");
        LOGGER.debug("调试级别");
        LOGGER.info("信息级别");
        LOGGER.warn("警告级别");
        LOGGER.error("异常级别");
    }



    /**
     * 添加
     * 注意：要提交事务
     *
     */
    @Test
    public void insert(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp =new Emp();
        emp.setUsername("李四");
        try {
            Integer result = mapper.insertEmp(emp);
            sqlSession.commit();
            System.out.println(result);
            System.out.println(emp);
        }
        catch (Exception ex){
            ex.printStackTrace();
            sqlSession.rollback();
        }
        finally {
            sqlSession.close();
        }

    }

    /**
     * 更新
     * 注意：要提交事务
     *
     */
    @Test
    public void update(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp =new Emp();
        emp.setId(5);
        emp.setUsername("张三");
        try {
            Integer result = mapper.updateEmp(emp);
            sqlSession.commit();
            System.out.println(result);
        }
        catch (Exception ex){
            sqlSession.rollback();
        }
        finally {
            sqlSession.close();
        }

    }


    /**
     * 删除
     * 注意：要提交事务
     *
     */
    @Test
    public void delete(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        try {
            Boolean result = mapper.deleteEmp(12);
            sqlSession.commit();
            System.out.println(result);
        }
        catch (Exception ex){
            sqlSession.rollback();
        }
        finally {
            sqlSession.close();
        }

    }
}
