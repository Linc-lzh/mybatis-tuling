package cn.tulingxueyuan.mapper;


import cn.tulingxueyuan.pojo.Dept;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
  */
public interface DeptMapper {
    //嵌套查询： 一对多   使用部门id查询员工
   Dept SelectDeptAndEmps(Integer id);

   // 嵌套查询（异步查询）： 一对多  查询部门及所有员工
    Dept SelectDeptAndEmps2(Integer id);
}
