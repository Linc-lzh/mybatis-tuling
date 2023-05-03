package cn.tulingxueyuan.mapper;


import cn.tulingxueyuan.pojo.Emp;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
  */
public interface EmpMapper {

    List<Emp> queryEmp(RowBounds rowBounds);

    List<Emp> queryEmp();

}
