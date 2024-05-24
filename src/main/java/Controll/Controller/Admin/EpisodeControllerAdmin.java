package Controll.Controller.Admin;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import Controll.Entity.Category;
import Controll.Entity.Episode;
import Controll.Entity.Movie;
import Controll.Service.EpisodeService;
import Controll.Service.EpisodeServicev;
import Controll.Service.MovieService;
import Controll.Service.Impl.EpisodeServiceImpl;
import Controll.Service.Impl.MovieServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class EpisodeControllerAdmin
 */
@WebServlet({"/episodesingleviews","/episodeAllviews","/episodeadd","/episodeedit","/episodedelete"})
public class EpisodeControllerAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
//	private EpisodeServicev episodeService;
//    private MovieService movieService;
    private MovieService movieService = new MovieServiceImpl();
    private EpisodeService episodeService = new EpisodeServiceImpl();
//    @Override
//    public void init() {
//        episodeService = new EpisodeServicev();
//    }

    
    public EpisodeControllerAdmin() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String movieIdParam = request.getParameter("movieId");

        if (movieIdParam == null || movieIdParam.isEmpty()) {
            switch (path) {
                case "/episodeAllviews":
                    doGetViewsAllEpisode(request, response);
                    break;
                case "/episodeadd":
                    doGetAddEpisode(request, response);
                    break;
                case "/episodeedit":
                    doGetEditEpisode(request, response);
                    break;
                case "/episodedelete":
                    doGetDeleteEpisode(request, response);
                    break;
            }
            return;
        }

        Integer movieId = Integer.parseInt(movieIdParam);
        
        switch (path) {
            case "/episodesingleviews":
                doGetViewsEpisodePerMovie(request, movieId, response);
                break;
        }

        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();

		switch (path) {
        case "/episodeadd":
            doPostNewEpisode(request, response);
            break;
        case "/episodeedit":
            doPostEditEpisode(request, response);
            break;
        case "/episodedelete":
            doPostDeleteEpisode(request, response);
            break;
		}
    }	
	
    private void doGetDeleteEpisode(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void doGetEditEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("Id"));
		List<Movie> movies;
		Episode episode;
        movies = movieService.findMovieByCategoryName("phim bộ");
        episode = episodeService.findById(id);
        System.out.print(movies);
        request.setAttribute("movies", movies);
        request.setAttribute("episode", episode);
        request.getRequestDispatcher("/views/admin/EditEpisode.jsp").forward(request, response);
		
	}

	private void doGetAddEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Movie> movies;
        movies = movieService.findMovieByCategoryName("phim bộ");
        System.out.print(movies);
        request.setAttribute("movies", movies);
        request.getRequestDispatcher("/views/admin/newEpisode.jsp").forward(request, response);
		
	}
		
		private void doGetViewsEpisodePerMovie(HttpServletRequest request, Integer movieId, HttpServletResponse response) throws ServletException, IOException {
//	        int movieId = Integer.parseInt(request.getParameter("Id"));
	        List<Episode> episodes = episodeService.findByMovieId(movieId);   
	        request.setAttribute("episodes", episodes);
	        request.getRequestDispatcher("/views/admin/add-episode.jsp").forward(request, response);
	    }
		
		private void doGetViewsAllEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<Episode> episodes = episodeService.findAll();
			request.setAttribute("episodes", episodes);
			request.getRequestDispatcher("/views/admin/add-episode.jsp").forward(request, response);
		}
		
		
		
		
		
		
		private void doPostDeleteEpisode(HttpServletRequest request, HttpServletResponse response) throws IOException {
			Integer id = Integer.parseInt(request.getParameter("Id"));
			Episode episode= episodeService.delete(id);

	        if (episode != null) {
	            response.sendRedirect("episodeAllviews");
	        }
		
		}

		private void doPostEditEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			Integer id = Integer.parseInt(request.getParameter("Id"));
			Episode episode = episodeService.findById(id);
	        List<Movie> movies;

	        movies = movieService.findMovieByCategoryName("phim bộ");
	        
	        String title = request.getParameter("title");
	        Integer episodeNumber =Integer.parseInt(request.getParameter("episodeNumber"));
	        String href1 = request.getParameter("href1");
	        String href2 = request.getParameter("href2");
	        String href3 = request.getParameter("href3");
	        Integer episodeTitle =Integer.parseInt(request.getParameter("movieTitle"));
	        String noted = request.getParameter("noted");

	        
	        
	        if (isValid(title, episodeNumber, episodeTitle, href1, href2, href3, noted)) {
	            try {
	                Episode episodeCreate = episodeService.update(id, title, episodeNumber, episodeTitle, href1, href2, href3, noted);
	                if (episodeCreate != null) {
	                    session.setAttribute("addMovieSuccess", true);
	                    response.sendRedirect("episodeAllviews");
	                    return;
	                } else {
	                    session.setAttribute("addMovieSuccess", false);
	                    response.sendRedirect("episodeAllviews");
	                    return;
	                }
	            } catch (Exception e) {
	                e.printStackTrace(); // Log the error for debugging
	                request.setAttribute("error", "An error occurred while creating the movie.");
	            }
	        } else {
	            request.setAttribute("error", "All fields are required.");
	        }
	        
	        request.setAttribute("movies", movies);
	        request.getRequestDispatcher("/views/admin/EditEpisode.jsp").forward(request, response);
		}
		
		private boolean isValid(String title, Integer episodeNumber, Integer episodeTitle, String href1, String href2, String href3, String noted) {
		    return title != null && !title.isEmpty() &&
		           episodeNumber != null && 
		           episodeTitle != null &&
		           href1 != null && !href1.isEmpty() &&
		           href2 != null && !href2.isEmpty() &&
		           href3 != null && !href3.isEmpty() &&
		           noted != null && !noted.isEmpty();
		}

		private void doPostNewEpisode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();

	        String title = request.getParameter("title");
	        Integer episodeNumber =Integer.parseInt(request.getParameter("episodeNumber"));
	        String href1 = request.getParameter("href1");
	        String href2 = request.getParameter("href2");
	        String href3 = request.getParameter("href3");
	        Integer episodeTitle =Integer.parseInt(request.getParameter("movieTitle"));
	        String noted = request.getParameter("noted");

	        
	        
	        if (isValid(title, episodeNumber, episodeTitle, href1, href2, href3, noted)) {
	            try {
	                Episode episodeCreate = episodeService.create(title, episodeNumber, episodeTitle, href1, href2, href3, noted);
	                if (episodeCreate != null) {
	                    session.setAttribute("addMovieSuccess", true);
	                    response.sendRedirect("episodeAllviews");
	                    return;
	                } else {
	                    session.setAttribute("addMovieSuccess", false);
	                    response.sendRedirect("episodeAllviews");
	                    return;
	                }
	            } catch (Exception e) {
	                e.printStackTrace(); // Log the error for debugging
	                request.setAttribute("error", "An error occurred while creating the movie.");
	            }
	        } else {
	            request.setAttribute("error", "All fields are required.");
	        }

	        request.getRequestDispatcher("/views/admin/newEpisode.jsp").forward(request, response);

		
		}   
}
       




