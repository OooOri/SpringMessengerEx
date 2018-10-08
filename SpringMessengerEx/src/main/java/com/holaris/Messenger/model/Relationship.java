package com.holaris.Messenger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Relationship {
	@Id @GeneratedValue
	@Column(name="relation_id")
	private long id;
	
	/*@NotNull
	private Account requestingUser;
	
	@ManyToOne
	@JoinColumn(name="requestedUser_FK")
	private Account requestedUser;*/
		
	private boolean isFriend = false;
}
