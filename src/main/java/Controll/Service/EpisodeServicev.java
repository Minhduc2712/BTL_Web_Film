package Controll.Service;

import java.util.List;

import Controll.Dao.EpisodeDAOv;
import Controll.Entity.Episode;

public class EpisodeServicev {
    private EpisodeDAOv episodeDAO;

    public EpisodeServicev() {
        this.episodeDAO = new EpisodeDAOv();
    }

    public void addEpisode(Episode episode) {
        episodeDAO.saveEpisode(episode);
    }

    public Episode getEpisode(int id) {
        return episodeDAO.getEpisode(id);
    }
    
    public List<Episode> getAllEpisodes() {
        return episodeDAO.getAllEpisodes();
    }

    public List<Episode> getEpisodesByMovie(int movieId) {
        return episodeDAO.getEpisodesByMovie(movieId);
    }

    public void deleteEpisode(int id) {
        episodeDAO.deleteEpisode(id);
    }
}
