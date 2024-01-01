package com.Veggie.Cart.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.mail.SimpleMailMessage;
import java.util.List;
import java.util.Optional;

import com.Veggie.Cart.Dao.AccountsRepo;
import com.Veggie.Cart.Dao.RegisterDao;
import com.Veggie.Cart.Entity.*;
import com.Veggie.Cart.ServiceInt.AccountsInterface;
import com.Veggie.Cart.ServiceInt.RegisterInterface;
@Service
public class RegisterService  implements RegisterInterface {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private RegisterDao register; 
	int generatedOTP;
	@Autowired
    AccountsRepo accounts;
	@Autowired
	AccountsInterface map;

	int numberOfAccounts=0;
	public List<Register> getUserData() {
		return this.register.findAll();
	}
    @Override
	public ResponseEntity<String> registerUser(Register register) {
		try {
			if (this.register.findById(register.getEmail()).isPresent()) {
				if (register.getVerified() == 1)
					return ResponseEntity.status(HttpStatus.CONFLICT)
							.body("An account with this email id already exist");
			}
			Optional<Register> existingRegister = this.register.findById(register.getEmail());
			int otp = 0;
			if (existingRegister.isPresent()) {
				Register existingEntity = existingRegister.get();
				otp = existingEntity.getOtp();
			}
			if (verifyOtp(otp, register.getOtp()) == 1) {
				register.setVerified(1);
				BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
				String encryptedPassword=bcrypt.encode(register.getPassword());
				register.setPassword(encryptedPassword);
				this.register.save(register);
			    BootHashMapOnStartup.mapUsernamePassword.containsKey(register.getEmail());
				map.updateMapWithNewRegistration(register);
				List<Accounts> list=map.fetchNumberOfAccounts();
				if(list.size()==0)
				{
				numberOfAccounts+=1;
				}
				else {
				numberOfAccounts=1+list.get(0).getNumberOfAccounts();
				}
				Accounts account=new Accounts("Developer@VeggiewCart.com",numberOfAccounts);
				this.accounts.save(account);
				return ResponseEntity.status(HttpStatus.OK).body("Registered Successfully");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Error Registering User: Otp is invalid");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error Registering User: Otp is invalid" + e.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> sendSimpleEmail(String from, String to, int message, String subject) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		String otp = String.valueOf(message);
		String messageSend = "Welcome to VeggieCart, your go-to destination for fresh and healthy produce delivered right to your doorstep!\n\n"
				+ "To ensure the security of your account and start shopping with us, please verify your email address by entering the OTP (One-Time Password) provided below:\n\n"
				+ "OTP: " + otp + "\n\n"
				+ "Please enter the above OTP in the verification field on our website to complete the registration process. This OTP will be valid for the next 10 minutes.\n\n"
				+ "Thank you for choosing VeggieCart. We're excited to have you on board!\n\n" + "Best regards,\n"
				+ "The VeggieCart Team";
		simpleMailMessage.setTo(to);
		simpleMailMessage.setText(messageSend);
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setSubject(subject);

		mailSender.send(simpleMailMessage);
		return ResponseEntity.status(HttpStatus.OK).body("Otp sent");

	}

	@Override
	public int getOtp(String email) {
		int otpLength = 6; 
		String otp = "";

		for (int i = 0; i < otpLength; i++) {
			int digit = 0;
			digit = (int) (Math.random() * 10); 
			otp += (digit);
		}
		generatedOTP = Integer.parseInt(otp);
		Optional<Register> existingRegister = register.findById(email);
		if (existingRegister.isPresent()) {
			Register existingEntity = existingRegister.get();
			if (existingEntity.getVerified() == 1)
				return -999999;
		}

		Register newRegister = new Register(email, "", generatedOTP, 0,"","");
		this.register.save(newRegister);
		return generatedOTP;
	}

	public int verifyOtp(int generatedOTP, int userOtp) {
		String first = String.valueOf(generatedOTP);
		String second = String.valueOf(userOtp);
		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) != second.charAt(i))
				return 0;
		}
		return 1;
	}

	@Override
	public ResponseEntity<String> checkExistence(String email) {
		Optional<Register> existingRegister = register.findById(email);
		if (existingRegister.isPresent()) {
			Register existingEntity = existingRegister.get();
			if (existingEntity.getVerified() == 1)
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Account exist otp will not be sent");
		}
		return ResponseEntity.status(HttpStatus.OK).body("new user send otp to email");
	}
}