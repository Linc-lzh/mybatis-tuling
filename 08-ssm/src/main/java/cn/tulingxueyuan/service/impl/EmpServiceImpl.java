package cn.tulingxueyuan.service.impl;

import cn.tulingxueyuan.mapper.EmpMapper;
import cn.tulingxueyuan.pojo.Emp;
import cn.tulingxueyuan.service.IEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Service
public class EmpServiceImpl implements IEmpService {


    @Autowired
    EmpMapper empMapper;

    @Override
    public List<Emp> selectEmp() {
       return  empMapper.selectAll();
    }
}
