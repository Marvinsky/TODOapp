package todo.mobile.com.todoapp.model;

/**
 * Created by mabisrror on 1/24/17.
 */

public class Task {
    private String id;
    private String title;
    private String content;
    private String category;

    public Task(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
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
}
