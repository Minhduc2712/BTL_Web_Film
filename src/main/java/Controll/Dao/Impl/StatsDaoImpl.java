package Controll.Dao.Impl;

import java.util.ArrayList;
import java.util.List;

import Controll.DTO.MovieLikedInfo;
import Controll.Dao.AbstactDao;
import Controll.Dao.StatsDao;

public class StatsDaoImpl extends AbstactDao<Object[]> implements StatsDao {

	@Override
	public List<MovieLikedInfo> findMovieLikeInfo() {
	    String sql = "SELECT v.title, v.href1 AS href, v.daodien, v.isActive, " +
	                 "SUM(CASE WHEN h.isLiked = TRUE THEN 1 ELSE 0 END) AS totalLike " +
	                 "FROM movie v " +
	                 "JOIN history h ON h.movieId = v.id " +
	                 "WHERE v.isActive = 1 " +
	                 "GROUP BY v.title, v.href1, v.daodien, v.isActive " +
	                 "ORDER BY totalLike DESC";
	    List<Object[]> objects = findManyByNativeQuery(sql);
	    List<MovieLikedInfo> result = new ArrayList<>();

	    objects.forEach(object -> {
	        MovieLikedInfo likeInfo = setDataMovieLikedInfo(object);
	        result.add(likeInfo);
	    });
	    return result;
	}

	private MovieLikedInfo setDataMovieLikedInfo(Object[] object) {
		MovieLikedInfo likeInfo = new MovieLikedInfo();
		likeInfo.setTitle((String) object[0]);
		likeInfo.setHref((String) object[1]);
		likeInfo.setDaodien((String) object[2]);
		likeInfo.setTotalLike(object[4] == null ? 0 : (Integer) object[4]);
		likeInfo.setIsActive((Boolean) object[3]);
		return likeInfo;
	}

}
