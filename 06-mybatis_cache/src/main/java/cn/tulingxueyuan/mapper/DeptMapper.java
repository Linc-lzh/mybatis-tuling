package cn.tulingxueyuan.mapper;


import cn.tulingxueyuan.pojo.Dept;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
  */
public interface DeptMapper {


    // 查询部门
    List<Dept> selectDept(Dept dept);

    //插入部门
    Integer insert(String deptName);
}
