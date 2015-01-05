runadock-java
=============

Java Bindings to access runadock.io

Typical usage looks like:

```java

Runadock runadock = RunadockFactory.connect(
				"<your runadock account>",
				"<your runadoxk api token");

CreateContainerRequest containerToCreate = new CreateContainerRequest();
containerToCreate.setSource("mysql");
containerToCreate.setEnv(ImmutableList.of("MYSQL_ROOT_PASSWORD=blubber"));
Container response = this.runadock.createContainer(containerToCreate);
System.out.println("Created Container: " + response);

Container mysqlContainer = this.runadock.describeContainer(response.getContainerId());
this.runadock.terminateContainer(mysqlContainer.getContainerId());


List<Container> containers = this.runadock.describeContainers(false);
for (Container container : containers) {
	System.out.println(container.getContainerId() + " from source: " + container.getSource() + " costs: " + container.getCost());
}
System.out.println("----------------------- ALL Containers ------------------------------------");
List<Container> allContainers = this.runadock.describeContainers(true);
for (Container container : allContainers) {
	System.out.println(container.getContainerId() + " from source: " + container.getSource() + " costs: " + container.getCost());
}

```

Enjoy runadock.
