package com.holaris.Messenger.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


@Data
@Entity
public class Account {
	@Id @GeneratedValue
	@Column(name="account_id")
	private long id;
	
	@NotEmpty(message="The username must not be null")
	private String username;
	
	@NotEmpty(message="The password must not be null")
	private String password;
	
	@Email
	@NotEmpty(message="The email must not be null")
	private String email;

	private boolean enabled=false;
	
	private String authority;
	
	@CreationTimestamp
    private LocalDateTime createdTimeAt;

	@UpdateTimestamp
    private LocalDateTime updateTimeAt;
	
}
