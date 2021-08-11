package models;

import java.util.ArrayList;

public class Squad {
    private String name;
    private String purpose;
    private int totalSize;
    private ArrayList<Hero> hero;
    private static ArrayList<Squad> squadInstances = new ArrayList<>();
    private int id;

    public Squad(String name, String purpose, int size, ArrayList<Hero> hero) {
        this.name = name;
        this.purpose = purpose;
        this.totalSize = size;
        squadInstances.add(this);
        this.id = squadInstances.size();
    }

    public String getName() {
        return this.name;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public int getTotalSize() {
        return this.totalSize;
    }

    public ArrayList<Hero> getHero() {
        return this.hero;
    }

    public static ArrayList<Squad> getSquadInstances() {
        return squadInstances;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        return this.id = id;
    }

    public static void clearSquad() {
        squadInstances.clear();
    }

    public static Squad findById(int id) {
        try {
            return squadInstances.get(id - 1);
        } catch (IndexOutOfBoundsException exception) {
        }
        return null;
    }
}
