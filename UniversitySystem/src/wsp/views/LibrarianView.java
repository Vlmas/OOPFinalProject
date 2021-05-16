package wsp.views;

import wsp.models.Librarian;

public class LibrarianView extends UserView {
    private Librarian librarian;

    public LibrarianView() {}

    public LibrarianView(Librarian librarian) {
        this.librarian = librarian;
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