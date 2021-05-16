package wsp.views;

import wsp.models.Teacher;

public class TeacherView extends UserView {
    private Teacher teacher;

    public TeacherView() {}

    public TeacherView(Teacher teacher) {
        this.teacher = teacher;
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