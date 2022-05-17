package com.ibm.mvs.cms.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mvs.cms.models.ERole;
import com.ibm.mvs.cms.models.Resource;
import com.ibm.mvs.cms.models.Role;
import com.ibm.mvs.cms.payload.request.LoginRequest;
import com.ibm.mvs.cms.payload.request.SignupRequest;
import com.ibm.mvs.cms.payload.response.JwtResponse;
import com.ibm.mvs.cms.payload.response.MessageResponse;
import com.ibm.mvs.cms.repository.ResourceRepository;
import com.ibm.mvs.cms.repository.RoleRepository;
import com.ibm.mvs.cms.security.jwt.JwtUtils;
import com.ibm.mvs.cms.security.services.ResourceDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class CertificationManagementAuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	ResourceRepository resourceRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		ResourceDetailsImpl resourceDetails = (ResourceDetailsImpl) authentication.getPrincipal();
		List<String> roles = resourceDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, resourceDetails.getId(), resourceDetails.getUsername(),
				resourceDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (resourceRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (resourceRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new Resource's account
		Resource resource = new Resource(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_RESOURCE)
					.orElseThrow(() -> new RuntimeException("Error: Resource Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Admin Role is not found."));
					roles.add(adminRole);

					break;
				case "manager":
					Role modRole = roleRepository.findByName(ERole.ROLE_MANAGEMENT)
							.orElseThrow(() -> new RuntimeException("Error: Management Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_RESOURCE)
							.orElseThrow(() -> new RuntimeException("Error: Resource Role is not found."));
					roles.add(userRole);
				}
			});
		}

		resource.setRoles(roles);
		resourceRepository.save(resource);

		return ResponseEntity.ok(new MessageResponse("Resource registered successfully!"));
	}
}
