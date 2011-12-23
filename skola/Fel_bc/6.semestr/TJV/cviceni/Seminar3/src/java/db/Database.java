package db;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static List<DVDItem> dvds = new ArrayList<DVDItem>();

    public static void addDVD(String title, String year, String genre) {
        dvds.add(new DVDItem(title, year, genre));
    }

    public static List<DVDItem> getDvds() {
        return dvds;
    }
}
