package com.developersstack.edumanage.repo.custom;

import com.developersstack.edumanage.entity.Teacher;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TeacherRepo {
    public boolean saveTeacher(Teacher teacher) throws SQLException, ClassNotFoundException;
    public String findTeacherLastId() throws SQLException, ClassNotFoundException;
    public Teacher findTeacher(String teacher_code) throws SQLException, ClassNotFoundException;
    public boolean updateTeacher(Teacher teacher) throws SQLException, ClassNotFoundException;
    public ArrayList<Teacher> findAllTeachers(String searchText) throws SQLException, ClassNotFoundException;
    public boolean deleteTeacher(String code) throws SQLException, ClassNotFoundException;
}
