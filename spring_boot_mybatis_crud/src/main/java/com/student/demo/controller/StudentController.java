package com.student.demo.controller;

import com.student.demo.bean.Student;
import com.student.demo.mapper.StudentMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "学生信息表")
@RestController
@RequestMapping("student") // 这里配置路由映射都在/student下
public class StudentController {
   @Autowired
   private StudentMapper studentMapper;

   /**
    * 获取所有的学生
    * @return
    */
   @ApiOperation(value = "获取学生列表")
   @GetMapping("getList")
    public List<Student> getStudentList(){
       return studentMapper.getAllStudent();
   }

   /**
    * 添加学生到数据库
    * @param student
    * @return
    */
   @PostMapping("insert")
   @ApiOperation(value = "创建学生信息", notes = "根据Student对象创建用户")
   public String insertStudent(Student student){
      studentMapper.insertStudent(student);
      return "insert success";
   }

   /**
    * 批量插入多条数据到数据库
    * @param studentList
    * @return
    */
   @ApiOperation(value="批量创建学生信息", notes = "根据List(数组)类型的<Student>对象")
   @PostMapping("insertStudents")
   public String insertStudents(@RequestBody List<Student> studentList){
      studentMapper.insertSomeStudent(studentList);
      return "add list success";
   }

   /**
    * 按名字进行模糊查询(有可能返回多条)
    * @param name
    * @return
    */
   @ApiOperation(value = "按名字进行模糊查询学生信息")
   @GetMapping("likeName")
   public List<Student> selectStudentLikeName(String name){
      return studentMapper.selectStudentLikeName(name);
   }

   /**
    * 删除某个id的学生
    * @param id
    * @return
    */
   @ApiOperation(value="根据id删除对应的学生")
   @GetMapping("{id}")
   public String deleteStudent(@PathVariable("id") Integer id){
      studentMapper.deleteStudent(id);
      return "delete success";
   }

   /**
    * 获取成绩大于 多少分的学僧
    * @param score
    * @return
    */
   @ApiOperation(value="获取成绩大于 多少分的学生")
   @GetMapping("scoreStudent")
   public List<Student> scoreStudent(Integer score){
      return studentMapper.selectStudentScor(score);
   }

   /**
    * 查询金额在 xx 和 xx 之间的学生
    * @param smallMoney
    * @param bigMoney
    * @return
    */
   @ApiOperation(value="获取金额在 xx 和 xx 之间的学生")
   @GetMapping("getStudentMoney")
   public List<Student> getStudentMoney(Integer smallMoney, Integer bigMoney){
      return studentMapper.selectMoneyIn(smallMoney, bigMoney);
   }

   /**
    * 更新一个学生的信息
    * @param student
    * @return
    */
   @ApiOperation(value="根据 id 更新一个学生的信息")
   @PostMapping("/updateStudent")
   public String updateStudent(Student student){
      studentMapper.updateStudent(student);
      return "success update";
   }
}
