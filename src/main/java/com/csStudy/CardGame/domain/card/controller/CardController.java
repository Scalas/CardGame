package com.csStudy.CardGame.domain.card.controller;

import com.csStudy.CardGame.domain.card.dto.DetailCard;
import com.csStudy.CardGame.domain.card.dto.NewCard;
import com.csStudy.CardGame.domain.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* 카드 관련 API */
@RestController
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // 전체 카드 또는 조건에 맞는 카드의 리스트를 반환하는 API
    @GetMapping("/cards")
    public List<DetailCard> getCards(@RequestParam(required = false) List<Long> category) {
        if (category != null) {
            return cardService.findCardsByCategories(category);
        }
        return cardService.getAllCards();
    }

    // 카드 추가
    @PostMapping("/cards")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addCard(@RequestBody NewCard newCard){
        cardService.addCard(newCard);
    }

    // 카드 수정
    @PutMapping("/cards")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void editCard(@RequestBody DetailCard detailCard){
        cardService.editCard(detailCard);
    }

    // 카드 삭제
    @DeleteMapping("/cards/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }

}
