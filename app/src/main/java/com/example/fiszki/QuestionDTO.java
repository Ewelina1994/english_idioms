package com.example.fiszki;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.fiszki.entity.Option;
import com.example.fiszki.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionDTO implements Parcelable {
    Question question;
    List<Option> options;

    public QuestionDTO(Question question, List<Option> options) {
        this.question = question;
        this.options = options;
    }

    protected QuestionDTO(Parcel in) {
        question = in.readParcelable(Question.class.getClassLoader());
        if (in.readByte() == 0x01) {
            options = new ArrayList<Option>();
            in.readList(options, Option.class.getClassLoader());
        } else {
            options = null;
        }
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(question, flags);
        if (options == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(options);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuestionDTO> CREATOR = new Creator<QuestionDTO>() {
        @Override
        public QuestionDTO createFromParcel(Parcel in) {
            return new QuestionDTO(in);
        }

        @Override
        public QuestionDTO[] newArray(int size) {
            return new QuestionDTO[size];
        }
    };

    public Question getQuestion() {

        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
