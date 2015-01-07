package com.runadock.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Strings;
import com.google.common.util.concurrent.AbstractScheduledService;
import com.runadock.Callback;
import com.runadock.Container;
import com.runadock.CreateContainerRequest;
import com.runadock.Runadock;
import com.runadock.RunadockError;

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
		this.restAdapter.setLogLevel(LogLevel.BASIC);
		this.runadockService = this.restAdapter.create(RunadockService.class);
		this.authorization = user + ":" + token;
	}

	@Override
	public Container createContainer(final CreateContainerRequest containerToCreate) {
		return this.runadockService.createContainer(this.authorization, containerToCreate);
	}

	@Override
	public void createContainer(final CreateContainerRequest containerToCreate, final Callback callback) {
		Container container = this.runadockService.createContainer(this.authorization, containerToCreate);
		ContainerStateChecker stateChecker = new ContainerStateChecker(
				container.getId(),
				this.runadockService,
				this.authorization,
				callback);
		stateChecker.startAsync();
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

	private static class ContainerStateChecker extends AbstractScheduledService {

		private final String containerId;
		private final RunadockService runadockService;
		private final String authorization;
		private final Callback callback;
		private com.runadock.Container.State lastState = com.runadock.Container.State.ORDERED;

		/**
		 * @param containerId
		 * @param runadockService
		 * @param authorization
		 * @param callback
		 */
		public ContainerStateChecker(final String containerId, final RunadockService runadockService,
				final String authorization, final Callback callback) {
			super();
			this.containerId = containerId;
			this.runadockService = runadockService;
			this.authorization = authorization;
			this.callback = callback;
		}

		@Override
		protected void runOneIteration() throws Exception {
			Container result;
			try {
				result = this.runadockService.describeContainer(this.authorization, this.containerId);
				com.runadock.Container.State newState = result.getState();
				if (!newState.equals(this.lastState)) {
					this.callback.success(result, newState);
					this.lastState = newState;
				}
				if (newState.equals(com.runadock.Container.State.TERMINATED)) {
					this.stopAsync();
				}
			} catch (Exception e) {
				this.callback.failure(new RunadockError(e.getMessage()));
			}

		}

		@Override
		protected Scheduler scheduler() {
			return Scheduler.newFixedRateSchedule(0L, 2L, TimeUnit.SECONDS);
		}

	}
}
