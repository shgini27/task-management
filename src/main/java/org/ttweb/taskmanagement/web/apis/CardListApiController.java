package org.ttweb.taskmanagement.web.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ttweb.taskmanagement.domain.application.CardListService;
import org.ttweb.taskmanagement.domain.common.security.CurrentUser;
import org.ttweb.taskmanagement.domain.model.cardlist.CardList;
import org.ttweb.taskmanagement.domain.model.user.SimpleUser;
import org.ttweb.taskmanagement.web.payload.AddCardListPayload;
import org.ttweb.taskmanagement.web.payload.ChangeCardListPositionsPayload;
import org.ttweb.taskmanagement.web.results.AddCardListResult;
import org.ttweb.taskmanagement.web.results.ApiResult;
import org.ttweb.taskmanagement.web.results.Result;

@Controller
public class CardListApiController {
    private CardListService cardListService;

    @Autowired
    public CardListApiController(CardListService cardListService){
        this.cardListService = cardListService;
    }

    @PostMapping("/api/card-lists")
    public ResponseEntity<ApiResult> addCardList(
            @RequestBody AddCardListPayload payload,
            @CurrentUser SimpleUser currentUser){
        CardList cardList = cardListService.addCardList(payload.toCommand(currentUser.getUserId()));
        return AddCardListResult.build(cardList);
    }

    @PostMapping("/api/card-list/positions")
    public ResponseEntity<ApiResult> changeCardListPositions(@RequestBody ChangeCardListPositionsPayload payload){
        cardListService.changePositions(payload.toCommand());
        return Result.ok();
    }
}
