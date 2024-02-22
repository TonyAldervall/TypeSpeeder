package se.ju23.typespeeder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsLetterRepo extends JpaRepository<NewsLetter, Integer> {
    List<NewsLetter> findFirstByOrderByPublishDateTimeDesc();
}
