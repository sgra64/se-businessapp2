package com.businessapp.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;

import com.businessapp.model.Article;


/**
 * Local (packaged-scoped) implementation of ArticleRepositoryExtensionIntf.
 * @author Sven Graupner
 *
 */
class ArticleRepositoryExtensionIntfImpl implements ArticleRepositoryExtensionIntf {

	//@Autowired	// triggers UnsatisfiedDependencyException, fix below.
	private ArticleRepositoryIntf ArticleRepository = null;

	/**
	 * Create a new (unsaved) Entity instance.
	 * @return new (unsaved) Entity instance.
	 */
	@Override
	public Article create() {
		return new Article( "", "" );
	}

	/**
	 * Update entity instance in repository if entity exists. If not and if
	 * create is true, insert entity into the repository.
	 * 
	 * @param entity entity to update in repository, if entity exists.
	 * @param create boolean flag that indicates whether entity should
	 * be inserted into repository in case it does not exist.
	 */
	@Override
	public void update( Article entity, boolean create ) {
		ArticleRepository().save( entity );
	}

	/**
	 * Delete entities with ids from the list from the repository.
	 * @param ids list of ids of entities to be deleted.
	 */
	@Override
	public void delete( List<String> ids ) {
		for( String id : ids ) {
			ArticleRepository().deleteById( id );
		}
	}


	/*
	 * Private methods.
	 * Fix UnsatisfiedDependencyException problem for @Autowire ArticleRepository.
	 */
	@Autowired
	private ApplicationContext applicationContext;

	private ArticleRepositoryIntf ArticleRepository() {
		if( ArticleRepository == null ) {
			ArticleRepository = applicationContext.getBean( ArticleRepositoryIntf.class );
		}
		return ArticleRepository;
	}

}
