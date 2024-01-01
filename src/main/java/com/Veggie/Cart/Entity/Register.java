package com.Veggie.Cart.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Register {
	@Id
	String email;
	String password;
	int otp;
	int verified;
	String name;
	String address;
	public Register() {
		super();
	}

	public Register(String email, String password, int otp, int verified, String name, String address) {
		super();
		this.email = email;
		this.password = password;
		this.otp = otp;
		this.verified = verified;
		this.name = name;
		this.address = address;
	}

	@Override
	public String toString() {
		return "Register [email=" + email + ", password=" + password + ", otp=" + otp + ", verified=" + verified
				+ ", name=" + name + ", address=" + address + "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public int getVerified() {
		return verified;
	}
	public void setVerified(int verified) {
		this.verified = verified;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	

}
