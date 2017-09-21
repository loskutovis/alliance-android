package is.loskutov.alliance.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Questions implements Parcelable {
    private int id, number, rightAnswer, theme;
    private char category4, category5, category6;
    private String question, answer1, answer2, answer3, category, userAnswer = null;

    Questions(JSONObject question) throws JSONException {
        this.id = question.getInt("id");
        this.number = question.getInt("number");
        this.rightAnswer = question.getInt("right_answer");
        this.theme = question.getInt("theme");

        this.category4 = question.getString("category4").toCharArray()[0];
        this.category5 = question.getString("category5").toCharArray()[0];
        this.category6 = question.getString("category6").toCharArray()[0];

        this.category = question.getString("category");
        this.question = question.getString("question");
        this.answer1 = question.getString("answer1");
        this.answer2 = question.getString("answer2");
        this.answer3 = question.getString("answer3");
    }

    private Questions(Parcel in) {
        id = in.readInt();
        rightAnswer = in.readInt();
        theme = in.readInt();
        category = in.readString();
        answer1 = in.readString();
        answer2 = in.readString();
        answer3 = in.readString();
        userAnswer = in.readString();
        question = in.readString();
    }

    public static final Creator<Questions> CREATOR = new Creator<Questions>() {
        @Override
        public Questions createFromParcel(Parcel in) {
            return new Questions(in);
        }

        @Override
        public Questions[] newArray(int size) {
            return new Questions[size];
        }
    };

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getId() {
        return id;
    }

    private int getNumber() {
        return number;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public int getTheme() {
        return theme;
    }

    public char getCategory4() {
        return category4;
    }

    public char getCategory5() {
        return category5;
    }

    public char getCategory6() {
        return category6;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    private String getCategory() {
        return category;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setQuestionNumber(int number) {
        this.question = String.valueOf(number) + ". " + this.question;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.getId());
        dest.writeInt(this.getRightAnswer());
        dest.writeInt(this.getTheme());
        dest.writeString(this.getCategory());
        dest.writeString(this.getAnswer1());
        dest.writeString(this.getAnswer2());
        dest.writeString(this.getAnswer3());
        dest.writeString(this.getUserAnswer());
        dest.writeString(this.getQuestion());
    }
}
