package org.ttweb.taskmanagement.infrastrucure.repository;

import org.hibernate.query.NativeQuery;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.ttweb.taskmanagement.domain.model.board.BoardId;
import org.ttweb.taskmanagement.domain.model.card.Card;
import org.ttweb.taskmanagement.domain.model.card.CardPosition;
import org.ttweb.taskmanagement.domain.model.card.CardRepository;

import javax.persistence.EntityManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HibernateCardRepository extends HibernateSupport<Card> implements CardRepository {
    private JdbcTemplate jdbcTemplate;

    HibernateCardRepository(EntityManager entityManager, JdbcTemplate jdbcTemplate) {
        super(entityManager);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Card> findByBoardId(BoardId boardId) {
        String sql =
                "SELECT c.* FROM card c " +
                "LEFT JOIN card_list cl ON c.card_list_id = cl.id " +
                "WHERE cl.board_id = :boardId";
        NativeQuery<Card> query = getSession().createNativeQuery(sql, Card.class);
        query.setParameter("boardId", boardId.value());
        return query.list();
    }

    @Override
    public void changePositions(List<CardPosition> cardPositions) {
        String sql = "update card set card_list_id = ?, `position` = ? where id = ?";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                CardPosition cardPosition = cardPositions.get(i);
                preparedStatement.setLong(1, cardPosition.getCardListId().value());
                preparedStatement.setInt(2, cardPosition.getPosition());
                preparedStatement.setLong(3, cardPosition.getCardId().value());
            }

            @Override
            public int getBatchSize() {
                return cardPositions.size();
            }
        });
    }
}
