package com.nextevent.JavaBeans;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * @author Ghaith Darwish
 * @last modified: 30/03/2020
 */
public class EventResult implements Parcelable {
    int count;
    boolean overflow;
    String next;
    String previous;
    Event[] results;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.count);
        dest.writeByte(this.overflow ? (byte) 1 : (byte) 0);
        dest.writeString(this.next);
        dest.writeString(this.previous);
    }

    public EventResult() {
    }

    protected EventResult(Parcel in) {
        this.count = in.readInt();
        this.overflow = in.readByte() != 0;
        this.next = in.readString();
        this.previous = in.readString();
    }

    public static final Creator<EventResult> CREATOR = new Creator<EventResult>() {
        @Override
        public EventResult createFromParcel(Parcel source) {
            return new EventResult(source);
        }

        @Override
        public EventResult[] newArray(int size) {
            return new EventResult[size];
        }
    };

    public int getCount() {
        return count;
    }

    public boolean isOverflow() {
        return overflow;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public Event[] getResults() {
        return results;
    }

    public static Creator<EventResult> getCREATOR() {
        return CREATOR;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Count: %d\nNext: %s®\nPrevious: %s\n", count, next, previous);
    }
}
