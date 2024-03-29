package com.example.forum.repositories;

import com.example.forum.exceptions.EntityNotFoundException;
import com.example.forum.models.Post;
import com.example.forum.models.Reaction;
import com.example.forum.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReactionRepositoryImpl implements ReactionRepository{
    private final SessionFactory sessionFactory;

    @Autowired
    public ReactionRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Reaction reaction) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(reaction);
            session.getTransaction().commit();
        }
    }

    @Override
    public Reaction update(Reaction reaction) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(reaction);
            session.getTransaction().commit();
        }
        return reaction;
    }

    @Override
    public void delete(int reactionId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(get(reactionId));
            session.getTransaction().commit();
        }
    }

    @Override
    public void clearAll(Post post) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("DELETE FROM Reaction WHERE post = :post");
            session.beginTransaction();
            query.setParameter("post", post);
            query.executeUpdate();

            session.getTransaction().commit();
            }
    }

    @Override
    public Reaction get(Post post, User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Reaction> query = session.createQuery(
                    "from Reaction where post = :post_id and createdBy = :created_by", Reaction.class
            );
            query.setParameter("post_id", post);
            query.setParameter("created_by", user);

            List<Reaction> result = query.list();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("reactions");
            }

            return result.get(0);
        }
    }

    @Override
    public Reaction get(int id) {
        try (
                Session session = sessionFactory.openSession()
        ){
            Reaction reaction = session.get(Reaction.class,id);
            if (reaction==null){
                throw new EntityNotFoundException("Reaction",id);
            }
            return reaction;

        }
    }

    @Override
    public long getUpVotedPostCount(Post post) {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(*) FROM Reaction r WHERE r.isUpVoted=true AND post = :post_id"
                    , Long.class);
            query.setParameter("post_id",post);

           return query.uniqueResult();
    }

}}
