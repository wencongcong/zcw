package com.role.controller;

import com.role.entity.Department;
import com.role.entity.Employee;
import com.role.entity.Levels;
import com.role.enums.ErrorEnum;
import com.role.result.Result;
import org.springframework.http.MediaType;
import com.role.service.DepartmentService;
import com.role.service.EmployeeService;
import com.role.service.LevelsService;
import com.role.service.impl.VerifCode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private LevelsService levelsService;


    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result Login(@RequestParam(name = "ephone",defaultValue = "0")String ephone, @RequestParam(name = "epwd",defaultValue = "0")String epwd,
                        @RequestParam(name = "getcodeimg",defaultValue = "0")String getcodeimg,HttpServletRequest request,HttpSession session) throws ParseException {
            String imgtext = (String)session.getAttribute("simpleCaptcha").toString();
            Date now = new Date();
            Long codeTime = Long.valueOf(session.getAttribute("codeTime")+"");
            if (imgtext.toUpperCase().equals(getcodeimg.toUpperCase())) {
                session.removeAttribute("simpleCaptcha");
                return employeeService.Login(ephone, epwd);
            } else if ((now.getTime()-codeTime)/1000/60>5) {
                //验证码有效时长为5分钟
                session.removeAttribute("simpleCaptcha");
                return Result.success(0, "验证码已失效，请重新输入！");
            } else{
                return Result.success(0, "验证码错误");
            }
    }

    @RequestMapping(value = "/logins",method = RequestMethod.POST)
    public Result Logins(@RequestParam(name = "ephone",defaultValue = "0")String ephone, @RequestParam(name = "epwd",defaultValue = "0")String epwd
                        ) throws ParseException {
            return employeeService.Login(ephone, epwd);
    }

    @RequestMapping(value = "/yanzhenimg",method = RequestMethod.POST)
    public void sss(@RequestParam Map map, HttpServletResponse response,HttpServletRequest request)throws Exception{
            VerifCode vs=new VerifCode();
            ImageIO.write(vs.getImage(), "JPG", response.getOutputStream());
            request.getSession().setAttribute("simpleCaptcha",vs.getText());
            request.getSession().setAttribute("codeTime",new Date().getTime());
    }

    @RequestMapping(value = "/queryemply",method = RequestMethod.POST)
    public Result queryemply(){
        Map map=new HashMap();
        List<Employee> empl=employeeService.queryAllem();
        List<Department> depar=departmentService.queryAlldp();
        List<Levels> levels=levelsService.queryAlllv();
        map.put("empl",empl);
        map.put("depar",depar);
        map.put("levels",levels);
        return Result.success(1,map);
    }

    @RequestMapping(value = "/chadepa",method = RequestMethod.POST)
    public Result chadepa(@RequestBody Map map){
        SimpleDateFormat sld=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        String time=sld.format(date);
        try {
            if (map.get("rname").toString().equals("0")){
                Department department=new Department();
                department.setDname(map.get("dname").toString());
                department.setDaccept(map.get("daccept").toString());
                department.setDtime(time);
                departmentService.insertOneDepartment(department);
            }else if (map.get("rname").toString().equals("1")){
                Levels levels=new Levels();
                levels.setLname(map.get("lname").toString());
                levels.setLaccept(map.get("laccept").toString());
                levels.setLtime(time);
                levels.setLjiami(Integer.parseInt(map.get("ljiami").toString()));
                levels.setLxiazai(Integer.parseInt(map.get("lxiazai").toString()));
                levels.setLshangchuang(Integer.parseInt(map.get("lshangchuang").toString()));
                levels.setDjson(map.get("data").toString());
                levels.setDarr(map.get("arr").toString());
                levels.setLfenpei(Integer.parseInt(map.get("lfenpei").toString()));
                levelsService.insertOneLevel(levels);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(1,"运行成功");
    }

    @RequestMapping(value = "/tianjia",method = RequestMethod.POST)
    public Result inserEmpl(@RequestParam Map map){

        Employee employee =new Employee();
        Department department=new Department();
        Levels levels=new Levels();
        employee.setEname(map.get("ename").toString());
        employee.setEphone(map.get("ephone").toString());
        int lid=levelsService.chaid(map.get("lname").toString());
        int did=departmentService.chadeid(map.get("dname").toString());
//        department.setId(did);
//        levels.setId(lid);
//        employee.setDepasid(department);
//        employee.setLevelsid(levels);
        employee.setLid(lid);
        employee.setDid(did);
        int result=employeeService.insertOneEmployee(employee);
        if (result>0){
            return Result.success(1,"添加成功");
        }else{
            return Result.success(0,ErrorEnum.FIAL_ERROR);
        }
    }

    @RequestMapping(value = "/xiugailevels",method = RequestMethod.POST)
    public Result updateOnelevels(@RequestBody Map map){
        int result= levelsService.updateOneLevel(map);
        if (result>0){
            return Result.success(result,"修改成功");
        }else{
            return Result.fail(0,ErrorEnum.CHA_ERROR);
        }
    }

    @RequestMapping(value = "/xiugaidepa",method = RequestMethod.POST)
    public Result updateOnede(@RequestParam Map map){
        int result= departmentService.updateOneDepartment(map);
        if (result>0){
            return Result.success(result,"修改成功");
        }else{
            return Result.fail(0,ErrorEnum.CHA_ERROR);
        }
    }

}
