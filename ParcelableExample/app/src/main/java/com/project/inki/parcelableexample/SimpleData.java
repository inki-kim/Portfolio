package com.project.inki.parcelableexample;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {
    int number;
    String message;

    public int getNumber() {
        return number;
    }

    public String getMessage() {
        return message;
    }

    public SimpleData(int num, String msg) {
        number = num;
        message = msg;
    }

    public SimpleData(Parcel in) {
        number = in.readInt();
        message = in.readString();
    }


    public static final Creator<SimpleData> CREATOR = new Creator<SimpleData>() {
        @Override
        public SimpleData createFromParcel(Parcel in) {
            return new SimpleData(in);
        }

        @Override
        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(message);
    }
}
