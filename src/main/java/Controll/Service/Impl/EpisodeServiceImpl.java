package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.List;

import Controll.Dao.EpisodeDao;
import Controll.Dao.MovieDao;
import Controll.Dao.Impl.EpisodeDaoImpl;
import Controll.Entity.Episode;
import Controll.Entity.Movie;
import Controll.Service.EpisodeService;
import Controll.Service.MovieService;

public class EpisodeServiceImpl implements EpisodeService {

	private EpisodeDao dao;
	private MovieDao movieDao;

	public EpisodeServiceImpl() {
		dao = new EpisodeDaoImpl();
	}
	
	public MovieServiceImpl MovieService = new MovieServiceImpl();


	@Override
	public Episode findById(Integer id) {
		return dao.findById(id);
	}
	
	@Override
	public List<Episode> findByMovieId(Integer id) {
		return dao.findByMovieId(id);
	}

	@Override
	public Episode findByHref(String href) {
		return dao.findByHref(href);
	}

	@Override
	public List<Episode> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Episode> findAllMovieDelete() {
		return dao.findAllMovieDelete();
	}

	@Override
	public List<Episode> findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public List<Episode> findAll(int pageNumber, int pageSize) {
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public List<Episode> findAllMovieDelete(int pageNumber, int pageSize) {
		return dao.findAllMovieDelete(pageNumber, pageSize);
	}

	@Override
	public List<Episode> findMovieTrending() {
		return dao.findMovieTrending();
	}


	@Override
	public Episode create(String title, Integer episodeNumber,Integer episodeId, String href1, String href2, String href3, String description) {
		Movie movie = MovieService.findById(episodeId);
		Episode newEpisode = new Episode();

	    newEpisode.setMovie(movie);  // Associate the episode with the found movie
	    newEpisode.setTitle(title);
	    newEpisode.setEpisodeNumber(episodeNumber);
	    newEpisode.setHref1(href1);
	    newEpisode.setHref2(href2);
	    newEpisode.setHref3(href3);
	    newEpisode.setDescription(description);
	    newEpisode.setReleaseDate(new Timestamp(System.currentTimeMillis()));
		return dao.create(newEpisode);
	}
	
	@Override
	public Episode update(Integer id, String title, Integer episodeNumber, Integer episodeId, String href1,
			String href2, String href3, String description) {
		Episode existingEpisode = dao.findById(id);
		Movie movie = MovieService.findById(episodeId);
		if (existingEpisode != null) {
			existingEpisode.setMovie(movie);  // Associate the episode with the found movie
			existingEpisode.setTitle(title);
			existingEpisode.setEpisodeNumber(episodeNumber);
			existingEpisode.setHref1(href1);
			existingEpisode.setHref2(href2);
			existingEpisode.setHref3(href3);
			existingEpisode.setDescription(description);
			existingEpisode.setReleaseDate(new Timestamp(System.currentTimeMillis()));
		return dao.update(existingEpisode);
		}
		return existingEpisode;

	}

	@Override
	public Episode delete(Integer id) {
		Episode entity = findById(id);
		return dao.delete(entity);
	}



	

}
