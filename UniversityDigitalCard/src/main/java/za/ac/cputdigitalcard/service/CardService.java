package za.ac.cputdigitalcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cputdigitalcard.domain.Card;
import za.ac.cputdigitalcard.repository.CardRepository;

import java.util.List;

@Service
public class CardService implements ICard {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    @Override
    public Card create(Card card) {
        if (cardRepository.findByCardId(card.getCardId()) != null & cardRepository.findByStudentId(card.getStudentId()) != null) {
            throw new RuntimeException("Card already exists");
        }
        Card createdCard =  this.cardRepository.save(card);
        System.out.println("Created card: " + createdCard.getCardId());
        return createdCard;
    }

    @Override
    public Card read(Long aLong) {
        if (cardRepository.findByStudentId(aLong) == null) {
            throw new RuntimeException("Card not found");
        }
        System.out.println("Card found: ");
        return this.cardRepository.findByStudentId(aLong);
    }

    @Override
    public Card update(Card card) {
        return null;
    }

    @Override
    public Card delete(Long aLong) {
        return null;
    }
    @Override
    public List<Card> getCards() {
        return List.of();
    }

    @Override
    public Card searchByCardId(Long cardId) {
        if (cardId == null) {
            throw new RuntimeException("Card ID cannot be null");
        }

        Card foundcard = cardRepository.findByCardId(cardId);
        if (foundcard == null) {
            throw new RuntimeException("Card not found for ID: " + cardId);
        }
        return foundcard;

    }
}
