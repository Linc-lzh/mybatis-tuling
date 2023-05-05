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
 */
public class MybatisCRUD {

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
     * 查询
     */
    @Test
    public void select(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.selectEmp(1);
        System.out.println(emp);

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
        emp.setUsername("linc lee");
        try {
            Integer result = mapper.insertEmp(emp);
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
     * 更新
     * 注意：要提交事务
     *
     */
    @Test
    public void update(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp =new Emp();
        emp.setId(4);
        emp.setUsername("linc new");
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
            Integer result = mapper.deleteEmp(4);
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
