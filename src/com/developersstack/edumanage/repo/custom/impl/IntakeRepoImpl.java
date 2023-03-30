package com.developersstack.edumanage.repo.custom.impl;

import com.developersstack.edumanage.db.DBConnection;
import com.developersstack.edumanage.entity.Intake;
import com.developersstack.edumanage.repo.custom.IntakeRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IntakeRepoImpl implements IntakeRepo {
    @Override
    public boolean saveIntake(Intake intake) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO intake VALUES(?,?,?,?,?)");
        stm.setString(1, intake.getIntakeId());
        stm.setString(2, intake.getIntakeName());
        stm.setObject(3, intake.getStartDate());
        stm.setString(4, intake.getProgramId());
        stm.setBoolean(5, intake.isIntakeCompleteness());
        return stm.executeUpdate() > 0;
    }

    @Override
    public String findIntakeLastId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT intake_id FROM intake ORDER BY CAST(SUBSTRING(intake_id,3) AS UNSIGNED) DESC LIMIT 1");
        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public Intake findIntake(String intakeId) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM intake WHERE intake_id=?");
        ResultSet rst = stm.executeQuery();
        stm.setString(1, intakeId);
        if (rst.next()) {
            return new Intake(
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getBoolean(5));
        }
        return null;
    }

    @Override
    public boolean updateIntake(Intake intake) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("UPDATE intake SET intake_name=?, start_date=?, intake_completeness=?, program_code=? WHERE intake_id=?");
        stm.setString(1, intake.getIntakeName());
        stm.setObject(2, intake.getStartDate());
        stm.setString(3, intake.getProgramId());
        stm.setString(4, String.valueOf(intake.isIntakeCompleteness()));
        stm.setString(5, intake.getIntakeId());
        return stm.executeUpdate() > 0;
    }

    @Override
    public ArrayList<Intake> findAllIntakes(String searchText) throws SQLException, ClassNotFoundException {
        searchText = "%" + searchText + "%";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM intake WHERE intake_id LIKE ? OR intake_name LIKE ?");
        stm.setString(1, searchText);
        stm.setString(2, searchText);
        ResultSet rst = stm.executeQuery();
        ArrayList<Intake> intakeList = new ArrayList<>();
        while (rst.next()) {
            intakeList.add(new Intake(
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getBoolean(5)));
        }
        return intakeList;
    }

    @Override
    public boolean deleteIntake(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("DELETE FROM inkate WHERE intake_id=?");
        stm.setString(1, id);
        return stm.executeUpdate() > 0;
    }

    @Override
    public ArrayList<String> loadAllPrograms() throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT program_code FROM Intake");
        ResultSet rst = pstm.executeQuery();
        ArrayList<String> codeList = new ArrayList<>();
        while (rst.next()) {
            codeList.add(rst.getString(4));
        }
        return codeList;
    }
}
