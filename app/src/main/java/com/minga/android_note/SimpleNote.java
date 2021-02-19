package com.minga.android_note;

import android.os.Parcel;
import android.os.Parcelable;

class SimpleNote implements Parcelable {
    private String title;
    private String desc;
    private String date;

    public SimpleNote(String title, String desc, String date) {
        this.title = title;
        this.desc = desc;
        this.date = date;
    }

    protected SimpleNote(Parcel in) {
        title = in.readString();
        desc = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SimpleNote> CREATOR = new Creator<SimpleNote>() {
        @Override
        public SimpleNote createFromParcel(Parcel in) {
            return new SimpleNote(in);
        }

        @Override
        public SimpleNote[] newArray(int size) {
            return new SimpleNote[size];
        }
    };
}