package cn.tulingxueyuan.mapper;

import cn.tulingxueyuan.pojo.Emp;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 *
 * 注意：
 * mapper 文件中同一个命名空间只能有一个唯一的id。 所以就导致接口中也只能有唯一的方法名，虽然在java语法中是没有问题的，但是mybatis不支持。
 */
public interface EmpMapper {
    // 查询
    Emp SelectEmp(Integer id);

    // 插入
    Integer insertEmp(Emp emp);

    // 更新
    Integer updateEmp(Emp emp);

    /**
     * 增删改的返回值 除了可以声明int 还可以声明 boolean(如果大于1就会返回true)
     * @param id
     * @return
     */
    // 删除
    boolean deleteEmp(Integer id);
}
