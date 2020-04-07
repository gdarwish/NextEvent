package com.nextevent.JavaBeans;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

/**
 * @author Ghaith Darwish
 * @author Abel Anderson
 * @since 06/04/2020
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
    private int isSaved = 0;
    private int isAdded = 0;

    public Event(String id, String title, String description, String category, String[] labels, int rank, int duration, String start, String end, double[] location, String country, String image, int isSaved, int isAdded) {
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
        this.isSaved = isSaved;
        this.isAdded = isAdded;
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

    /**
     * Creates a parsable date out of the start date.
     *
     * @return a parsable date
     * @author Abel Anderson
     * @since 01/04/2020
     */
    public String getParsableStartDate() {
        return start.substring(0, 10).replace('-', '/');
    }

    public String getFormattedStartDate() {
        return getParsableStartDate() + " - " + (!start.substring(11, 16).equals("00:00") ? start.substring(11,16) : "All Day");
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

    public String getDescription() {
        if (!description.isEmpty()){
            return description;
        }
        return "No description...";
    }

    public String getCategory() {
        return category;
    }

    public String[] getLabels() {
        return labels;
    }

    public int getRank() {
        return rank;
    }

    public int getDuration() {
        return duration;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public double[] getLocation() {
        return location;
    }

    public String getCountry() {
        return country;
    }

    public int getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved ? 1 : 0;
    }

    public int getIsAdded() {
        return isAdded;
    }

    public void setIsAdded(boolean isAdded) {
        this.isAdded = isAdded ? 1 : 0;
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
