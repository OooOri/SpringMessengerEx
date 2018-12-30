package com.holaris.Messenger.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Reply implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2547625312951484354L;

	@Id @GeneratedValue
	private long rno;
	
	@ManyToOne
	@JoinColumn(name="connected_bno")
	@JsonIgnore
	private Board connected_bno;
	
	@Transient
	private long bno;
	
	private String replytext;
	private String replyer;
	
	@CreationTimestamp
	private LocalDateTime regdate;
	
	@UpdateTimestamp
	private LocalDateTime updatedate;
}
