package com.dfbz.service.impl;

import com.dfbz.dao.StudentDao;
import com.dfbz.dao.impl.StudentDaoImpl;
import com.dfbz.pojo.Student;
import com.dfbz.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private StudentDao dao = new StudentDaoImpl();

    @Override
    public void save(Student student) {
        dao.save(student);
    }

    @Override
    public void delStudentById(int id) {
        dao.delById(id, Student.class);
    }

    @Override
    public List<Student> getListById() {
        return dao.getListById(Student.class);
    }

    @Override
    public void update(Student student) {
        dao.add(student);
    }

    @Override
    public void delAllById(String[] ids) {
        Integer[] intIds = new Integer[ids.length];
        for (int i = 0; i < ids.length; i++) {
            intIds[i] = Integer.parseInt(ids[i]);
        }
        dao.delByIds(intIds, Student.class);
    }
}
