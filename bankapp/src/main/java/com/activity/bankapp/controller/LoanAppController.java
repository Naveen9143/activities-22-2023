package com.activity.bankapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.activity.bankapp.entities.Loan;
import com.activity.bankapp.entities.LoanApplication;
import com.activity.bankapp.exception.LoanNotFoundException;
import com.activity.bankapp.service.LoanAppServiceImpl;
import com.activity.bankapp.service.LoanService;

@RestController
@RequestMapping("/api/loanapp")
@CrossOrigin(origins = "*")
public class LoanAppController {

	@Autowired
	public LoanAppServiceImpl loanappsvc;

	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> Store(@RequestBody LoanApplication loanapp) {
		return ResponseEntity.status(201).body(loanappsvc.store(loanapp));
	}
	
	@GetMapping()
	public ResponseEntity<Object> fetchLoans() throws LoanNotFoundException{
		try {
			List<LoanApplication> list =loanappsvc.fetchLoanapp();
		return ResponseEntity.status(200).body(list);
		}catch(Exception e){
			String message = e.getMessage();//every Exception class has getMessage
			Map<String, String> error =new HashMap<>();
			error.put("error", message);
			return ResponseEntity.status(404).body(error); //map will be converted to JSON
		}
	}
	
	@GetMapping(path ="/{id}")
	public ResponseEntity<Object> findByLoanAppId(@PathVariable("id") int id) throws LoanNotFoundException{
		try {
			LoanApplication list =loanappsvc.findByLoanAppId(id);
		return ResponseEntity.status(200).body(list);
		}catch(Exception e){
			String message = e.getMessage();//every Exception class has getMessage
			Map<String, String> error =new HashMap<>();
			error.put("error", message);
			return ResponseEntity.status(404).body(error); //map will be converted to JSON
		}
	}
}
