package todo.mobile.com.todoapp.model;

/**
 * Created by mabisrror on 1/24/17.
 */

public class Task {
    private String id;
    private String title;
    private String content;
    private String category;
    private String imageUrl;
    private boolean checked;

    public Task(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.checked = false;
    }

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
}
