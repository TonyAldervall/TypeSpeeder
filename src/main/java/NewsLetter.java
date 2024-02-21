import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewsLetter {
    private String content;
    public LocalDateTime publishDateTime;

    public NewsLetter() {
        this.content = "This is a test newsletter to see if this will be long enough to pass the test in the NewsLetterTest class. I hope this is long enough" +
                " please how much more of this.";
        this.publishDateTime = LocalDateTime.now();
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPublishDateTime() {
        return publishDateTime;
    }
}
