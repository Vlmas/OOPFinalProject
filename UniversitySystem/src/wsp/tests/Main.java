package wsp.tests;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        B myB = new B(5, new A(10));
        B bb;

        serialize(myB);
        //bb = deserialize("ttt.txt");
    }

    public static void serialize(B b) {
        try {
            FileOutputStream file = new FileOutputStream("test.txt");
            ObjectOutputStream obj = new ObjectOutputStream(file);

            obj.writeObject(b);
            obj.close();
            file.close();
            System.out.println("Successfully serialized!");
        } catch(IOException exc) {
            System.err.println("Failed to serialize!");
            exc.printStackTrace();
        }
    }

    public static B deserialize(String fileName) {
        B testing = null;

        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream obj = new ObjectInputStream(file);

            testing = (B) obj.readObject();
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