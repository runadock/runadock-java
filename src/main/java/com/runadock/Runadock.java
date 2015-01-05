package com.runadock;

import java.util.List;

/**
 * Interface to runadock. @see "https://runadock.io/api/v1"
 *
 * @author stefan
 *
 */
public interface Runadock {

	/**
	 * Create a new container.
	 *
	 * @param containerToCreate
	 * @return the created Container Response.
	 */
	Container createContainer(final CreateContainerRequest containerToCreate);

	/**
	 * Terminate a container.
	 *
	 * @param id
	 */
	void terminateContainer(final String id);

	/**
	 * Describe a Container
	 *
	 * @param id
	 * @return the container
	 */
	Container describeContainer(final String id);

	/**
	 * Describe all Containers
	 * 
	 * @param all
	 *            if set to true all containers including terminated will be described.
	 *
	 * @return all Containers.
	 */
	List<Container> describeContainers(final Boolean all);
}
