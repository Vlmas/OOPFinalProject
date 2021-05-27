package wsp.tests;

import wsp.interfaces.CanAlterUserData;

import java.io.Serializable;

public class C implements Serializable {
    private int a;

    public void setA(Object o, int a) {
        if(o instanceof CanAlterUserData) {
            this.a = a;
            System.out.println(a);
        } else {
            System.out.println("Error you don't have the permission");
        }
    }

    public static void main(String[] args) {
        C c = new C();
        c.setA(c, 5);
    }
}
