package com.jspstudy.bbs.beans;

public class MemVo {
	int idx, op;
	String name, id, pwd, zipcode, addr, email, phon, birth, gender ;
	
	public MemVo() {
		// TODO Auto-generated constructor stub
	}
	
	

	public MemVo(int idx, String name, String id, String pwd, String zipcode, String addr, String email, String phon,
			String birth, String gender, int op) {
		super();
		this.idx = idx;
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.zipcode = zipcode;
		this.addr = addr;
		this.email = email;
		this.phon = phon;
		this.birth = birth;
		this.gender = gender;
		this.op = op;
	}





	public MemVo(String name, String id, String pwd, String zipcode, String addr, String email, String phon,
			String birth, String gender) {
		super();
		this.name = name;
		this.id = id;
		this.pwd = pwd;
		this.zipcode = zipcode;
		this.addr = addr;
		this.email = email;
		this.phon = phon;
		this.birth = birth;
		this.gender = gender;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhon() {
		return phon;
	}

	public void setPhon(String phon) {
		this.phon = phon;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public int getOp() {
		return op;
	}

	public void setOp(int op) {
		this.op = op;
	}
	
	

}
