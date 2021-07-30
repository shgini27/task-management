package org.ttweb.taskmanagement.web.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ttweb.taskmanagement.domain.application.CardService;
import org.ttweb.taskmanagement.domain.common.security.CurrentUser;
import org.ttweb.taskmanagement.domain.model.card.Card;
import org.ttweb.taskmanagement.domain.model.user.SimpleUser;
import org.ttweb.taskmanagement.web.payload.AddCardPayload;
import org.ttweb.taskmanagement.web.payload.ChangeCardPositionPayload;
import org.ttweb.taskmanagement.web.results.AddCardResult;
import org.ttweb.taskmanagement.web.results.ApiResult;
import org.ttweb.taskmanagement.web.results.Result;
import org.ttweb.taskmanagement.web.updater.CardUpdater;

@Controller
public class CardApiController {
    private CardService cardService;
    private CardUpdater cardUpdater;

    @Autowired
    public CardApiController(CardService cardService, CardUpdater cardUpdater){
        this.cardService = cardService;
        this.cardUpdater = cardUpdater;
    }

    @PostMapping("/api/cards")
    public ResponseEntity<ApiResult> addCard(
            @RequestBody AddCardPayload payload,
            @CurrentUser SimpleUser currentUser){
        Card card = cardService.addCard(payload.toCommand(currentUser.getUserId()));
        cardUpdater.onCardAdded(payload.getBoardId(), card);
        return AddCardResult.build(card);
    }

    @PostMapping("/api/cards/positions")
    public ResponseEntity<ApiResult> changeCardPositions(@RequestBody ChangeCardPositionPayload payload){
        cardService.changePositions(payload.toCommand());
        return Result.ok();
    }
}
