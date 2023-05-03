package cn.tulingxueyuan.controller;

import cn.tulingxueyuan.pojo.Emp;
import cn.tulingxueyuan.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@RestController
public class EmpController {


    @Autowired
    IEmpService empService;

    @RequestMapping("test")
    public  List<Emp> test(){
        List<Emp> emps = empService.selectEmp();
        return emps;
    }
}
