package Controll.DTO;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import Controll.Entity.Movie;

public class EpisodeDTO {
	
	    private Integer id;

	    private int episodeNumber;

	    private String title;

	    private String description;

	    private String href1;
	    
	    private String href2;
	    
	    private String href3;

	    private Movie movie;

	    private Timestamp releaseDate;
	    
	    public EpisodeDTO(int id, String title, String href1, String href2, String href3, int episodeNumber) {
	        this.id = id;
	        this.title = title;
	        this.href1 = href1;
	        this.href2 = href2;
	        this.href3 = href3;
	        this.episodeNumber = episodeNumber;
	    }
	    
	    public EpisodeDTO(int id , int episodeNumber , String title, String href1, String href2, String href3) {
	    	this.id = id;
	        this.title = title;
	        this.href1 = href1;
	        this.href2 = href2;
	        this.href3 = href3;
	        this.episodeNumber = episodeNumber;
		}

		public EpisodeDTO() {
			// TODO Auto-generated constructor stub
		}

		public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public int getEpisodeNumber() {
	        return episodeNumber;
	    }

	    public void setEpisodeNumber(int episodeNumber) {
	        this.episodeNumber = episodeNumber;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public String getHref1() {
			return href1;
		}

		public void setHref1(String href1) {
			this.href1 = href1;
		}
		
		public String getHref2() {
			return href2;
		}

		public void setHref2(String href2) {
			this.href2 = href2;
		}
		
		public String getHref3() {
			return href3;
		}

		public void setHref3(String href3) {
			this.href3 = href3;
		}

	    public Movie getMovie() {
	        return movie;
	    }

	    public void setMovie(Movie movie) {
	        this.movie = movie;
	    }

	    public Timestamp getReleaseDate() {
	        return releaseDate;
	    }

	    public void setReleaseDate(Timestamp releaseDate) {
	        this.releaseDate = releaseDate;
	    }

}
