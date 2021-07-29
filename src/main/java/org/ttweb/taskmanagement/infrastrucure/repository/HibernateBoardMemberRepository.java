package org.ttweb.taskmanagement.infrastrucure.repository;

import org.springframework.stereotype.Repository;
import org.ttweb.taskmanagement.domain.model.board.BoardMember;
import org.ttweb.taskmanagement.domain.model.board.BoardMemberRepository;

import javax.persistence.EntityManager;

@Repository
public class HibernateBoardMemberRepository extends HibernateSupport<BoardMember> implements BoardMemberRepository {
    HibernateBoardMemberRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
