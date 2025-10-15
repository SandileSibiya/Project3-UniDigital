package za.ac.cputdigitalcard.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cputdigitalcard.domain.Card;

import java.sql.Blob;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class CardServiceTest {

    @Autowired
    private CardService cardService;

    private Card card = new Card.Builder()
           // .setCardId(1001L)
            .setTitle("MR.")
            .setInitials("SE")
            .setSurname("Sibiya")
            .setCourse("PRM")
            .setDate("Fri September 2025")
            .setPhoto(null)
            .setStudentId(300975623L)
            .build();

    @Test
    void create() {
        Card savedCard = cardService.create(card);
        assertNotNull(savedCard);
        System.out.println("Saved Card: " + savedCard);
    }

//    @Test
//    void read() {
//        Card readCard = cardService.read(card.getCardId());
//        assertNotNull(readCard);
//        System.out.println("Read Card: " + readCard);
//    }
    @Test
    void searchByCardId() {
        Long cardId = 152L;

        Card readCard = cardService.searchByCardId(cardId);
     //   assertNotNull(readCard);
    //    System.out.println("Read Card: " + readCard);
    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void getCards() {
//    }
}