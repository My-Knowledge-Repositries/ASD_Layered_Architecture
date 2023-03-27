package com.developersstack.edumanage.controller;

import com.developersstack.edumanage.db.DatabaseAccessCode;
import com.developersstack.edumanage.entity.Intake;
import com.developersstack.edumanage.entity.Student;
import com.developersstack.edumanage.view.tm.IntakeTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

public class IntakeFormController {

    public AnchorPane context;
    public TextField txtId;
    public TextField txtSearch;
    public Button btn;
    public TableView<IntakeTm> tblIntakes;
    public TableColumn colId;
    public TableColumn colIntake;
    public TableColumn colStartDate;
    public TableColumn colProgram;
    public TableColumn colCompleteState;
    public TableColumn colOption;
    public TextField txtName;
    public DatePicker txtDate;
    public ComboBox<String> cmbProgram;
    String searchText = "";

    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("intakeId"));
        colIntake.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("intakeName"));
        colProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        colCompleteState.setCellValueFactory(new PropertyValueFactory<>("completeState"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        allProgramsId();
        setTableData(searchText);

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            setTableData(searchText);
        });

        tblIntakes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (null != newValue) {
                setData(newValue);
            }
        });
    }

    private void setData(IntakeTm tm) {
        txtId.setText(tm.getIntakeId());
        txtName.setText(tm.getIntakeName());
        txtDate.setValue(LocalDate.now());
        cmbProgram.setValue(tm.getProgram());
        btn.setText("Update Intake");
    }

    private void setTableData(String searchText) {
        ObservableList<IntakeTm> obList = FXCollections.observableArrayList();
        try {
            for (Intake tm : new DatabaseAccessCode().findAllIntakes(searchText)
            ) {
                Button btn = new Button("Delete");
                IntakeTm t = new IntakeTm(
                        tm.getIntakeId(),
                        tm.getStartDate().toString(),
                        tm.getIntakeName(),
                        tm.getProgramId(),
                        tm.isIntakeCompleteness(),
                        btn
                );

                btn.setOnAction(e -> {
                    Alert alert = new Alert(
                            Alert.AlertType.CONFIRMATION,
                            "Are you sure?",
                            ButtonType.YES, ButtonType.NO
                    );
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get().equals(ButtonType.YES)) {
                        try {
                            boolean isDeleted = new DatabaseAccessCode().deleteIntake(t.getIntakeId());
                            if (isDeleted) {
                                new Alert(Alert.AlertType.INFORMATION, "Intake Deleted!").show();
                                setTableData(searchText);
                                setIntakeId();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try Again").show();
                            }
                        } catch (SQLException | ClassNotFoundException exception) {
                            exception.printStackTrace();
                        }
                        new Alert(Alert.AlertType.INFORMATION, "Deleted!").show();
                    }
                });
                obList.add(t);
            }
            tblIntakes.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setIntakeId() {
        try {
            String selectedId = new DatabaseAccessCode().findIntakeLastId();
            if (null != selectedId) {
                String[] splitData = selectedId.split("-");
                String lastIdIntegerNumberAsAString = splitData[1];
                int lastIntegerIdAsInt = Integer.parseInt(lastIdIntegerNumberAsAString);
                lastIntegerIdAsInt++;
                String generatedStudentId = "S-" + lastIntegerIdAsInt;
                txtId.setText(generatedStudentId);
            } else {
                txtId.setText("I-1");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void allProgramsId() {
        try {
            cmbProgram.setItems(FXCollections.observableArrayList(
                    new DatabaseAccessCode().loadAllPrograms()
            ));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void newIntakeOnAction(ActionEvent actionEvent) {
        txtId.clear();
        txtName.clear();
        txtDate.setValue(null);
        cmbProgram.setValue(null);
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void saveOnAction(ActionEvent actionEvent) {
        Intake intake = new Intake(
                txtId.getText(),
                txtName.getText(),
                Date.from(txtDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                cmbProgram.getValue(),
                Boolean.parseBoolean()

        );

        if (btn.getText().equalsIgnoreCase("Save Student")) {
            try {
                boolean isSaved = new DatabaseAccessCode().saveIntake(intake);
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Student Saved!").show();
                    setIntakeId();
//                    clear();
                    setTableData(searchText);
                } else {
                    new Alert(Alert.AlertType.WARNING, "Try Again").show();
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.toString()).show();
            }

        } else {

            try {
                Intake selectedIntake = new DatabaseAccessCode().findIntake(txtId.getText());
                if (null!=selectedIntake){
                    boolean isUpdated= new DatabaseAccessCode().updateIntake(intake);
                    if (isUpdated) {
                        new Alert(Alert.AlertType.INFORMATION, "Updated!").show();
                        setIntakeId();
// clear();
                        setTableData(searchText);
                        btn.setText("Save Student");
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again").show();
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.toString()).show();
            }
        }
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
        stage.centerOnScreen();
    }
}
