package com.Veggie.Cart.Service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Veggie.Cart.Dao.AccountsRepo;
import com.Veggie.Cart.Dao.AgentRepo;
import com.Veggie.Cart.Dao.NumberOfAgentsRepo;
import com.Veggie.Cart.Dao.RegisterDao;
import com.Veggie.Cart.Entity.Accounts;
import com.Veggie.Cart.Entity.Agent;
import com.Veggie.Cart.Entity.NumberOfAgents;
import com.Veggie.Cart.Entity.Register;
import com.Veggie.Cart.ServiceInt.AccountsInterface;
import com.Veggie.Cart.ServiceInt.NumberOfAgentsInterface;

@Service
public class BootHashMapOnStartup implements AccountsInterface,NumberOfAgentsInterface {
	@Autowired
	RegisterDao register;
	@Autowired
	AccountsRepo accounts;
	@Autowired
	AgentRepo agent;
	@Autowired
	NumberOfAgentsRepo agentCount;

	static HashMap<String, String> mapUsernamePassword = new HashMap<>();
	static HashMap<Integer, String> mapAgentUsernamePassword = new HashMap<>();

	@Override
	public List<Accounts> fetchNumberOfAccounts() {
		return this.accounts.findAll();
	}

	public List<Register> getRegisterData() {
		return this.register.findAll();
	}

	@Override
	public ResponseEntity<String> bootMap(int numberOfAccounts) {
		if (numberOfAccounts == mapUsernamePassword.size())
			return ResponseEntity.status(304).body("Data already present in HashMap");
		List<Register> data = getRegisterData();
		if (data != null) {
			for (Register register : data) {
				if(register.getPassword().length()>1) {
				String username = register.getEmail();
				String password = register.getPassword();
				if (!mapUsernamePassword.containsKey(username))
					mapUsernamePassword.put(username, password);
			}
			}
			return ResponseEntity.status(200).body("Data filled in HashMap");
		}
		return ResponseEntity.status(204).body("No data present in Database");
	}	
	@Override
	public void updateMapWithNewRegistration(Register newRegister) {
        String username = newRegister.getEmail();
        String password = newRegister.getPassword();
        if (!mapUsernamePassword.containsKey(username)) {
            mapUsernamePassword.put(username, password);
        }
    }

	@Override
	public List<NumberOfAgents> fetchNumberOfAgents() {
		return this.agentCount.findAll();
	}

	public List<Agent> getAgentData() {
		return this.agent.findAll();
	}

	@Override
	public ResponseEntity<String> bootAgentMap(int numberOfAccounts) {
		if (numberOfAccounts == mapAgentUsernamePassword.size())
			return ResponseEntity.status(304).body("Data already present in HashMap");
		List<Agent> agentsData = getAgentData();
		if (agentsData != null) {
			for (Agent agent : agentsData) {
				if(agent.getPassword().length()>1) {
				int id = agent.getId();
				String password = agent.getPassword();
				if (!mapUsernamePassword.containsKey(id))
					mapAgentUsernamePassword.put(id, password);
			}
			}
			return ResponseEntity.status(200).body("Data filled in HashMap");
		}
		return ResponseEntity.status(204).body("No data present in Database");
	}
}