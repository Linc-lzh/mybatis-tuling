package cn.tulingxueyuan.tests;

import cn.tulingxueyuan.mapper.EmpMapper;
import cn.tulingxueyuan.pojo.Emp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
     * 分页插件实现
     */
    @Test
    public void test01() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // Mybatis在getMapper就会给我们创建jdk动态代理
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

            // 在需要执行分页查询的上面调用PageHelper.startPage设置 当前页 和 一页数
            PageHelper.startPage(1,10);
            List<Emp> emps = mapper.queryEmp();
            //在查询完后封装成PageInfo （因为pageInfo中的属性非常实用）
            PageInfo page = new PageInfo(emps,5);
            // page 传到request域里面
            System.out.println(page);
            System.out.println("当前页："+page.getPageNum());
            System.out.println("总条数："+page.getTotal());
            System.out.println("是否第一页："+page.isIsFirstPage());
            System.out.println("是否最后一页："+page.isIsLastPage());
            System.out.println("页码导航：");
            for (int i:page.getNavigatepageNums()){
                System.out.print(i+"\t");
            }


        }
    }


    /**
     * 基于mybatis伪分页
     */
    @Test
    public void test02() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // Mybatis在getMapper就会给我们创建jdk动态代理
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);


            List<Emp> emps = mapper.queryEmp(new RowBounds(0,10));

            System.out.println(emps);


        }
    }
}