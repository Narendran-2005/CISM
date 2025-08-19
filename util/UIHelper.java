package util;

public class UIHelper {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";

    public static void printHeader(String title) {
        System.out.println(CYAN + "\n=== " + title.toUpperCase() + " ===" + RESET);
    }

    public static void printBoxed(String content) {
        String border = "+-" + "-".repeat(content.length()) + "-+";
        System.out.println(border);
        System.out.println("| " + content + " |");
        System.out.println(border);
    }

    public static void printColored(String text, String colorCode) {
        System.out.println(colorCode + text + RESET);
    }

    public static void pause() {
        System.out.println("\nPress Enter to continue...");
        try
        {
            System.in.read();
        }
        catch (Exception ignored)
        {
           ignored.printStackTrace();
        }
    }
}
