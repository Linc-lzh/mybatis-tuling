package cn.tulingxueyuan.mapper;


import cn.tulingxueyuan.pojo.Emp;
import cn.tulingxueyuan.pojo.QueryEmpDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
  */
public interface EmpMapper {

    List<Emp> QueryEmp(QueryEmpDTO dto);


    List<Emp> QueryEmp2(String deptName);

    List<Emp> QueryEmp3(@Param("usernames") String[] deptName);

    Integer update(Emp emp);

    List<Emp> QueryEmp4(String username);

    Integer insert(Emp emp);

    Integer inserBatch(@Param("emps") List<Emp> emp);

}
