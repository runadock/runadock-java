package com.runadock;

public class RunadockError {
	private final String message;

	public RunadockError(final String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

}
