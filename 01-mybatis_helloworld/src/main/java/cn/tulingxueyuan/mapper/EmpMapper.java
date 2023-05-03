package cn.tulingxueyuan.mapper;

import cn.tulingxueyuan.pojo.Emp;
import org.apache.ibatis.annotations.Select;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public interface EmpMapper {
    // 根据id查询Emp实体
    //@Select("select * from emp where id=#{id}")
    Emp selectEmp(Integer id);

    // 插入
    Integer insertEmp(Emp emp);

    // 更新
    Integer updateEmp(Emp emp);

    // 删除
    Integer deleteEmp(Integer id);
}
