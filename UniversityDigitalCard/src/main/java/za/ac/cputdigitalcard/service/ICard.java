package za.ac.cputdigitalcard.service;

import za.ac.cputdigitalcard.domain.Card;

import java.util.List;

public interface ICard extends IService <Card, Long> {
    List<Card> getCards();
    Card searchByCardId(Long cardId);
}
