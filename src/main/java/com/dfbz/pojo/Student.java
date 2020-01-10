package com.dfbz.pojo;

public class Student {
    private Integer id;
    private String studentN;
    private String phone;
    private String addr;

    public Student() {
    }

    public Student(Integer id, String studentN, String phone, String addr) {
        this.id = id;
        this.studentN = studentN;
        this.phone = phone;
        this.addr = addr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentN() {
        return studentN;
    }

    public void setStudentN(String studentN) {
        this.studentN = studentN;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentN='" + studentN + '\'' +
                ", phone=" + phone +
                ", addr='" + addr + '\'' +
                '}';
    }
}
