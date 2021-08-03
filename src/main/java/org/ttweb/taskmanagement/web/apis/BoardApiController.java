package org.ttweb.taskmanagement.web.apis;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.ttweb.taskmanagement.domain.application.BoardService;
import org.ttweb.taskmanagement.domain.application.CardListService;
import org.ttweb.taskmanagement.domain.application.CardService;
import org.ttweb.taskmanagement.domain.application.TeamService;
import org.ttweb.taskmanagement.domain.application.commands.AddBoardMemberCommand;
import org.ttweb.taskmanagement.domain.application.commands.CreateBoardCommand;
import org.ttweb.taskmanagement.domain.common.file.FileUrlCreator;
import org.ttweb.taskmanagement.domain.model.board.Board;
import org.ttweb.taskmanagement.domain.model.board.BoardId;
import org.ttweb.taskmanagement.domain.model.card.Card;
import org.ttweb.taskmanagement.domain.model.cardlist.CardList;
import org.ttweb.taskmanagement.domain.model.team.Team;
import org.ttweb.taskmanagement.domain.model.user.User;
import org.ttweb.taskmanagement.domain.model.user.UserNotFoundException;
import org.ttweb.taskmanagement.web.payload.AddBoardMemberPayload;
import org.ttweb.taskmanagement.web.payload.CreateBoardPayload;
import org.ttweb.taskmanagement.web.results.ApiResult;
import org.ttweb.taskmanagement.web.results.BoardResult;
import org.ttweb.taskmanagement.web.results.CreateBoardResult;
import org.ttweb.taskmanagement.web.results.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BoardApiController extends AbstractBaseController {
    private BoardService boardService;
    private TeamService teamService;
    private CardListService cardListService;
    private CardService cardService;
    private FileUrlCreator fileUrlCreator;

    public BoardApiController(BoardService boardService,
                              TeamService teamService,
                              CardListService cardListService,
                              CardService cardService,
                              FileUrlCreator fileUrlCreator) {
        this.boardService = boardService;
        this.teamService = teamService;
        this.cardListService = cardListService;
        this.cardService = cardService;
        this.fileUrlCreator = fileUrlCreator;
    }

    @PostMapping("/api/boards")
    public ResponseEntity<ApiResult> createBoard(@RequestBody CreateBoardPayload payload,
                                                 HttpServletRequest request) {
        CreateBoardCommand command = payload.toCommand();
        addTriggeredBy(command, request);

        Board board = boardService.createBoard(command);
        return CreateBoardResult.build(board);
    }

    @GetMapping("/api/boards/{boardId}")
    public ResponseEntity<ApiResult> getBoard(@PathVariable("boardId") long rawBoardId) {
        BoardId boardId = new BoardId(rawBoardId);
        Board board = boardService.findById(boardId);
        if (board == null) {
            return Result.notFound();
        }
        List<User> members = boardService.findMembers(boardId);

        Team team = null;
        if (!board.isPersonal()) {
            team = teamService.findById(board.getTeamId());
        }

        List<CardList> cardLists = cardListService.findByBoardId(boardId);
        List<Card> cards = cardService.findByBoardId(boardId);

        return BoardResult.build(team, board, members, cardLists, cards, fileUrlCreator);
    }

    @PostMapping("/api/boards/{boardId}/members")
    public ResponseEntity<ApiResult> addMember(@PathVariable("boardId") long rawBoardId,
                                               @RequestBody AddBoardMemberPayload payload,
                                               HttpServletRequest request) {
        BoardId boardId = new BoardId(rawBoardId);
        Board board = boardService.findById(boardId);
        if (board == null) {
            return Result.notFound();
        }

        try {
            AddBoardMemberCommand command = payload.toCommand(boardId);
            addTriggeredBy(command, request);

            User member = boardService.addMember(command);

            ApiResult apiResult = ApiResult.blank()
                    .add("id", member.getId().value())
                    .add("shortName", member.getInitials());
            return Result.ok(apiResult);
        } catch (UserNotFoundException e) {
            return Result.failure("No user found");
        }
    }
}
