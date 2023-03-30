package com.developersstack.edumanage.repo.custom;

import com.developersstack.edumanage.entity.Intake;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IntakeRepo {
    public boolean saveIntake(Intake intake) throws SQLException, ClassNotFoundException;
    public String findIntakeLastId() throws SQLException, ClassNotFoundException;
    public Intake findIntake(String intakeId) throws SQLException, ClassNotFoundException;
    public boolean updateIntake(Intake intake) throws SQLException, ClassNotFoundException;
    public ArrayList<Intake> findAllIntakes(String searchText) throws SQLException, ClassNotFoundException;
    public boolean deleteIntake(String id) throws SQLException, ClassNotFoundException;
    public ArrayList<String> loadAllPrograms() throws SQLException, ClassNotFoundException;
}
