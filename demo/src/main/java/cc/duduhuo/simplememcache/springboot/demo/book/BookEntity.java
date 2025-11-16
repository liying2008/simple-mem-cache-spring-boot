package cc.duduhuo.simplememcache.springboot.demo.book;

public class BookEntity {
    private String code;
    private String title;
    private String author;

    public BookEntity() {
    }

    public BookEntity(String code, String title, String author) {
        this.code = code;
        this.title = title;
        this.author = author;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
            "code='" + code + '\'' +
            ", title='" + title + '\'' +
            ", author='" + author + '\'' +
            '}';
    }
}
