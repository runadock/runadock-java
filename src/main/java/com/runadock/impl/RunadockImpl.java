package com.runadock.impl;

import java.util.List;

import com.google.common.base.Strings;
import com.runadock.Container;
import com.runadock.CreateContainerRequest;
import com.runadock.Runadock;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;

/**
 * Implementation of the Cloudflare REST API based on Square Retrofit.
 *
 * @author stefan
 *
 */
public class RunadockImpl implements Runadock {
	private final RestAdapter restAdapter;
	private final RunadockService runadockService;
	private final String authorization;
	private final String endpoint;

	/**
	 * Constructor.
	 * 
	 * @param user
	 * @param token
	 * @param endpoint
	 * 
	 */
	public RunadockImpl(final String user, final String token, final String endpoint) {
		if (Strings.isNullOrEmpty(endpoint)) {
			this.endpoint = "https://runadock.io/";
		} else {
			this.endpoint = endpoint;
		}
		this.restAdapter = new RestAdapter.Builder().setEndpoint(this.endpoint).build();
		this.restAdapter.setLogLevel(LogLevel.FULL);
		this.runadockService = this.restAdapter.create(RunadockService.class);
		this.authorization = user + ":" + token;
	}

	@Override
	public Container createContainer(final CreateContainerRequest containerToCreate) {
		return this.runadockService.createContainer(this.authorization, containerToCreate);
	}

	@Override
	public void terminateContainer(final String id) {
		this.runadockService.terminateContainer(this.authorization, id);
	}

	@Override
	public Container describeContainer(final String id) {
		return this.runadockService.describeContainer(this.authorization, id);
	}

	@Override
	public List<Container> describeContainers(final Boolean all) {
		return this.runadockService.describeContainers(this.authorization, all);
	}

}
