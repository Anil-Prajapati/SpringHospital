package com.hospitals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospitals.model.JwtRequest;
import com.hospitals.model.JwtResponse;
import com.hospitals.service.JwtService;

@RestController
public class JwtController {

	@Autowired
	private JwtService jwtService;
	
		
	@PostMapping("/authentication")
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) {	
		return jwtService.createJwtToken(jwtRequest);
	}
}
