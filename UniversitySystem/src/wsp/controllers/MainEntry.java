package wsp.controllers;

import wsp.exceptions.FailedLogInException;
import java.io.IOException;

public class MainEntry {
    public static void main(String[] args) throws IOException, FailedLogInException, InterruptedException {
        SystemController system = new SystemController();
        system.start();
    }
}