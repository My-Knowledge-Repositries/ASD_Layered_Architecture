package com.developersstack.edumanage.db;

import com.developersstack.edumanage.model.Student;

import java.sql.SQLException;
import java.util.Date;

class DatabaseAccessCodeTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new DatabaseAccessCodeTest().saveStudent();
    }

    void saveStudent() throws SQLException, ClassNotFoundException {
        Student student = new Student("s-1", "nimal", new Date(), "galle");
        boolean isSaved = new DatabaseAccessCode().saveStudent(student);
        System.out.println(isSaved);
    }
}