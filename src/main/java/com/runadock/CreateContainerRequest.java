package com.runadock;

import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * This represents all needed information to create a new Container.
 * 
 * @author stefan
 *
 */
public class CreateContainerRequest {

	private String name;
	private String source;
	private String size;
	private String plan;
	private List<String> env = Lists.newArrayList();
	private List<String> cmd = Lists.newArrayList();

	/**
	 * 
	 */
	public CreateContainerRequest() {
		// Jaxb needs this
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return this.source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(final String source) {
		this.source = source;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return this.size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(final String size) {
		this.size = size;
	}

	/**
	 * @return the env
	 */
	public List<String> getEnv() {
		return this.env;
	}

	/**
	 * @param env
	 *            the env to set
	 */
	public void setEnv(final List<String> env) {
		this.env = ImmutableList.copyOf(env);
	}

	/**
	 * @return the plan
	 */
	public String getPlan() {
		return this.plan;
	}

	/**
	 * @param plan
	 *            the plan to set
	 */
	public void setPlan(final String plan) {
		this.plan = plan;
	}

	/**
	 * @return the cmd
	 */
	public List<String> getCmd() {
		return this.cmd;
	}

	/**
	 * @param cmd
	 *            the cmd to set
	 */
	public void setCmd(final List<String> cmd) {
		this.cmd = ImmutableList.copyOf(cmd);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects
				.toStringHelper(this)
				.add("name", this.name)
				.add("source", this.source)
				.add("cmd", this.cmd)
				.add("env", this.env)
				.toString();
	}

}
