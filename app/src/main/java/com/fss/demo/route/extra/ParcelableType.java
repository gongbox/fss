package com.fss.demo.route.extra;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableType implements Parcelable {
    public String name;
    public int age;

    public ParcelableType(String name, int age) {
        this.name = name;
        this.age = age;
    }

    protected ParcelableType(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParcelableType> CREATOR = new Creator<ParcelableType>() {
        @Override
        public ParcelableType createFromParcel(Parcel in) {
            return new ParcelableType(in);
        }

        @Override
        public ParcelableType[] newArray(int size) {
            return new ParcelableType[size];
        }
    };

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
