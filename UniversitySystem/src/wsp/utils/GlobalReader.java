package wsp.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GlobalReader {
    public static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }
}
