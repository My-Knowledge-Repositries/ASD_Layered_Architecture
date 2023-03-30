package com.developersstack.edumanage.bo.custom;

import com.developersstack.edumanage.dto.StudentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBo {
    boolean saveStudent(StudentDto dto) throws SQLException, ClassNotFoundException;

    boolean updateStudent(StudentDto dto) throws SQLException, ClassNotFoundException;

    boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;

    StudentDto findStudent(String id) throws SQLException, ClassNotFoundException;

    String findStudentLastId() throws SQLException, ClassNotFoundException;

    ArrayList<StudentDto> searchStudents(String searchText) throws SQLException, ClassNotFoundException;
}
