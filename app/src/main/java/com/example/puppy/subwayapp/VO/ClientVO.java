package com.example.puppy.subwayapp.VO;

import java.util.Optional;

public class ClientVO
{
	private int			client_pk;
	private String 		id;
	private String 		pw;
	private String		name; 
	private String 		address;
	private String		gender;
	private String		tel;
	private int			age;
	private int 		point;

	public ClientVO(){}

	public ClientVO(String id, String pw, String name,
					String tel, String address, int age)
	{
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.age = age;
	}

	// ---------------------- getters & setters -------------------------
	
	public int getClient_pk() {
		return client_pk;
	}
	public void setClient_pk(int client_pk) {
		this.client_pk = client_pk;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}


	public String getGender() {
		return Optional.ofNullable(gender)
					   .orElse("");
	}

	public void setGender(String gender) {
		this.gender = Optional.ofNullable(gender)
							  .orElse("");
	}





	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
}
