package com.Veggie.Cart.ServiceInt;

import org.springframework.http.ResponseEntity;
import com.Veggie.Cart.Entity.Reset;

public interface ForgotPassword {
	public boolean checkReset(String email);

	public int sendResetOtp(String email);

	public boolean resetPassword(Reset resetObj);

	public ResponseEntity<String> sendResetMail(String from, String to, int message, String subject);

	public boolean checkSendResetMail(String email);
}
