package com.project.videos.vo;

public class MainVO {

	int v_no; // 비디오 번호  
	int v_like; // 좋아요 수 
	int v_views; // 조회수 
	
	String v_uploader;
	String v_videoloc; // 영상 업로드 위치 
	String v_sumnailloc; // 영상 썸네일 업로드 위치 
	String v_title; // 영상 제목 
	String v_hashtag; // 영상 해시태그 (#구독, #좋아요) 
	String v_content; // 영상 밑 본문 설명
	String v_regdate; // 영상 업로드 일자 
	String v_category; // 영상 주제
	
	public MainVO() {
		// TODO Auto-generated constructor stub
	}
	
	public MainVO(int v_no, int v_like, int v_views, String v_uploader, String v_videoloc, String v_sumnailloc,
			String v_title, String v_hashtag, String v_content, String v_regdate, String v_category) {
		super();
		this.v_no = v_no;
		this.v_like = v_like;
		this.v_views = v_views;
		this.v_uploader = v_uploader;
		this.v_videoloc = v_videoloc;
		this.v_sumnailloc = v_sumnailloc;
		this.v_title = v_title;
		this.v_hashtag = v_hashtag;
		this.v_content = v_content;
		this.v_regdate = v_regdate;
		this.v_category = v_category;
	}

	public MainVO(String v_uploader, String v_videoloc, String v_sumnailloc, String v_title, String v_hashtag,
			String v_content, String v_category) {
		super();
		this.v_uploader = v_uploader;
		this.v_videoloc = v_videoloc;
		this.v_sumnailloc = v_sumnailloc;
		this.v_title = v_title;
		this.v_hashtag = v_hashtag;
		this.v_content = v_content;
		this.v_category = v_category;
	}

	public MainVO(String v_title, String v_hashtag, String v_category) {
		super();
		this.v_title = v_title;
		this.v_hashtag = v_hashtag;
		this.v_category = v_category;
	}
	
	public MainVO(String v_title, String v_hashtag, String v_content, String v_category, int v_no) {
		super();
		this.v_no = v_no;
		this.v_title = v_title;
		this.v_hashtag = v_hashtag;
		this.v_content = v_content;
		this.v_category = v_category;
	}

	public String getV_category() {
		return v_category;
	}

	public void setV_category(String v_category) {
		this.v_category = v_category;
	}

	public String getV_uploader() {
		return v_uploader;
	}

	public void setV_uploader(String v_uploader) {
		this.v_uploader = v_uploader;
	}



	public String getV_videoloc() {
		return v_videoloc;
	}


	public void setV_videoloc(String v_videoloc) {
		this.v_videoloc = v_videoloc;
	}

	public String getV_sumnailloc() {
		return v_sumnailloc;
	}

	public void setV_sumnailloc(String v_sumnailloc) {
		this.v_sumnailloc = v_sumnailloc;
	}

	public String getV_regdate() {
		return v_regdate;
	}

	public void setV_regdate(String v_regdate) {
		this.v_regdate = v_regdate;
	}

	public int getV_no() {
		return v_no;
	}

	public void setV_no(int v_no) {
		this.v_no = v_no;
	}

	public int getV_like() {
		return v_like;
	}

	public void setV_like(int v_like) {
		this.v_like = v_like;
	}

	public int getV_views() {
		return v_views;
	}

	public void setV_views(int v_views) {
		this.v_views = v_views;
	}

	public String getV_title() {
		return v_title;
	}

	public void setV_title(String v_title) {
		this.v_title = v_title;
	}

	public String getV_hashtag() {
		return v_hashtag;
	}

	public void setV_hashtag(String v_hashtag) {
		this.v_hashtag = v_hashtag;
	}

	public String getV_content() {
		return v_content;
	}

	public void setV_content(String v_content) {
		this.v_content = v_content;
	}
	
}