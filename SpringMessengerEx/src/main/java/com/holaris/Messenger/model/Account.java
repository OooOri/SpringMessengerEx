package com.holaris.Messenger.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


@Data
@Entity
public class Account {
	@Id @GeneratedValue
	@Column(name="user_id")
	private long id;
	
	@NotEmpty(message="The username must not be null")
	private String username;
	
	@NotEmpty(message="The password must not be null")
	private String password;
	
	@Email
	@NotEmpty(message="The email must not be null")
	private String email;

	private int active;
	
	@CreationTimestamp
    private LocalDateTime createdTimeAt;

	@UpdateTimestamp
    private LocalDateTime updateTimeAt;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
	
	
}
