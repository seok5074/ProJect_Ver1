package com.project.videos.vo;

public class V_ReplyVO {

	int vr_no;
	int v_no;

	String vr_reply;
	String vr_writer;
	String vr_regdate;

	public V_ReplyVO() {
		super();
	}

	public V_ReplyVO(int v_no, String vr_reply, String vr_writer) {
		super();
		this.v_no = v_no;
		this.vr_reply = vr_reply;
		this.vr_writer = vr_writer;
	}

	public V_ReplyVO(int vr_no, int v_no, String vr_reply, String vr_writer, String vr_regdate) {
		super();
		this.vr_no = vr_no;
		this.v_no = v_no;
		this.vr_reply = vr_reply;
		this.vr_writer = vr_writer;
		this.vr_regdate = vr_regdate;
	}

	public int getVr_no() {
		return vr_no;
	}

	public void setVr_no(int vr_no) {
		this.vr_no = vr_no;
	}

	public int getV_no() {
		return v_no;
	}

	public void setV_no(int v_no) {
		this.v_no = v_no;
	}

	public String getVr_reply() {
		return vr_reply;
	}

	public void setVr_reply(String vr_reply) {
		this.vr_reply = vr_reply;
	}

	public String getVr_writer() {
		return vr_writer;
	}

	public void setVr_writer(String vr_writer) {
		this.vr_writer = vr_writer;
	}

	public String getVr_regdate() {
		return vr_regdate;
	}

	public void setVr_regdate(String vr_regdate) {
		this.vr_regdate = vr_regdate;
	}
}