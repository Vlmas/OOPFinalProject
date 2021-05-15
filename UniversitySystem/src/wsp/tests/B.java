package wsp.tests;

import java.io.Serializable;

public class B implements Serializable {
    private int b;
    private A myA;

    B() {}

    public B(int b, A myA) {
        this.b = b;
        this.myA = myA;
    }

    public int getB() {
        return b;
    }

    public A getMyA() {
        return myA;
    }
}
