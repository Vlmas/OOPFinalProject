package wsp.views;

import wsp.utils.GlobalReader;
import java.io.IOException;

public abstract class UserView {
    private String menu;

    public UserView() {}

    public boolean start() throws InterruptedException, IOException {
        Thread.sleep(500);

        System.out.println("\nSelect which type of action you want to perform, or X to cancel:");
        System.out.println(getMenu());

        String choice = GlobalReader.reader.readLine();

        if(performAction(choice)) {
            return start();
        } else {
            return true;
        }
    }

    public abstract void greet();

    public abstract boolean performAction(String choice) throws IOException, InterruptedException;

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}