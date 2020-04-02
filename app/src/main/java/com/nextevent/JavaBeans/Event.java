package com.nextevent.JavaBeans;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

/**
 * @author Ghaith Darwish
 * @Last Modified: 30/03/2020
 */
public class Event implements Parcelable {

    private String id;
    private String title;
    private String description;
    private String category;
    private String[] labels;
    private int rank;
    private int duration;
    private String start;
    private String end;
    private double[] location;
    private String country;
    private String image;

    private double lang;
    private double lat;

    public Event(String title, String start, String country, String image) {
        this.title = title;
        this.start = start;
        this.country = country;
        this.image = image;
    }

    public Event(String id, String title, String description, String category, String[] labels, int rank, int duration, String start, String end, double[] location, String country, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.labels = labels;
        this.rank = rank;
        this.duration = duration;
        this.start = start;
        this.end = end;
        this.location = location;
        this.country = country;
        this.image = image;
    }

    public double getLang() {
        if (location.length >= 2)
            return location[0];
        return 0;
    }

    public double getLat() {
        if (location.length >= 2) {
            return location[1];
        }
        return 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double[] getLocation() {
        return location;
    }

    public void setLocation(double[] location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // get the image URL by event category
    public String getImage() {
        switch (category) {
            case "school-holidays":
                image = "https://gdarwish.scweb.ca/NextEventImages/school-holiday.png";
                break;
            case "public-holidays":
                image = "https://gdarwish.scweb.ca/NextEventImages/public-holiday.png";
                break;
            case "observances":
                image = "https://gdarwish.scweb.ca/NextEventImages/observances.png";
                break;
            case "politics":
                image = "https://gdarwish.scweb.ca/NextEventImages/politics.png";
                break;
            case "conferences":
                image = "https://gdarwish.scweb.ca/NextEventImages/conferences.png";
                break;
            case "expos":
                image = "https://gdarwish.scweb.ca/NextEventImages/concerts.jpg";
                break;
            case "festivals":
                image = "https://gdarwish.scweb.ca/NextEventImages/festivals.png";
                break;
            case "performing-arts":
                image = "https://gdarwish.scweb.ca/NextEventImages/performing.png";
                break;
            case "community":
                image = "https://gdarwish.scweb.ca/NextEventImages/community.png";
                break;
            case "sports":
                image = "https://gdarwish.scweb.ca/NextEventImages/sports.png";
                break;
            case "daylight-savings":
                image = "https://gdarwish.scweb.ca/NextEventImages/daylight.png";
                break;
            case "airport-delays":
                image = "https://gdarwish.scweb.ca/NextEventImages/airport.png";
                break;
            case "severe-weather":
                image = "https://gdarwish.scweb.ca/NextEventImages/severe.jpg";
                break;
            case "disasters":
                image = "https://gdarwish.scweb.ca/NextEventImages/disasters.png";
                break;
            case "terror":
                image = "https://gdarwish.scweb.ca/NextEventImages/terror.png";
                break;
            case "concerts":
                image = "https://gdarwish.scweb.ca/NextEventImages/concerts.jpg";
                break;
            default:
                image = "default image";
        }
        return image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.category);
        dest.writeStringArray(this.labels);
        dest.writeInt(this.rank);
        dest.writeInt(this.duration);
        dest.writeString(this.start);
        dest.writeString(this.end);
        dest.writeDoubleArray(this.location);
        dest.writeString(this.country);
        dest.writeString(this.image);
    }

    protected Event(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.category = in.readString();
        this.labels = in.createStringArray();
        this.rank = in.readInt();
        this.duration = in.readInt();
        this.start = in.readString();
        this.end = in.readString();
        this.location = in.createDoubleArray();
        this.country = in.readString();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Event) {
            return id.equals(((Event) obj).getId());
        }
        return super.equals(obj);
    }
}
