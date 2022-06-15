package com.csStudy.CardGame.repository;

import com.csStudy.CardGame.domain.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository("mariadb_card")
public class CardRepositoryImpl implements CardRepository {

    private final EntityManager em;

    @Autowired
    public CardRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Card> save(Card card) {
        if (card.getId() == null) {
            em.persist(card);
        }
        else {
            em.merge(card);
        }
        return Optional.ofNullable(card);
    }

    @Override
    public int delete(Card card) {
        try {
            em.remove(card);
            return 1;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteById(Long id) {
        // 예외처리 필요
        try {
            em.createQuery("delete from Card c where c.id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            return 1;
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Optional<Card> findOne(Long id) {
        return Optional.ofNullable(em.find(Card.class, id));
    }

    @Override
    public List<Card> findAll() {
        return em.createQuery("select c from Card c", Card.class)
                .getResultList();
    }

    @Override
    public List<Card> findByCategory(Long cid) {
        return em.createQuery("select c from Card c where c.category.id = :cid", Card.class)
                .setParameter("cid", cid)
                .getResultList();
    }

    @Override
    public List<Card> findByCategoryIn(List<Long> cidList) {
        return em.createQuery("select c from Card c where c.category.id in :cidList", Card.class)
                .setParameter("cidList", cidList)
                .getResultList();
    }

    @Override
    public List<Card> findByQuestionContaining(String keyword) {
        return em.createQuery("select c from Card c where c.question like :keyword", Card.class)
                .setParameter("keyword", String.format("%%%s%%", keyword))
                .getResultList();
    }

    @Override
    public List<Card> findByTagContaining(String tag) {
        return em.createQuery("select c from Card c where c.tags like :tag", Card.class)
                .setParameter("tag", String.format("%%%s%%", tag))
                .getResultList();
    }

}