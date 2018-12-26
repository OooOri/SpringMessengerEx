package com.holaris.Messenger.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Board implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7731880590557909602L;
	@Id @GeneratedValue
	private long bno;
	private String title;
	private String contents;
	private String writer;
	@CreationTimestamp
	private LocalDateTime regDate;
	private int viewcnt=0;
	
	
	
	@OneToMany(mappedBy="connected_bno" ,cascade=CascadeType.ALL ,fetch=FetchType.EAGER)
	private List<Reply> reply = new ArrayList<>();
	
	@OneToMany(mappedBy="connected_bno2" ,cascade=CascadeType.ALL ,fetch=FetchType.LAZY)
	private List<Attach> attach = new ArrayList<>();
	
	/*@ManyToOne
	private Account writerAccount;*/
	
	
}
