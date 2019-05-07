package com.via.Webservice.WebService.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Co2")
public class Co2 extends ResourceSupport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	final transient int higheAcceptableValue = 1;

	final transient int lowAcceptableValue = 0;

	@ManyToOne
	private Room room;

	@Column(name = "status")
	private String status;

	@Column(name = "value")
	private String value;

	@Column(name = "timestamp")
	private Timestamp timestamp;

	public Co2() {

	}

	@JsonCreator
	public Co2(@JsonProperty("co2") String value, Timestamp timestamp, Room room) {
		this.room = room;

		this.value = value;
		this.timestamp = timestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getHigheacceptablevalue() {
		return higheAcceptableValue;
	}

	public int getLowacceptablevalue() {
		return lowAcceptableValue;
	}

	@Override
	public String toString() {
		return "Co2 [id=" + id + ", higheAcceptableValue=" + higheAcceptableValue + ", lowAcceptableValue="
				+ lowAcceptableValue + ", room=" + room + ", status=" + status + ", value=" + value + ", timestamp="
				+ timestamp + "]";
	}

	

}
