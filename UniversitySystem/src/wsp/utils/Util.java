package wsp.utils;

import wsp.database.Database;
import wsp.models.News;
import java.util.ArrayList;

public class Util {
    public static final String COLOR_RESET = "\033[0m";
    public static final String COLOR_BLACK = "\033[0;30m";
    public static final String COLOR_RED = "\033[0;31m";
    public static final String COLOR_GREEN = "\033[0;32m";
    public static final String COLOR_BLUE = "\033[0;34m";
    public static final String COLOR_WHITE = "\033[0;37m";

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
}