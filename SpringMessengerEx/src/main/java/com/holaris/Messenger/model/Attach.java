package com.holaris.Messenger.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Attach implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7037245147688093615L;

	@Id @GeneratedValue
	private long id;
	
	private String fileName;
	
	@ManyToOne
	@JoinColumn(name="connected_bno2")
	@JsonIgnore
	private Board connected_bno2;
	
	@CreationTimestamp
	private LocalDateTime regdate;
}
