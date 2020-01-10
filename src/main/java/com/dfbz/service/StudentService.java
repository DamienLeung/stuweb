package com.dfbz.service;

import com.dfbz.pojo.Student;

import java.util.List;

public interface StudentService {

    void save(Student student);

    void delStudentById(int id);

    List<Student> getListById();

    void update(Student student);

    void delAllById(String[] ids);
}
