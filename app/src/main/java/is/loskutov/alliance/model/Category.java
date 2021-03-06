package is.loskutov.alliance.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
    private int id, category;
    private String name;

    public Category(int id, String name, int category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Category(int id, int category) {
        this.id = id;
        this.category = category;
    }

    private Category(Parcel in) {
        id = in.readInt();
        name = in.readString();
        category = in.readInt();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.getId());
        dest.writeString(this.getName());
        dest.writeInt(this.getCategory());
    }
}
