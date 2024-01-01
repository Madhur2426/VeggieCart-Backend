package com.Veggie.Cart.Service;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import com.Veggie.Cart.Dao.RegisterDao;
import com.Veggie.Cart.Entity.Register;
import com.Veggie.Cart.Entity.Reset;
import com.Veggie.Cart.ServiceInt.ForgotPassword;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class ForgotPasswordService implements ForgotPassword{
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private RegisterDao register;
	int generatedOTP;
	@Override
	public boolean checkReset(String email) {
		Optional<Register> exist = this.register.findById(email);
		if (exist.isPresent()) {
			Register reset = exist.get();
			if (reset.getVerified() == 1)
				return true;
		}
		return false;
	}

	@Override
	public int sendResetOtp(String email) {
		int otpLength = 6; // Length of the OTP
		String otp = "";

		for (int i = 0; i < otpLength; i++) {
			int digit = 0;
			digit = (int) (Math.random() * 10); // Generate a random digit between 0 and 9
			otp += (digit);
		}
		generatedOTP = Integer.parseInt(otp);
		Optional<Register> existingRegister = register.findById(email);
		if (existingRegister.isPresent()) {
			// Update existing entity
			Register existingEntity = existingRegister.get();
			if (existingEntity.getVerified() == 1) {
				String name=existingEntity.getName();
				Register update = new Register(email, existingEntity.getPassword(), generatedOTP, 1,name,existingEntity.getAddress());
				this.register.save(update);
			}
		}
		return generatedOTP;
	}

	@Override
	public boolean resetPassword(Reset resetObj) {
		Optional<Register> reset = this.register.findById(resetObj.getEmail());
		Register newEntity = reset.get();
		newEntity.setVerified(1);
		if (reset.isPresent()) {
			int resetPassword = reset.get().getOtp();
			if (verifyOtp(resetPassword, resetObj.getOtp())) {
				BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
				newEntity.setPassword(bcrypt.encode(resetObj.getPassword()));
				register.save(newEntity);
			    BootHashMapOnStartup.mapUsernamePassword.put(resetObj.getEmail(),bcrypt.encode(resetObj.getPassword()));
				return true;
			}
		}
		return false;
	}

	@Override
	public ResponseEntity<String> sendResetMail(String from, String to, int message, String subject) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		String otp = String.valueOf(message);
		String messageSend = "Hello,\n\n"
				+ "You are receiving this email because you requested to reset your VeggieCart account password. To proceed with the password reset, please use the following OTP (One-Time Password):\n\n"
				+ "OTP: " + otp + "\n\n"
				+ "Please enter the above OTP on the password reset page within the next 10 minutes. If you didn't request this password reset, you can safely ignore this email.\n\n"
				+ "If you have any questions or need assistance, please contact our support team.\n\n"
				+ "Thank you for using VeggieCart!\n\n" + "Best regards,\n" + "The VeggieCart Team";
		simpleMailMessage.setTo(to); 
		simpleMailMessage.setText(messageSend);
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setSubject(subject);

		mailSender.send(simpleMailMessage);
		return ResponseEntity.status(HttpStatus.OK).body("Password reset OTP sent");

	}

	@Override
	public boolean checkSendResetMail(String email) {
		try {
			int otp = this.sendResetOtp(email);
			this.sendResetMail("madhurnigam96@gmail.com", email, otp, "Reset Password for VeggieCart");
			return true;
		} catch (Exception ex) {
			return false;
		}

	}
	public boolean verifyOtp(int generatedOTP, int userOtp) {
	    String generatedOTPStr = String.valueOf(generatedOTP);
	    String userOtpStr = String.valueOf(userOtp);

	    return generatedOTPStr.equals(userOtpStr);
	}

}
