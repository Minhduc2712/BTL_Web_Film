package Controll.Dao;
import Controll.Entity.Episode;
import Controll.Util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EpisodeDAOv {
    private SessionFactory sessionFactory;

    public EpisodeDAOv() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void saveEpisode(Episode episode) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(episode);
        session.getTransaction().commit();
        session.close();
    }

    public Episode getEpisode(int id) {
        Session session = sessionFactory.openSession();
        Episode episode = session.get(Episode.class, id);
        session.close();
        return episode;
    }
    
    @SuppressWarnings("unchecked")
    public List<Episode> getAllEpisodes() {
        Session session = sessionFactory.openSession();
        List<Episode> episodes = session.createQuery("from Episode").list();
        session.close();
        return episodes;
    }

    public List<Episode> getEpisodesByMovie(int movieId) {
        Session session = sessionFactory.openSession();
        List<Episode> episodes = session.createQuery("from Episode where movie_id = :movieId", Episode.class)
                .setParameter("movieId", movieId)
                .list();
        session.close();
        return episodes;
    }

    public void deleteEpisode(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Episode episode = session.get(Episode.class, id);
        if (episode != null) {
            session.delete(episode);
        }
        session.getTransaction().commit();
        session.close();
    }
}
