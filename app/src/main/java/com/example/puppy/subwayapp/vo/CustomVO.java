package com.example.puppy.subwayapp.vo;

public class CustomVO
{
	private int custom_id;
	private int client_id;
	private String name;
	private String bread;
	private String cheese;
	private String sauce;
	private String excludeVegit;
	private String addition;
	private int price;

	// --------------- token for JWT ---------------------
	private String jwt;

	//----------------  constructor  -----------------------
    public CustomVO(){}

    public CustomVO(String name    , String bread , String cheese,
                    String sauce   , String excludeVegit,
                    String addition, int price, String jwt)
    {
        this.name         = name;
        this.bread        = bread;
        this.cheese       = cheese;
        this.sauce        = sauce;
        this.excludeVegit = excludeVegit;
        this.addition     = addition;
        this.price        = price;
        this.jwt          = jwt;
    }

	public CustomVO(int custom_id, String name) {
		this.custom_id = custom_id;
		this.name = name;
	}

	// --------------- getters & setters ----------------
	public int getCustom_id() {
		return custom_id;
	}
	public void setCustom_id(int custom_id) {
		this.custom_id = custom_id;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBread() {
		return bread;
	}
	public void setBread(String bread) {
		this.bread = bread;
	}
	public String getCheese() {
		return cheese;
	}
	public void setCheese(String cheese) {
		this.cheese = cheese;
	}
	public String getSauce() {
		return sauce;
	}
	public void setSauce(String sauce) {
		this.sauce = sauce;
	}
	public String getExcludeVegit() {
		return excludeVegit;
	}
	public void setExcludeVegit(String excludeVegit) {
		this.excludeVegit = excludeVegit;
	}
	public String getAddition() {
		return addition;
	}
	public void setAddition(String addition) {
		this.addition = addition;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
    public String getJwt() {
        return jwt;
    }
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
