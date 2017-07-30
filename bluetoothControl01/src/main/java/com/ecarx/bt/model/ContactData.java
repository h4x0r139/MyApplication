package com.ecarx.bt.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kongnan on 16/12/14.
 * 联系人
 */
public class ContactData implements Parcelable, Serializable {

    private String name = "";
    private String letter = "";

    private List<Integer> phoneNumTypeList = new ArrayList<>();

    private List<String> phoneNumList = new ArrayList<>();

    public List<NumberData> numbers = new ArrayList<>();

    public ContactData() {
    }

    private ContactData(Parcel in) {
        name = in.readString();
        numbers = in.createTypedArrayList(NumberData.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(numbers);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ContactData> CREATOR = new Creator<ContactData>() {
        @Override
        public ContactData createFromParcel(Parcel in) {
            return new ContactData(in);
        }

        @Override
        public ContactData[] newArray(int size) {
            return new ContactData[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public List<String> getPhoneNumList() {
        return phoneNumList;
    }

    public void setPhoneNumList(List<String> phoneNumList) {
        this.phoneNumList = phoneNumList;
    }

    public List<Integer> getPhoneNumTypeList() {
        return phoneNumTypeList;
    }

    public void setPhoneNumTypeList(List<Integer> phoneNumList) {
        this.phoneNumTypeList = phoneNumList;
    }

    public static class NumberData implements Parcelable, Serializable {

        public String type = "";
        public String number = "";

        public NumberData(String type, String number) {
            this.type = type;
            this.number = number;
        }

        NumberData(Parcel in) {
            type = in.readString();
            number = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(type);
            dest.writeString(number);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<NumberData> CREATOR = new Creator<NumberData>() {
            @Override
            public NumberData createFromParcel(Parcel in) {
                return new NumberData(in);
            }

            @Override
            public NumberData[] newArray(int size) {
                return new NumberData[size];
            }
        };
    }

    public String getPhone() {
        if (numbers != null && numbers.size() > 0) {
            return numbers.get(0).number;
        }
        return "";
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", letter='" + letter + '\'' +
                ", phoneNumTypeList=" + phoneNumTypeList +
                ", phoneNumList=" + phoneNumList +
                ", numbers=" + numbers +
                '}';
    }
}
