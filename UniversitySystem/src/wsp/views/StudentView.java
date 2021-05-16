package wsp.views;

import wsp.models.Student;

public class StudentView extends UserView {
    private Student student;

    public StudentView() {}

    public StudentView(Student student) {
        this.student = student;
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public void greet() {

    }

    @Override
    public boolean performAction(String choice) {
        return true;
    }
}