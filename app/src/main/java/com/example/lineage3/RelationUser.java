package com.example.lineage3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "relations")
public class RelationUser implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int rId;

    @ColumnInfo(name="r_info")
    public String person1;
    public String person2;

    public String relationBetween;


    public RelationUser() {

    }


    protected RelationUser(Parcel in) {
        rId = in.readInt();
        person1 = in.readString();
        person2 = in.readString();
        relationBetween = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(rId);
        dest.writeString(person1);
        dest.writeString(person2);
        dest.writeString(relationBetween);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RelationUser> CREATOR = new Creator<RelationUser>() {
        @Override
        public RelationUser createFromParcel(Parcel in) {
            return new RelationUser(in);
        }

        @Override
        public RelationUser[] newArray(int size) {
            return new RelationUser[size];
        }
    };
}
