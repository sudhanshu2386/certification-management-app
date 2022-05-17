package com.ibm.mvs.cms.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/resource")
	@PreAuthorize("hasRole('RESOURCE') or hasRole('MANAGEMENT') or hasRole('ADMIN')")
	public String certificateUIAccess() {
		return "Resource UI Dashboard.";
	}

	@GetMapping("/management")
	@PreAuthorize("hasRole('MANAGEMENT')")
	public String skillUIAccess() {
		return "Management UI Dashboard.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String profileUIAccess() {
		return "Admin UI Dashboard.";
	}
}
