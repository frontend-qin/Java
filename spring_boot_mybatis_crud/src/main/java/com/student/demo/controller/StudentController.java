package com.student.demo.controller;

import com.student.demo.bean.Student;
import com.student.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {
   @Autowired
   private StudentMapper studentMapper;

   /**
    * 获取所有的学生
    * @return
    */
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
   public String insertStudent(Student student){
      studentMapper.insertStudent(student);
      return "insert success";
   }

   /**
    * 按名字进行模糊查询(有可能返回多条)
    * @param name
    * @return
    */
   @GetMapping("likeName")
   public List<Student> selectStudentLikeName(String name){
      System.out.println(name);
      return studentMapper.selectStudentLikeName(name);
   }

   /**
    * 删除某个id的学生
    * @param id
    * @return
    */
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
   @GetMapping("scoreStudent")
   public List<Student> scoreStudent(Integer score){
      return studentMapper.selectStudentScor(score);
   }
}
