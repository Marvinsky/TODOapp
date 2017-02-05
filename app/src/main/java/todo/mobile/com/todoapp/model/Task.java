package todo.mobile.com.todoapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by mabisrror on 1/24/17.
 */

public class Task implements Serializable, Parcelable {
    private String id;
    private String title;
    private String content;
    private String category;
    private String imageUrl;
    private String color;
    private boolean checked;
    private String createdDate;

    public Task(String title, String content, String category, String imageUrl, String color, String createdDate, boolean checked) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.imageUrl = imageUrl;
        this.color = color;
        this.createdDate = createdDate;
        this.checked = checked;
    }

    protected Task(Parcel in) {
        id = in.readString();
        title = in.readString();
        content = in.readString();
        category = in.readString();
        imageUrl = in.readString();
        color = in.readString();
        checked = in.readByte() != 0;
        createdDate = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getChecked() { return checked; }

    public void setChecked(Boolean checked) { this.checked = checked; }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(category);
        parcel.writeString(imageUrl);
        parcel.writeString(color);
        parcel.writeByte((byte) (checked ? 1 : 0));
        parcel.writeString(createdDate);
    }
}
