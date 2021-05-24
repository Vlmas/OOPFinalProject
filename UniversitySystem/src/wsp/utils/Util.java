package wsp.utils;

import wsp.database.Database;
import wsp.models.News;
import wsp.models.User;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

public class Util implements Serializable {
    public static final String COLOR_RESET = "\033[0m";
    public static final String COLOR_BLACK = "\033[0;30m";
    public static final String COLOR_RED = "\033[0;31m";
    public static final String COLOR_GREEN = "\033[0;32m";
    public static final String COLOR_BLUE = "\033[0;34m";
    public static final String COLOR_WHITE = "\033[0;37m";
    public static BufferedReader reader;

    static {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    private Util() {}

    public static int parseChoice(String choice) {
        try {
            return (Integer.parseInt(choice) - 1);
        } catch (NumberFormatException exc) {
            return -1;
        }
    }

    public static boolean isInRange(int number, int left, int right) {
        return (number >= left && number <= right);
    }

    public static void viewNews() {
        ArrayList<News> allNews = Database.getInstance().getNews();

        if(!allNews.isEmpty()) {
            int index = 1;

            for(News news : allNews) {
                System.out.println(COLOR_GREEN + "|" + index + "| " + news.getTitle() + ". " + news.getPostDate() + COLOR_RESET);
                System.out.println("Content: " + news.getContent());
                System.out.println("Comments: " + news.getComments() + "\n");
                index++;
            }
        } else {
            System.out.println("No news for now");
        }
    }

    public static<T> void printList(Iterable<T> list) {
        int index = 1;
        for(T type : list) {
            System.out.println(index + ") " + type);
            index++;
        }
    }

    public static<T extends User> void printUserList(Iterable<T> users) {
        int index = 1;
        for(T user : users) {
            System.out.println(index + ") " + user.getName() + " " + user.getSurname()
                    + " (" + user.getClass().getSimpleName() + ")");
            index++;
        }
    }

    public static void printSingleMap() {

    }

    public static void printMultiMap() {

    }
}