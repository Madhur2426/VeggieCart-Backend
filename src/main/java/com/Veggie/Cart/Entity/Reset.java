package com.Veggie.Cart.Entity;

public class Reset {
	String email;
	String password;
	int otp;

	public Reset() {
		super();
	}

	public Reset(String email, String password, int otp) {
		super();
		this.email = email;
		this.password = password;
		this.otp = otp;
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

}
