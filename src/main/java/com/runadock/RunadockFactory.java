package com.runadock;

import com.runadock.impl.RunadockImpl;

/**
 * Use this factory to create a new Instance of a Cloudflare DNS Api Adapter.
 *
 * @author stefan
 *
 */
public enum RunadockFactory {
	;
	/**
	 * Creates a new runadock.io connection.
	 * 
	 * @param user
	 * @param token
	 * @param endpoint
	 * @return a Runadock API Instance.
	 *
	 */
	public static Runadock connect(final String user, final String token, final String endpoint) {
		return new RunadockImpl(user, token, endpoint);
	}

	/**
	 * Creates a new runadock.io connection.
	 * 
	 * @param user
	 * @param token
	 * @return a Runadock API Instance.
	 *
	 */
	public static Runadock connect(final String user, final String token) {
		return new RunadockImpl(user, token, null);
	}

}
