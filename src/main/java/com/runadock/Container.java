package com.runadock;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * Representation of a Container.
 * 
 * @author stefan
 *
 */
public class Container {

	private String id;
	private String containerId;
	private String name;
	private String publicDns;
	private String ip;
	private String source;
	private final State state = State.ORDERED;
	private Long cpuShares;
	private Long memory;
	private Long diskSize;
	private Long ordered;
	private Long created;
	private Long terminated;
	private String orderedBy;
	private String plan;
	private Person owner;
	private final List<Port> ports = Lists.newArrayList();
	private final List<String> env = Lists.newArrayList();
	private final List<String> cmd = Lists.newArrayList();
	private Money pricePerMinute;
	private Money pricePerOrder;
	private Money cost;
	private String buildLog;

	/**
	 * The state of this container.
	 *
	 * @author stefan
	 *
	 */
	public static enum State {
		/** It was just ordererd */
		ORDERED,
		/** Source Dockerfile is actually being checked out */
		CHECKING_OUT,
		/** Image is building from Dockerfile */
		BUILDING,
		/** It is running */
		RUNNING,
		/** It was just ordererd to terminate */
		TERMINATION_ORDERED,
		/** It is terminated */
		TERMINATED,
		/** It is unknown, should never happen */
		UNKNOWN;
	}

	public static class Money {
		public String amount;
		public String currency;

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(this.currency);
			builder.append(" ");
			builder.append(this.amount);
			return builder.toString();
		}

	}

	public static class Port {
		public Integer privatePort;
		public Integer publicPort;
		public String scheme;
	}

	public static class Person {
		public String firstName;
		public String lastName;
		public String street;
		public String zip;
		public String city;
		public String country;
		public String houseNumber;
		public String username;
		public String email;
	}

	/**
	 * @return the containerId
	 */
	public String getContainerId() {
		return this.containerId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return the publicDns
	 */
	public String getPublicDns() {
		return this.publicDns;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return this.source;
	}

	/**
	 * @return the state
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * @return the cpuShares
	 */
	public Long getCpuShares() {
		return this.cpuShares;
	}

	/**
	 * @return the memory
	 */
	public Long getMemory() {
		return this.memory;
	}

	/**
	 * @return the diskSize
	 */
	public Long getDiskSize() {
		return this.diskSize;
	}

	/**
	 * @return the ordered
	 */
	public Long getOrdered() {
		return this.ordered;
	}

	/**
	 * @return the created
	 */
	public Long getCreated() {
		return this.created;
	}

	/**
	 * @return the terminated
	 */
	public Long getTerminated() {
		return this.terminated;
	}

	/**
	 * @return the orderedBy
	 */
	public String getOrderedBy() {
		return this.orderedBy;
	}

	/**
	 * @return the plan
	 */
	public String getPlan() {
		return this.plan;
	}

	/**
	 * @return the owner
	 */
	public Person getOwner() {
		return this.owner;
	}

	/**
	 * @return the ports
	 */
	public List<Port> getPorts() {
		return this.ports;
	}

	/**
	 * @return the env
	 */
	public List<String> getEnv() {
		return this.env;
	}

	/**
	 * @return the cmd
	 */
	public List<String> getCmd() {
		return this.cmd;
	}

	/**
	 * @return the pricePerMinute
	 */
	public Money getPricePerMinute() {
		return this.pricePerMinute;
	}

	/**
	 * @return the pricePerOrder
	 */
	public Money getPricePerOrder() {
		return this.pricePerOrder;
	}

	/**
	 * @return the buildLog
	 */
	public String getBuildLog() {
		return this.buildLog;
	}

	/**
	 * @return the cost
	 */
	public Money getCost() {
		return this.cost;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Container [id=");
		builder.append(id);
		builder.append(",\n containerId=");
		builder.append(containerId);
		builder.append(",\n name=");
		builder.append(name);
		builder.append(",\n publicDns=");
		builder.append(publicDns);
		builder.append(",\n ip=");
		builder.append(ip);
		builder.append(",\n source=");
		builder.append(source);
		builder.append(",\n state=");
		builder.append(state);
		builder.append(",\n cpuShares=");
		builder.append(cpuShares);
		builder.append(",\n memory=");
		builder.append(memory);
		builder.append(",\n diskSize=");
		builder.append(diskSize);
		builder.append(",\n ordered=");
		builder.append(ordered);
		builder.append(",\n created=");
		builder.append(created);
		builder.append(",\n terminated=");
		builder.append(terminated);
		builder.append(",\n orderedBy=");
		builder.append(orderedBy);
		builder.append(",\n plan=");
		builder.append(plan);
		builder.append(",\n owner=");
		builder.append(owner);
		builder.append(",\n ports=");
		builder.append(ports);
		builder.append(",\n env=");
		builder.append(env);
		builder.append(",\n cmd=");
		builder.append(cmd);
		builder.append(",\n pricePerMinute=");
		builder.append(pricePerMinute);
		builder.append(",\n pricePerOrder=");
		builder.append(pricePerOrder);
		builder.append(",\n cost=");
		builder.append(cost);
		builder.append(",\n buildLog=");
		builder.append(buildLog);
		builder.append("]");
		return builder.toString();
	}
	
}
