package wsp.views;

import java.io.IOException;

public abstract class UserView {
    private String menu;

    public abstract void start() throws InterruptedException, IOException;

    public abstract void greet();

    public abstract void performAction(String choice) throws IOException;

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
