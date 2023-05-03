package cn.tulingxueyuan.mapper;


import cn.tulingxueyuan.pojo.Emp;
import cn.tulingxueyuan.pojo.QueryEmpDTO;

import java.util.Map;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
  */
public interface EmpMapper {

    /*徐庶老师实际开发中的实现方式*/
    QueryEmpDTO QueryEmp(Integer id);

    /*实用嵌套结果实现联合查询  多对一 */
    Emp QueryEmp2(Integer id);


    /*实用嵌套查询实现联合查询  多对一 */
    Emp QueryEmp3(Integer id);
}
