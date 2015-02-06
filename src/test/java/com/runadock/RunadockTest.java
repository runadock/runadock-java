package com.runadock;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import retrofit.RetrofitError;

import com.google.common.collect.ImmutableList;
import com.runadock.Container.State;

@Test
public class RunadockTest {

	Runadock runadock;
	private State state;

	@BeforeTest
	public void setUp() {
		this.runadock = RunadockFactory.connect("<your-runadock-id>", "<b2712e39-4bcf-48ab-a9a7-9754a7ab13b6>");
	}

	@Test(enabled = false)
	public void createContainer() {
		CreateContainerRequest containerToCreate = new CreateContainerRequest();
		containerToCreate.setSource("mysql");
		containerToCreate.setEnv(ImmutableList.of("MYSQL_ROOT_PASSWORD=blubber"));
		Container response = this.runadock.createContainer(containerToCreate);
		System.out.println("Created Container: " + response);
	}

	void setState(final State newState) {
		this.state = newState;
	}

	@Test
	public void createContainerWithCallBack() throws InterruptedException {
		CreateContainerRequest containerToCreate = new CreateContainerRequest();
		containerToCreate.setSource("mysql");
		containerToCreate.setEnv(ImmutableList.of("MYSQL_ROOT_PASSWORD=blubber"));
		Callback callback = new Callback() {

			@Override
			public void success(final Container container, final State newState) {
				System.out.println("New State detected for container: " + container.getContainerId() + " " + newState);
				setState(newState);
				if (newState == State.RUNNING) {
					RunadockTest.this.runadock.terminateContainer(container.getId());
				}
			}

			@Override
			public void failure(final RunadockError error) {
				System.out.println("Error:" + error.getMessage());
			}
		};
		this.runadock.createContainer(containerToCreate, callback);

		while (this.state != State.TERMINATED) {
			Thread.sleep(100l);
		}
	}

	@Test
	public void describeContainer() {
		try {
			Container container = this.runadock.describeContainer("unknownContainer");
			Assert.fail("The expectation that describe of an unknown container gives 404 failed. ");
		} catch (RetrofitError e) {
			// Expected.
		}

	}

	@Test(enabled = false)
	public void describeContainers() {
		List<Container> containers = this.runadock.describeContainers(false);
		for (Container container : containers) {
			System.out.println(container.getContainerId() + " from source: " + container.getSource() + " costs: "
					+ container.getCost());
		}
		System.out.println("----------------------- ALL Containers ------------------------------------");
		List<Container> allContainers = this.runadock.describeContainers(true);
		for (Container container : allContainers) {
			System.out.println(container.getContainerId() + " from source: " + container.getSource() + " costs: "
					+ container.getCost());
		}

	}

	@Test
	public void terminateContainer() {
	}
}
