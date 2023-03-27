package com.developersstack.edumanage.repo.custom;

import com.developersstack.edumanage.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentRepo {
    boolean saveStudent(Student student) throws SQLException, ClassNotFoundException;
    String findStudentLastId() throws SQLException, ClassNotFoundException;
    Student findStudent(String student_id) throws SQLException, ClassNotFoundException;
    public boolean updateStudent(Student student) throws SQLException, ClassNotFoundException;
    public ArrayList<Student> findAllStudents(String searchText) throws SQLException, ClassNotFoundException;
    public boolean deleteStudent(String studentId) throws SQLException, ClassNotFoundException;
}
