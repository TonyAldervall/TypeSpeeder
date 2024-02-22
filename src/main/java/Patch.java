import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Patch {
    private String patchVersion;
    private LocalDateTime realeaseDateTime;

    public Patch(String patchVersion, LocalDateTime realeaseDateTime) {
        realeaseDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.patchVersion = patchVersion;
        this.realeaseDateTime = realeaseDateTime;

    }

    public String getPatchVersion(){
        return patchVersion;
    }
    public String getRealeaseDateTime() {
        return realeaseDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
