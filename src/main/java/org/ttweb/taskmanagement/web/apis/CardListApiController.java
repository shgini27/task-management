package org.ttweb.taskmanagement.web.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ttweb.taskmanagement.domain.application.CardListService;
import org.ttweb.taskmanagement.domain.application.commands.AddCardListCommand;
import org.ttweb.taskmanagement.domain.application.commands.ChangeCardListPositionsCommand;
import org.ttweb.taskmanagement.domain.model.cardlist.CardList;
import org.ttweb.taskmanagement.web.payload.AddCardListPayload;
import org.ttweb.taskmanagement.web.payload.ChangeCardListPositionsPayload;
import org.ttweb.taskmanagement.web.results.AddCardListResult;
import org.ttweb.taskmanagement.web.results.ApiResult;
import org.ttweb.taskmanagement.web.results.Result;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CardListApiController extends AbstractBaseController{
    private CardListService cardListService;

    public CardListApiController(CardListService cardListService) {
        this.cardListService = cardListService;
    }

    @PostMapping("/api/card-lists")
    public ResponseEntity<ApiResult> addCardList(@RequestBody AddCardListPayload payload,
                                                 HttpServletRequest request) {
        AddCardListCommand command = payload.toCommand();
        addTriggeredBy(command, request);

        CardList cardList = cardListService.addCardList(command);
        return AddCardListResult.build(cardList);
    }

    @PostMapping("/api/card-lists/positions")
    public ResponseEntity<ApiResult> changeCardListPositions(@RequestBody ChangeCardListPositionsPayload payload,
                                                             HttpServletRequest request) {
        ChangeCardListPositionsCommand command = payload.toCommand();
        addTriggeredBy(command, request);

        cardListService.changePositions(command);
        return Result.ok();
    }
}
