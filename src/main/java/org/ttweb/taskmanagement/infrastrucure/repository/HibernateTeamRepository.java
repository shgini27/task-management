package org.ttweb.taskmanagement.infrastrucure.repository;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.ttweb.taskmanagement.domain.model.team.Team;
import org.ttweb.taskmanagement.domain.model.team.TeamId;
import org.ttweb.taskmanagement.domain.model.team.TeamRepository;
import org.ttweb.taskmanagement.domain.model.user.UserId;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateTeamRepository extends HibernateSupport<Team> implements TeamRepository {
    HibernateTeamRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Team> findTeamsByUserId(UserId userId) {
        String sql =
                " SELECT t.* FROM team t WHERE t.user_id = :userId " +
                        " UNION " +
                        " ( " +
                        "   SELECT t.* FROM team t, board b, board_member bm " +
                        "   WHERE t.id = b.team_id AND bm.board_id = b.id AND bm.user_id = :userId " +
                        " ) ";
        NativeQuery<Team> query = getSession().createNativeQuery(sql, Team.class);
        query.setParameter("userId", userId.value());
        return query.list();
    }

    @Override
    public Team findById(TeamId teamId) {
        Query<Team> query = getSession().createQuery("from Team where id = :id", Team.class);
        query.setParameter("id", teamId.value());
        return query.uniqueResult();
    }
}
