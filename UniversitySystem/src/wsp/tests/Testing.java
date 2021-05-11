package wsp.tests;

import java.io.*;

public class Testing implements Serializable {
    private int a;
    private int b;

    public Testing() {}

    public Testing(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public static void main(String[] args) {
        Testing testing1 = new Testing(5, 10);
        Testing testing2;

        //Serialization
        serialize(testing1);
        //Deserialization
        testing2 = deserialize("test.txt");
        System.out.println("A: " + testing2.a + ", B: " + testing2.b);
    }

    public static void serialize(Testing testing) {
        try {
            FileOutputStream file = new FileOutputStream("test.txt");
            ObjectOutputStream obj = new ObjectOutputStream(file);

            obj.writeObject(testing);
            obj.close();
            file.close();
            System.out.println("Successfully serialized!");
        } catch(IOException exc) {
            System.err.println("Failed to serialize!");
        }
    }

    public static Testing deserialize(String fileName) {
        Testing testing = null;

        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream obj = new ObjectInputStream(file);

            testing = (Testing) obj.readObject();
            obj.close();
            file.close();
        } catch(IOException exc) {
            System.err.println("Failed to deserialize!");
        } catch(ClassNotFoundException exc) {
            System.err.println("Class not found!");
        }

        return testing;
    }
}