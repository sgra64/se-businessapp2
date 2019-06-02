package com.businessapp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.businessapp.model.Article;


/**
 * Public interface to access Article repository with CRUD methods (Create, Read, Update, Delete).
 * Spring will create a Repository component (-> @Repository annotation) for this interface that
 * can be @Autowired for getting access.
 * 
 *	@Autowired
 *	private CustomerRepositoryIntf customerRepository;
 * 
 * @author Sven Graupner
 *
 */
@Repository
public interface ArticleRepositoryIntf extends ArticleRepositoryExtensionIntf, CrudRepository<Article, String>  {

	/*
	 * All interface methods are inherited from CrudRepository, see:
	 *	- https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.core-concepts
	 *	- https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
	 *
	 * public interface CrudRepository <T, ID> extends Repository<T, ID> {
	 * 
	 *    long count();
	 *
	 *    void delete( T entity );
	 *    void deleteAll();
	 *    void deleteAll( Iterable<? extends T> entities );
	 *    void deleteById( ID id );
	 *
	 *    boolean existsById( ID id );
	 *
	 *    Iterable<T> findAll();
	 *    Iterable<T> findAllById( Iterable<ID> ids );
	 *    Optional<T> findById( ID id );
	 *
	 *    <S extends T> S save( S entity );
	 *    <S extends T> Iterable<S> save( Iterable<S> entities );
	 * }
	 */
}
