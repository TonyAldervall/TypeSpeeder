package se.ju23.typespeeder;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "patch")
public class Patch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String patchVersion;
    private LocalDateTime realeaseDateTime;

    public Patch(String patchVersion, LocalDateTime realeaseDateTime) {
        this.patchVersion = patchVersion;
        this.realeaseDateTime = realeaseDateTime;

    }

    public Patch() {

    }

    public String getPatchVersion(){
        return patchVersion;
    }
    public String getRealeaseDateTime() {
        return realeaseDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return "Patch: " + getPatchVersion();
    }
}
