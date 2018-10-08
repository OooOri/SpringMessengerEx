package com.holaris.Messenger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class AlarmMessage {
	@Id @GeneratedValue
	@Column(name="alarm_id")
	private long id;
	
	@ManyToOne
	@JoinColumn/*(name="toAccount")*/
	/*@JsonIgnore*/
	private Account toAccount;
	
	private long fromAccount;
	
	private String alarmContents;
	
	private String alarmType;
}
