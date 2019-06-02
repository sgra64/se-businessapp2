package com.businessapp.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;

import com.businessapp.model.Customer;


/**
 * Local (packaged-scoped) implementation of CustomerRepositoryExtensionIntf.
 * @author Sven Graupner
 *
 */
class CustomerRepositoryExtensionIntfImpl implements CustomerRepositoryExtensionIntf {

	//@Autowired	// triggers UnsatisfiedDependencyException, fix below.
	private CustomerRepositoryIntf customerRepository = null;

	/**
	 * Create a new (unsaved) Entity instance.
	 * @return new (unsaved) Entity instance.
	 */
	@Override
	public Customer create() {
		return new Customer( "" );
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
	public void update( Customer entity, boolean create ) {
		customerRepository().save( entity );
	}

	/**
	 * Delete entities with ids from the list from the repository.
	 * @param ids list of ids of entities to be deleted.
	 */
	@Override
	public void delete( List<String> ids ) {
		for( String id : ids ) {
			customerRepository().deleteById( id );
		}
	}


	/*
	 * Private methods.
	 * Fix UnsatisfiedDependencyException problem for @Autowire customerRepository.
	 */
	@Autowired
	private ApplicationContext applicationContext;

	private CustomerRepositoryIntf customerRepository() {
		if( customerRepository == null ) {
			customerRepository = applicationContext.getBean( CustomerRepositoryIntf.class );
		}
		return customerRepository;
	}

}
