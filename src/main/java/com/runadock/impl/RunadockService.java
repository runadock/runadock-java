package com.runadock.impl;

import java.util.List;

import com.runadock.Container;
import com.runadock.CreateContainerRequest;

import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Runadock API description needed to create a Retrofit Adapter.
 * 
 * @author stefan
 *
 */
interface RunadockService {

	@POST("/api/v1/container")
	Container createContainer(@Header("X-Authorization") String authorization,
			@Body final CreateContainerRequest containerToCreate);

	@DELETE("/api/v1/container/{id}")
	void terminateContainer(@Header("X-Authorization") String authorization, @Path("id") final String id);

	@GET("/api/v1/container/{id}")
	Container describeContainer(@Header("X-Authorization") String authorization, @Path("id") final String id);

	@GET("/api/v1/container")
	List<Container> describeContainers(@Header("X-Authorization") String authorization, @Query("all") final Boolean all);
}
