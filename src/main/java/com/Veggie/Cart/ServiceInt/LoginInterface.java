package com.Veggie.Cart.ServiceInt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.Veggie.Cart.Entity.Agent;
import com.Veggie.Cart.Entity.AgentLogin;
import com.Veggie.Cart.Entity.Login;
import com.Veggie.Cart.Entity.Register;

public interface LoginInterface {
	public ResponseEntity<String> loginUser(Login login);
	public ResponseEntity<Register> userProfile();
    public ResponseEntity<Register> loginInfo();
    public String getLoggedInEmail();
    public ResponseEntity<String> logoutDataClear();
    public ResponseEntity<String> checkEmail();
    public ResponseEntity<String> agentLogin(AgentLogin agent);
}
