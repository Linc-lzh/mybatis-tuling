package cn.tulingxueyuan.mapper;

import cn.tulingxueyuan.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 *
 * 注意：
 * mapper 文件中同一个命名空间只能有一个唯一的id。 所以就导致接口中也只能有唯一的方法名，虽然在java语法中是没有问题的，但是mybatis不支持。
 */
public interface EmpMapper {


   /*
    单个参数
    Emp SelectEmp(Integer id);
   * #{随便输入}
   * */

    /*
    多个参数
     没使用了@Param：
                   id=====>  #{arg0} 或者 #{param1}
             username=====>  #{arg1} 或者 #{param2}
     当使用了@Param:
               id=====>  #{id} 或者 #{param1}
         username=====>  #{username} 或者 #{param2}
    Emp SelectEmp(@Param("id") Integer id,@Param("username") String username);
     */



    /*
    javaBean的参数
    单个参数：Emp SelectEmp(Emp emp);
        获取方式：可以直接使用属性名
            emp.id=====>#{id}
            emp.username=====>#{username}
        多个参数：Emp SelectEmp(Integer num,Emp emp);
            num===>    #{param1} 或者 @Param
            emp===> 必须加上对象别名： emp.id===> #{param2.id} 或者  @Param("emp")Emp emp    ====>#{emp.id}
                                    emp.username===> #{param2.username} 或者  @Param("emp")Emp emp    ====>#{emp.username}
    Emp SelectEmp(@Param("a")Integer num,Emp emp);*/


   /*
     如果是list,MyBatis会自动封装为map:
            {key:"list":value:usernames}
            要获得:usernames.get(0)  =====>  #{list[0]}
    List<Emp> SelectEmp(List<String> usernames);*/

/*如果是数组,MyBatis会自动封装为map:
            {key:"array":value:usernames}
              没用@Param("")要获得:usernames.get(0)  =====>  #{array[0]}
                                :usernames.get(0)  =====>  #{agr0[0]}
              有@Param("usernames")要获得:usernames.get(0)  =====>  #{usernames[0]}
                                         :usernames.get(0)  =====>  #{param1[0]}
    List<Emp> SelectEmp(@Param("usernames") String[] usernames);*/



    List<Emp> SelectEmp(Integer id);
}
