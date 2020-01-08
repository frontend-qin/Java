package com.student.demo.mapper;

import com.student.demo.bean.Student;

import java.util.List;

public interface StudentMapper {
    /**
     * 获取所有的学生列表(并按成绩的高低排序)
     * @return
     */
  List<Student> getAllStudent();

    /**
     * add Student 到数据库
     * @param student
     */
  void insertStudent(Student student);

    /**
     * 模糊查询名字是 xx 的学生
     * @param name
     * @return
     */
   List<Student> selectStudentLikeName(String name);

    /**
     * 根据id 删除学生
     * @param id
     */
  void deleteStudent(Integer id);

    /**
     * 找出成绩大于 多少分的 学生
     * @param score
     * @return
     */
  List<Student> selectStudentScor(int score);

}
