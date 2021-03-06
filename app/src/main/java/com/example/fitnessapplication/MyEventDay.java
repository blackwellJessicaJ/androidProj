package com.example.fitnessapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;

import java.io.Serializable;
import com.applandeo.materialcalendarview.EventDay;
import android.content.res.Resources;

import java.util.Calendar;


class MyEventDay extends EventDay implements Parcelable {
    private String mNote;




    MyEventDay(Calendar day, int imageResource, String note) {
        super(day, imageResource);
        mNote = note;

    }

    String getNote() {
        return mNote;
    }

    void setNote(String newNote) {mNote= newNote;}


    private MyEventDay(Parcel in) {
        super((Calendar) in.readSerializable(), in.readInt());

        mNote = in.readString();
    }

    public static final Creator<MyEventDay> CREATOR = new Creator<MyEventDay>() {
        @Override
        public MyEventDay createFromParcel(Parcel in) {
            return new MyEventDay(in);
        }

        @Override
        public MyEventDay[] newArray(int size) {
            return new MyEventDay[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(getCalendar());
        parcel.writeInt(R.drawable.ic_message_black_48dp);
        parcel.writeString(mNote);
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
