package com.example.ywca_weatherappf22;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class City implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    int id;
    String city;
    String country;

    public City(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public City(String fullCityString) {
        //Torino, PI, Italy
        this.city = fullCityString.substring(0, fullCityString.indexOf(','));
        this.country = fullCityString.substring(fullCityString.indexOf(',') + 2, fullCityString.length());



    }

    protected City(Parcel in) {
        id = in.readInt();
        city = in.readString();
        country = in.readString();

    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(city);
        parcel.writeString(country);
    }
}
