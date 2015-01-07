package com.runadock;

public interface Callback {

	void success(Container container, Container.State newState);

	void failure(RunadockError error);
}
