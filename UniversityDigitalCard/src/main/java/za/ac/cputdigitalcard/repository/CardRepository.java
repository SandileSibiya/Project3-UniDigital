package za.ac.cputdigitalcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cputdigitalcard.domain.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
    Card findByCardId(Long cardId);
    Card findByStudentId(Long studentId);
}
