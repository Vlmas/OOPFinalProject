package wsp.views;

import wsp.models.Manager;

public class ManagerView extends UserView {
    private Manager manager;

    public ManagerView() {}

    public ManagerView(Manager manager) {
        this.manager = manager;
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