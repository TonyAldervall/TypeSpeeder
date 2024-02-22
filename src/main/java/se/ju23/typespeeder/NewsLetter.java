package se.ju23.typespeeder;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static se.ju23.typespeeder.TypeSpeederApplication.*;

@Entity
@Table(name = "newsletter")
public class NewsLetter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private LocalDateTime publishDateTime;

    public NewsLetter(String content, LocalDateTime publishDateTime) {
        this.content = content;
        this.publishDateTime = publishDateTime;
    }
    public NewsLetter() {

    }

    public String getContent() {
        return content;
    }

    public String getPublishDateTime() {
        return publishDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    @Override
    public String toString() {
        return getContent();
    }
}
