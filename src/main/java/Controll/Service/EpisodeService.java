package Controll.Service;

import java.util.List;

import Controll.Entity.Episode;

public interface EpisodeService {

	Episode findById(Integer id);
	
	List<Episode> findByMovieId(Integer id);

	Episode findByHref(String href);

	List<Episode> findAll();

	List<Episode> findAllMovieDelete();

	List<Episode> findByName(String name);

	List<Episode> findMovieTrending();

	List<Episode> findAll(int pageNumber, int pageSize);

	List<Episode> findAllMovieDelete(int pageNumber, int pageSize);

	Episode create(String title, Integer episodeNumber,Integer episodeId, String href1, String href2, String href3, String description);

	Episode update(Integer id, String title, Integer episodeNumber,Integer episodeId, String href1, String href2, String href3, String description);
	
	Episode delete(Integer id);
	
}
