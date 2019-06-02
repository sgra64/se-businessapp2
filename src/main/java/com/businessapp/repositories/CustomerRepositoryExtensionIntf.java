package com.businessapp.repositories;

import java.util.List;

import com.businessapp.model.Customer;


/**
 * Local (packaged-scoped) repository extension interface for methods that are
 * not part of CrudRepository<E,T>.
 * 
 * This interface is inherited by the actual repository:
 * 
 *    @Repository
 *    interface XYZ_RepositoryIntf extends ExtensionIntf, CrudRepository<E,T>  {
 *    	...
 *    }
 * 
 * Implementation of this interface must follow naming convention:
 * 
 *    class XYZ_RepositoryExtensionIntfImpl implements XYZ_RepositoryExtensionIntf {
 *    	...
 *    }
 * 
 * @author Sven Graupner
 *
 */
interface CustomerRepositoryExtensionIntf {

	/**
	 * Create a new (unsaved) Entity instance.
	 * @return new (unsaved) Entity instance.
	 */
	public Customer create();

	/**
	 * Update entity instance in repository if entity exists. If not and if
	 * create is true, insert entity into the repository.
	 * 
	 * @param entity entity to update in repository, if entity exists.
	 * @param create boolean flag that indicates whether entity should
	 * be inserted into repository in case it does not exist.
	 */
	public void update( Customer entity, boolean create );

	/**
	 * Delete entities with ids from the list from the repository.
	 * @param ids list of ids of entities to be deleted.
	 */
	void delete( List<String> ids );

}
