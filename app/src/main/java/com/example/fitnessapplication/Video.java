package com.example.fitnessapplication;

import android.os.Parcel;
import android.os.Parcelable;

 class Video implements Parcelable {

    private String mVidURL;

    Video(String video)
    {
        mVidURL = video;
    }

     protected Video(Parcel in) {
         mVidURL = in.readString();
     }

     public static final Creator<Video> CREATOR = new Creator<Video>() {
         @Override
         public Video createFromParcel(Parcel in) {
             return new Video(in);
         }

         @Override
         public Video[] newArray(int size) {
             return new Video[size];
         }
     };

     public void setVidURL(String mVidURL) {
        this.mVidURL = mVidURL;
    }

    public String getVidURL() {
        return mVidURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mVidURL);

    }
}
