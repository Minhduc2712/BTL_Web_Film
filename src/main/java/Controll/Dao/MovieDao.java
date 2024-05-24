package Controll.Dao;

import java.util.List;

import Controll.DTO.MovieDTO;
import Controll.Entity.Movie;

public interface MovieDao {

	Movie findById(Integer id);
	
	MovieDTO findByIdDTO(Integer id);

	Movie findByHref(String href);

	List<MovieDTO> findAll();
	
	List<MovieDTO> findAllMovies(int pageNumber, int pageSize);

	List<Movie> findAllMovieDelete();

	List<Movie> findByName(String name);
	
	Movie findBySingleName(String name);

	List<Movie> findAll(int pageNumber, int pageSize);

	List<Movie> findAllMovieDelete(int pageNumber, int pageSize);

	List<Movie> findMovieTrending();
	
	List<Movie> findMovieByCategoryName(String categoryName);

	Movie create(Movie entity);

	Movie update(Movie entity);

	Movie delete(Movie entity);

}
