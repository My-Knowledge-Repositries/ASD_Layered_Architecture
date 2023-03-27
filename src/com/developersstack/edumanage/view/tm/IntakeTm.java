package com.developersstack.edumanage.view.tm;

import javafx.scene.control.Button;

public class IntakeTm {
    private String intakeId;
    private String startDate;
    private String intakeName;
    private String program;
    private boolean completeState;
    private Button btn;

    public IntakeTm() {
    }

    public IntakeTm(String intakeId, String startDate, String intakeName, String program, boolean completeState, Button btn) {
        this.intakeId = intakeId;
        this.startDate = startDate;
        this.intakeName = intakeName;
        this.program = program;
        this.completeState = completeState;
        this.btn = btn;
    }

    public String getIntakeId() {
        return intakeId;
    }

    public void setIntakeId(String intakeId) {
        this.intakeId = intakeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getIntakeName() {
        return intakeName;
    }

    public void setIntakeName(String intakeName) {
        this.intakeName = intakeName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public boolean isCompleteState() {
        return completeState;
    }

    public void setCompleteState(boolean completeState) {
        this.completeState = completeState;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
