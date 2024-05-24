package Controll.Dao.Impl;

import java.util.List;

import Controll.DTO.MovieDTO;
import Controll.Dao.AbstactDao;
import Controll.Dao.MovieDao;
import Controll.Entity.Movie;

public class MovieDaoImpl extends AbstactDao<Movie> implements MovieDao {

	private static final String String = null;

	@Override
	public Movie findById(Integer id) {
		return super.findById(Movie.class, id);
	}
	
	@Override
	public MovieDTO findByIdDTO(Integer id) {
		return super.findAllEpisodeById( id);
	}

	@Override
	public Movie findByHref(String href) {
		String sql = "SELECT o FROM Video o WHERE o.href = ?0";
		return super.findOne(Movie.class, sql, href);
	}

	@Override
	public List<MovieDTO> findAll() {
		return super.findAllMovies( true);
	}
	
	@Override
	public List<Movie> findAll(int pageNumber, int pageSize) {
		return super.findAll(Movie.class, true, pageNumber, pageSize);
	}

	@Override
	public List<Movie> findAllMovieDelete() {
		return super.findAllMovieDelete(Movie.class, true);
	}

	@Override
	public List<Movie> findByName(String name) {
		String sql = "SELECT o FROM Movie o WHERE o.title LIKE ?0";
		return super.findMany(Movie.class, sql, "%" + name + "%");
	}
		
	@Override
	public Movie findBySingleName(String name) {
		String sql = "SELECT o FROM Movie o WHERE o.title = ?0";
		return super.findOne(Movie.class, sql, name);
	}

	@Override
	public List<Movie> findAllMovieDelete(int pageNumber, int pageSize) {
		return super.findAllMovieDelete(Movie.class, true, pageNumber, pageSize);
	}

	@Override
	public List<Movie> findMovieTrending() {
		String jpql = "SELECT o FROM Movie o WHERE o.isActive = 1 ORDER BY o.addDate DESC";
		return super.findManyMaxResult(Movie.class, jpql, 4);
	}

	@Override
	public List<Movie> findMovieByCategoryName(String categoryName) {
		return super.findByCategoryName(categoryName);
	}

	

}
