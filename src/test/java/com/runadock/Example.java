package com.runadock;


public class Example {

	public static void main(final String[] args) {
		Runadock runadock = RunadockFactory.connect(
				"auser",
				"b2712e39-4bcf-48ab-a9a7-9754a7ab13b6");
		CreateContainerRequest request = new CreateContainerRequest();
		request.setSource("mysql");
		Container response = runadock.createContainer(request);
		System.out.println("Created Container: " + response.getId());
	}

}
