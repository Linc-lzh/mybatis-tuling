package cn.tulingxueyuan.tests;

import cn.tulingxueyuan.mapper.DeptMapper;
import cn.tulingxueyuan.mapper.EmpMapper;
import cn.tulingxueyuan.pojo.Dept;
import cn.tulingxueyuan.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
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
     * 一级缓存
     * 特性：
     * 1.默认就开启了，也可以关闭一级缓存 localCacheScope=STATEMENT
     * 2.作用域：是基于sqlSession（默认），一次数据库操作会话。
     * 3.缓存默认实现类PerpetualCache ,使用map进行存储的
     * 4.查询完就会进行存储
     * 5.先从二级缓存中获取，再从一级缓存中获取
     * key==>   sqlid+sql
     * 失效情况：
     * 1.不同的sqlSession会使一级缓存失效
     * 2.同一个SqlSession，但是查询语句不一样
     * 3.同一个SqlSession，查询语句一样，期间执行增删改操作
     * 4.同一个SqlSession，查询语句一样，执行手动清除缓存
     */
    @Test
    public void test01() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // Mybatis在getMapper就会给我们创建jdk动态代理
            DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);

            Dept dept = new Dept();
            dept.setId(1);
            List<Dept> depts = mapper.selectDept(dept);

          /* // 执行了增删改，缓存也会失效
           EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
           empMapper.insert(new Emp("缓存"));*/

            //  sqlSession.clearCache();
            Dept dept2 = new Dept();
            dept2.setId(1);
            List<Dept> depts2 = mapper.selectDept(dept2);

            System.out.println(depts);
        }

        //----------------------------不同的sqlSession会使一级缓存失效---------------------------------
        /*try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            // Mybatis在getMapper就会给我们创建jdk动态代理
            DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);

            List<Dept> depts2 = mapper.selectDept(null);
            System.out.println(depts2);
        }*/
    }


    /**
     * 二级缓存：
     *    特性：
     *      1.默认开启了，没有实现
     *      2.作用域：基于全局范围，应用级别。
     *      3.缓存默认实现类PerpetualCache ,使用map进行存储的但是二级缓存根据不同的mapper命名空间多包了一层map
     *              : org.apache.ibatis.session.Configuration#caches    key:mapper命名空间   value:erpetualCache.map
     *          * key==> sqlid+sql
     *      4.事务提交的时候（sqlSession关闭)
     *      5.先从二级缓存中获取，再从一级缓存中获取
     *   实现：
     *      1.开启二级缓存<setting name="cacheEnabled" value="true"/>
     *      2.在需要使用到二级缓存的映射文件中加入<cache></cache>,基于Mapper映射文件来实现缓存的，基于Mapper映射文件的命名空间来存储的
     *      3.在需要使用到二级缓存的javaBean中实现序列化接口implements Serializable
     *          配置成功就会出现缓存命中率 同一个sqlId: 从缓存中拿出的次数/查询总次数
     *
     *   失效：
     *      1.同一个命名空间进行了增删改的操作，会导致二级缓存失效
     *          但是如果不想失效：可以将SQL的flushCache 这是为false,但是要慎重设置，因为会造成数据脏读问题，除非你能保证查询的数据永远不会执行增删改
     *      2.让查询不缓存数据到二级缓存中useCache="false"
     *      3.如果希望其他mapper映射文件的命名空间执行了增删改清空另外的命名空间就可以设置：
     *          <cache-ref namespace="cn.tulingxueyuan.mapper.DeptMapper"/>
     *
     */
    @Test
    public void test02() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // Mybatis在getMapper就会给我们创建jdk动态代理
            DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
            Dept dept = new Dept();
            List<Dept> depts = mapper.selectDept(dept);
        }

        /*
        1.同一个命名空间进行了增删改的操作，会导致二级缓存失效
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
            mapper.insert("测试");
            sqlSession.commit();
        }*/

       /*

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
            empMapper.insert(new Emp("缓存"));
            sqlSession.commit();
        }*/

        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // Mybatis在getMapper就会给我们创建jdk动态代理
            DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);

            Dept dept = new Dept();
            List<Dept> depts = mapper.selectDept(dept);
        }

    }


    /**
     * 只读
     */
    @Test
    public void test03() {
        List<Dept> depts;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // Mybatis在getMapper就会给我们创建jdk动态代理
            DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);

            Dept dept = new Dept();
            dept.setId(1);
            depts = mapper.selectDept(dept);
            System.out.println(depts);
        }
        // 已经将depts存储到二级缓存了
        depts.get(0).setDeptName("呵呵");


        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // Mybatis在getMapper就会给我们创建jdk动态代理
            DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);

            Dept dept = new Dept();
            dept.setId(1);
            depts = mapper.selectDept(dept);
            System.out.println(depts);
        }
    }




}
