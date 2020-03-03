package com.nextevent.JavaBeans;

public class Event {
    private int id;
    private String name;
    private String description;
    private String image;
    private String date;
    private boolean isSaved;
    private boolean isRegistered;

    public Event(int id, String name, String description, String image, String date, int isSaved, int isRegistered) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.date = date;
        this.isSaved = isSaved == 1;
        this.isRegistered = isRegistered == 1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getDate() {
        return date;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public boolean isRegistered() {
        return isRegistered;
    }
}
