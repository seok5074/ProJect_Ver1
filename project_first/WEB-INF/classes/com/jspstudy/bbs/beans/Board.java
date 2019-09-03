package com.jspstudy.bbs.beans;

import java.sql.Timestamp;

public class Board {
	//
	private int idx;
	private String name;
	private String subject;
	private String content;
	private Timestamp regDate;
	private int readHit;
	private int likeHit;
	private int ref;
	private int step;
	private int recommend;


	public Board(int idx, String name, String subject, String content, Timestamp regDate, int readHit, int likeHit,
			int ref, int step) {
		this.idx = idx;
		this.name = name;
		this.subject = subject;
		this.content = content;
		this.regDate = regDate;
		this.readHit = readHit;
		this.likeHit = likeHit;
		this.ref = ref;
		this.step = step;
	}
	

	public Board( String name, String subject, String content) {
		//super();
		this.name = name;
		this.subject = subject;
		this.content = content;
	}
	
	// �새로글쓰기
	public Board(int idx, String name, String subject, String content) {
		//super();
		this.idx = idx;
		this.name = name;
		this.subject = subject;
		this.content = content;
	}
	//수정
	public Board(String subject, String content,int idx) {
		//super();
		this.idx = idx;
		this.subject = subject;
		this.content = content;
	}
	//삭제
	public Board(int idx) {
		//super();
		this.idx = idx;
	}
	
	public Board() {
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public int getReadHit() {
		return readHit;
	}

	public void setReadHit(int readHit) {
		this.readHit = readHit;
	}

	public int getLikeHit() {
		return likeHit;
	}

	public void setLikeHit(int likeHit) {
		this.likeHit = likeHit;
	}


	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}


	public int getRecommend() {
		return recommend;
	}


	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

}
