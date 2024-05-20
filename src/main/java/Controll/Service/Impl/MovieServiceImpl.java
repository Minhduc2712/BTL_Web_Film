package Controll.Service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Controll.DTO.MovieDTO;
import Controll.Dao.MovieDao;
import Controll.Dao.Impl.MovieDaoImpl;
import Controll.Entity.Category;
import Controll.Entity.Movie;
import Controll.Service.CategoryService;
import Controll.Service.MovieService;



public class MovieServiceImpl implements MovieService {

	private MovieDao dao;
	
	
	private CategoryService categoryService = new CategoryServiceImpl();

	public MovieServiceImpl() {
		dao = new MovieDaoImpl();
	}

	@Override
	public Movie findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Movie findByHref(String href) {
		return dao.findByHref(href);
	}

	@Override
	public List<MovieDTO> findAllMovies() {
		return dao.findAll();
	}

	@Override
	public List<Movie> findAllMovieDelete() {
		return dao.findAllMovieDelete();
	}

	@Override
	public Movie findBySingleName(String name) {
		return dao.findBySingleName(name);
	}

	@Override
	public List<MovieDTO> findAllMovies(int pageNumber, int pageSize) {
		return dao.findAllMovies(pageNumber, pageSize);
	}

	@Override
	public List<Movie> findAllMovieDelete(int pageNumber, int pageSize) {
		return dao.findAllMovieDelete(pageNumber, pageSize);
	}

	@Override
	public List<Movie> findMovieTrending() {
		return dao.findMovieTrending();
	}

	@Override
	public Movie create(String title, String href1, String href2, String href3, String poster, String daodien, String dienvien, String[] categoryIds,
            String mota, String rawPrice, String description) {
	    Movie existingMovie = findBySingleName(title);
	    if (existingMovie == null) {
	        Movie newMovie = new Movie();
	        try {
	            String cleanPrice = rawPrice.replace(".", "");
	            int price = Integer.parseInt(cleanPrice);

	            newMovie.setTitle(title);
	            newMovie.setHref1(href1);
	            newMovie.setHref2(href2);
	            newMovie.setHref3(href3);
	            newMovie.setPoster(poster);
	            newMovie.setDaodien(daodien);
	            newMovie.setDienvien(dienvien);
	            newMovie.setMota(mota);
	            newMovie.setPrice(price);
	            newMovie.setDescription(description);
	            newMovie.setIsActive(Boolean.TRUE);
	            newMovie.setViews(0);
	            newMovie.setShares(0);
	            newMovie.setAddDate(new Timestamp(System.currentTimeMillis()));
	            
	            List<Category> categories = new ArrayList<>();
	            for (String categoryId : categoryIds) {
	                Category category = categoryService.findById(Integer.parseInt(categoryId));
	                categories.add(category);
	            }
	            newMovie.setCategories(categories);

	            return dao.create(newMovie);
	        } catch (NumberFormatException e) {
	            throw new IllegalArgumentException("Invalid price format: " + rawPrice, e);
	        }
	    }
	    return existingMovie;
	}

	@Override
	public Movie update(Integer id, String title, String href1, String href2, String href3, String daodien, String dienvien, String mota, String rawPrice,
			String description) {
		Movie videosUpdate = findById(id);

		int price = 0;
		if (rawPrice.contains(",")) {
			String cleanPrice = rawPrice.replace(",", "");
			price = Integer.parseInt(cleanPrice);
		} else {
			String cleanPrice = rawPrice.replace(".", "");
			price = Integer.parseInt(cleanPrice);
		}

		videosUpdate.setTitle(title);
		videosUpdate.setDaodien(daodien);
		videosUpdate.setDienvien(dienvien);
		videosUpdate.setMota(mota);
		videosUpdate.setPrice(price);
		videosUpdate.setDescription(description);
		videosUpdate.setIsActive(Boolean.TRUE);
		return dao.update(videosUpdate);
	}

	@Override
	public Movie updateDisabled(String title, String href, String daodien, String dienvien, String theloai, String mota,
			String rawPrice, String description) {
		Movie videosHref = findByHref(href);

		int price = 0;
		if (rawPrice.contains(",")) {
			String cleanPrice = rawPrice.replace(",", "");
			price = Integer.parseInt(cleanPrice);
		} else {
			String cleanPrice = rawPrice.replace(".", "");
			price = Integer.parseInt(cleanPrice);
		}

		videosHref.setTitle(title);
		videosHref.setDaodien(daodien);
		videosHref.setDienvien(dienvien);
		videosHref.setMota(mota);
		videosHref.setPrice(price);
		videosHref.setDescription(description);
		videosHref.setIsActive(Boolean.FALSE);
		return dao.update(videosHref);
	}

	@Override
	public Movie delete(String href) {
		Movie entity = findByHref(href);
		entity.setIsActive(Boolean.FALSE);
		return dao.update(entity);
	}

	@Override
	public Movie RestoreMovie(String href) {
		Movie movie = findByHref(href);
		movie.setIsActive(Boolean.TRUE);
		return dao.update(movie);
	}

	@Override
	public Movie DeleteMovieRestore(String href) {
		Movie movie = findByHref(href);
		return dao.delete(movie);
	}

	@Override
	public Movie update(String title, String href, String daodien, String dienvien, String mota, String rawPrice,
			String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie update(Integer id, String title, String href1, String href2, String href3, String daodien,
			String dienvien, String[] categoryIds, String mota, String price, String description) {
		// TODO Auto-generated method stub
		return null;
	}

}
