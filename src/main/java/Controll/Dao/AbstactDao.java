package Controll.Dao;

import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;

import Controll.DTO.MovieDTO;
import Controll.Entity.Category;
import Controll.Entity.Movie;
import Controll.Util.JPAUtil;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AbstactDao<T> {

//	tìm bằng id
	public T findById(Class<T> clazz, Integer id) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			return entityManager.find(clazz, id);
		} finally {
			entityManager.close();
		}
	}

//	tìm tất cả
	public List<T> findAll(Class<T> clazz, boolean existIsActive) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			if (existIsActive) {
				sql.append(" WHERE isActive = 1 ORDER BY o.addDate DESC");
			}
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}
	
    public List<MovieDTO> findAllMovies( boolean existIsActive) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT m FROM Movie m");
            if (existIsActive) {
                sql.append(" WHERE m.isActive = 1");
            }
            TypedQuery<Movie> query = entityManager.createQuery(sql.toString(), Movie.class);
            List<Movie> movies = query.getResultList();
            return movies.stream().map(this::convertToDTO).collect(Collectors.toList());
        } finally {
            entityManager.close();
        }
    }
	
	public List<T> findAll(Class<T> clazz){
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}
	
	



    public List<MovieDTO> findAllMovies(int pageNumber, int pageSize) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT DISTINCT m FROM Movie m LEFT JOIN FETCH m.categories c");

            TypedQuery<Movie> query = entityManager.createQuery(sql.toString(), Movie.class);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);

            List<Movie> movies = query.getResultList();

            // Use Hibernate.initialize to ensure all collections are fetched
            movies.forEach(movie -> {
                Hibernate.initialize(movie.getCategories());
                Hibernate.initialize(movie.getEpisodes());
                // Initialize other lazy-loaded collections if needed
            });

            return movies.stream().map(this::convertToDTO).collect(Collectors.toList());
        } finally {
            entityManager.close();
        }
    }

	
	
	
    private MovieDTO convertToDTO(Movie movie) {
        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setHref1(movie.getHref1());
        dto.setHref2(movie.getHref2());
        dto.setHref3(movie.getHref3());
        dto.setPoster(movie.getPoster());
        dto.setViews(movie.getViews());
        dto.setShares(movie.getShares());
        dto.setDescription(movie.getDescription());
        dto.setDaodien(movie.getDaodien());
        dto.setDienvien(movie.getDienvien());
        dto.setMota(movie.getMota());
        dto.setPrice(movie.getPrice());
        dto.setIsActive(movie.getIsActive());
        dto.setAddDate(movie.getAddDate());
        dto.setHoadon(movie.getHoadon());
        dto.setEpisodes(movie.getEpisodes());

        if (movie.getCategories() != null) {
            List<String> categoryNames = movie.getCategories().stream()
                .map(Category::getName)
                .collect(Collectors.toList());
            dto.setCategoryNames(categoryNames);
        }

        return dto;
    }


//	tìm tất cả và phân trang
	public List<T> findAll(Class<T> clazz, boolean existIsActive, int pageNumber, int pageSize) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			if (existIsActive) {
				sql.append(" WHERE isActive = 1 ORDER BY o.addDate DESC");
			}
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}
	


	public List<T> findAllUser(Class<T> clazz, boolean existIsActive, int pageNumber, int pageSize) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

//	tìm tất cả cho user và loại bỏ admin
	public List<T> findAllForUser(Class<T> clazz, boolean existIsActive) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			if (existIsActive) {
				sql.append(" WHERE isActive = 1 AND isAdmin = 0");
			}
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

//	tìm tất cả cho user và loại bỏ admin và phân trang
	public List<T> findAllForUser(Class<T> clazz, boolean existIsActive, int pageNumber, int pageSize) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			if (existIsActive) {
				sql.append(" WHERE isActive = 1 AND isAdmin = 0");
			}
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

//	tìm tất cả cho video đã ngưng hoạt động
	public List<T> findAllMovieDelete(Class<T> clazz, boolean existIsActive) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			if (existIsActive) {
				sql.append(" WHERE isActive = 0 ORDER BY o.addDate DESC");
			}
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

//	tìm tất cả cho video đã ngưng hoạt động và phân trang
	public List<T> findAllMovieDelete(Class<T> clazz, boolean existIsActive, int pageNumber, int pageSize) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			String entityName = clazz.getSimpleName();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT o FROM ").append(entityName).append(" o");
			if (existIsActive) {
				sql.append(" WHERE isActive = 0 ORDER BY o.addDate DESC");
			}
			TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

//	tìm một dữ liệu
	public T findOne(Class<T> clazz, String sql, Object... params) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			TypedQuery<T> query = entityManager.createQuery(sql, clazz);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			List<T> result = query.getResultList();
			return result.isEmpty() ? null : result.get(0);
		} finally {
			entityManager.close();
		}
	}

//	tìm một list dữ liệu
	public List<T> findMany(Class<T> clazz, String sql, Object... params) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			TypedQuery<T> query = entityManager.createQuery(sql, clazz);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

//	tìm một list dữ liệu video trending
	public List<T> findManyMaxResult(Class<T> clazz, String sql, int maxResult, Object... params) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			TypedQuery<T> query = entityManager.createQuery(sql, clazz);
			query.setMaxResults(maxResult);
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findManyByNativeQuery(String sql, Object... params) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			Query query = entityManager.createNativeQuery(sql);
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
			return query.getResultList();
		} finally {
			entityManager.close();
		}
	}

	public T create(T entity) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entity = entityManager.merge(entity); // Ensure the entity is managed
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }
    }

	public T update(T entity) {
	    EntityManager entityManager = JPAUtil.getEntityManager();
	    try {
	        entityManager.getTransaction().begin();
	        T mergedEntity = entityManager.merge(entity);
	        entityManager.getTransaction().commit();
	        return mergedEntity;
	    } catch (Exception e) {
	        if (entityManager.getTransaction().isActive()) {
	            entityManager.getTransaction().rollback();
	        }
	        throw new RuntimeException("Error updating entity", e);
	    } finally {
	        entityManager.close();
	    }
	}

	
	public Category update(Integer id, String name) {
	    EntityManager entityManager = JPAUtil.getEntityManager();
	    try {
	        entityManager.getTransaction().begin();
	        
	        Category category = entityManager.find(Category.class, id);
	        if (category != null) {
	            category.setName(name);
	            
	            category = entityManager.merge(category);

	            entityManager.getTransaction().commit();
	        } else {
	            entityManager.getTransaction().rollback();
	            throw new RuntimeException("Category not found for id: " + id);
	        }
	        
	        return category;
	    } catch (Exception e) {
	        entityManager.getTransaction().rollback();
	        throw new RuntimeException(e);
	    } finally {

	        entityManager.close();
	    }
	}


	public T delete(T entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entity = entityManager.merge(entity);
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}
	
//	public void delete(Integer id) {
//        EntityManager entityManager = JPAUtil.getEntityManager();
//        try {
//            entityManager.getTransaction().begin();
//            Category category = entityManager.find(Category.class, id);
//            if (category != null) {
//                entityManager.remove(category);
//            }
//            entityManager.getTransaction().commit();
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//            throw new RuntimeException(e);
//        } finally {
//            entityManager.close();
//        }
//    }
	

	@SuppressWarnings("unchecked")
	public List<T> callStored(String namedStored, Map<String, Object> params) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery(namedStored);
		params.forEach((key, value) -> query.setParameter(key, value));
		return (List<T>) query.getResultList();
	}

	

}
