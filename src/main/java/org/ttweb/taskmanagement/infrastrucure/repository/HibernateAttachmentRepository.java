package org.ttweb.taskmanagement.infrastrucure.repository;

import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import org.ttweb.taskmanagement.domain.model.attachment.Attachment;
import org.ttweb.taskmanagement.domain.model.attachment.AttachmentRepository;
import org.ttweb.taskmanagement.domain.model.card.CardId;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class HibernateAttachmentRepository extends HibernateSupport<Attachment> implements AttachmentRepository {
    HibernateAttachmentRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Attachment> findAttachments(CardId cardId) {
        String sql = "SELECT a.* FROM attachment a WHERE a.card_id = :cardId order by id desc";
        NativeQuery<Attachment> query = getSession().createNativeQuery(sql, Attachment.class);
        query.setParameter("cardId", cardId.value());
        return query.list();
    }
}
