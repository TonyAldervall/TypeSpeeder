import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewsLetter {
    private String content;
    private LocalDateTime publishDateTime;

    public NewsLetter(String content, LocalDateTime publishDateTime) {
        this.content = content;
        this.publishDateTime = publishDateTime;
    }

    public String getContent() {
        return content;
    }

    public String getPublishDateTime() {
        return publishDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
