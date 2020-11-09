package com.role.controller;

import com.role.entity.Employee;
import com.role.enums.ErrorEnum;
import com.role.result.Result;
import com.role.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/empl")
public class EmplController {

    @Resource
    private EmployeeService employeeService;

    @RequestMapping(value = "/turnorder",method = RequestMethod.POST)
    public Result trunoforder(@RequestParam Map map){

        return Result.success();
    }

    @RequestMapping(value ="/Gpwd",method = RequestMethod.POST)
    public Result Gpwd(@RequestParam Map map){
         if (employeeService.chaOneEmplyee(map.get("ephone").toString(),map.get("oldpwd").toString())==0){
            return Result.fail(0,"账号或密码错误,请重新输入");
        }else if (employeeService.updateOneEmployee(map)==0){
            return Result.fail(0,"修改失败");
        }else {
            return Result.success(1,"修改成功");
        }
    }

    @RequestMapping(value = "/empldepa",method = RequestMethod.POST)
    public Result Gtokem(@RequestParam Map map){
        int result=employeeService.updateOneEmployee(map);
        if (result>0){
            return Result.success(result,"修改成功");
        }else{
            return Result.fail(0, ErrorEnum.CHA_ERROR);
        }
    }

    @RequestMapping(value = "/deleteempl",method = RequestMethod.POST)
    public Result deleteempl(@RequestParam(value = "id",defaultValue = "0")int id){
        int result=employeeService.deleteOneEmployee(id);
        if (result>0){
            return Result.success(result,"删除成功");
        }else{
            return Result.fail(0, "删除失败");
        }
    }
    @RequestMapping(value = "/queryAllde",method = RequestMethod.POST)
    public Result chaname(@RequestParam(defaultValue = "",value = "ename")String ename){
        List<Employee> lists=employeeService.queryAllDepa(ename);
        return Result.success(1,lists);
    }
}
