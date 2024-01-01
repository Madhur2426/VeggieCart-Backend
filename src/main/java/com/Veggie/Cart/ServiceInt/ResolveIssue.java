package com.Veggie.Cart.ServiceInt;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Veggie.Cart.Entity.Issue;

public interface ResolveIssue {
	public ResponseEntity<String> registerIssue(Issue issue);
	public ResponseEntity<List<Issue>> getIssueList(String getLoggedInEmail);
}
