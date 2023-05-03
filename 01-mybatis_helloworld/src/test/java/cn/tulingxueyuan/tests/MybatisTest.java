package cn.tulingxueyuan.tests;

import cn.tulingxueyuan.mapper.EmpMapper;
import cn.tulingxueyuan.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 *
 * MyBatis 搭建步骤：
 * 1.添加pom依赖 （mybatis的核心jar包和数据库版本对应版本的驱动jar包）
 * 2.新建数据库和表
 * 3.添加mybatis全局配置文件 （可以从官网中复制）
 * 4.修改mybatis全局配置文件中的 数据源配置信息
 * 5.添加数据库表对应的POJO对象（相当于我们以前的实体类）
 * 6.添加对应的PojoMapper.xml (里面就维护所有的sql)
 *      修改namespace:  如果是StatementId没有特殊的要求
 *                      如果是接口绑定的方式必须等于接口的完整限定名
 *      修改对应的id(唯一)、resultType 对应返回的类型如果是POJO需要制定完整限定名
 * 7.修改mybatis全局配置文件：修改Mapper
 */
public class MybatisTest {

    SqlSessionFactory sqlSessionFactory;
    @Before
    public void before(){
        // 从 XML 中构建 SqlSessionFactory
        String resource = "mybatis.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 基于StatementId的方式去执行SQL
     *      <mapper resource="EmpMapper.xml"/>
     * @throws IOException
     */
    @Test
    public void test01() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Emp emp = (Emp) session.selectOne("cn.tulingxueyuan.pojo.EmpMapper.selectEmp", 1);
            System.out.println(emp);
        }
    }

    /**
     * 基于接口绑定的方式
     *  1.新建数据访问层的接口：  POJOMapper
     *  2.添加mapper中对应的操作的方法
     *      1.方法名要和mapper中对应的操作的节点的id要一致
     *      2.返回类型要和mapper中对应的操作的节点的resultType要一致
     *      3.mapper中对应的操作的节点的参数必须要在方法的参数中声明
     *  3.Mapper.xml 中的namespace必须要和接口的完整限定名要一致
     *  4.修改mybatis全局配置文件中的mappers,采用接口绑定的方式:
     *        <mapper class="cn.tulingxueyuan.mapper.EmpMapper"></mapper>
     *  5.一定要将mapper.xml和接口放在同一级目录中，只需要在resources新建和接口同样结构的文件夹就行了，生成就会合并在一起
     *
     * @throws IOException
     */
    @Test
    public void test02(){
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = mapper.selectEmp(1);
            System.out.println(emp);
        }
    }


    /**
     * 基于注解的方式
     * 1.在接口方法上面写上对应的注解
     *@Select("select * from emp where id=#{id}")
     * 注意：
     *      注解可以和xml共用， 但是不能同时存在方法对应的xml的id
     *
     */
    @Test
    public void test03(){
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmpMapper mapper = session.getMapper(EmpMapper.class);
            Emp emp = mapper.selectEmp(1);
            System.out.println(emp);
        }
    }

}
