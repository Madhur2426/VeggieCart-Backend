package com.Veggie.Cart.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Veggie.Cart.Dao.IssueDao;
import com.Veggie.Cart.Entity.Issue;
import com.Veggie.Cart.ServiceInt.ResolveIssue;
@Service
public class ResolveIssueService implements ResolveIssue {
	@Autowired
	IssueDao registerIssue;
    Issue support=null;
    String email="";
	@Override
	public ResponseEntity<String> registerIssue(Issue issue) {
		try {
            this.registerIssue.save(issue);
            return ResponseEntity.ok("Issue saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving the issue: " + e.getMessage());
        }			
	}
	@Override
	public ResponseEntity<List<Issue>> getIssueList(String getLoggedInEmail) {
	    try {
	        List<Issue> userIssues = registerIssue.findByEmail(getLoggedInEmail);
	        if(userIssues.size()==0)
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	        return ResponseEntity.status(HttpStatus.OK).body(userIssues);
	    } catch (Exception e) {
	        List<Issue> userIssues = null;
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(userIssues);
	    }
	}

}
