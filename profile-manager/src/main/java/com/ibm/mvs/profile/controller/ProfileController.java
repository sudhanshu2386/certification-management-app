package com.ibm.mvs.profile.controller;

import java.util.ArrayList;
import java.util.List;

import com.ibm.mvs.profile.dto.ProfileDTO;
import com.ibm.mvs.profile.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pm", produces = "application/vnd.api.v1+json")
public class ProfileController {

	// private static final Logger LOG =
	// LoggerFactory.getLogger(ProfileController.class);

	@PostMapping("/profiles")
	public ResponseEntity<ResponseDTO> certificates() {
		ProfileDTO profileDTO = new ProfileDTO(1, "testIBM", "testIBM", "Y", "2023-02-01 23:00:00",
				"2023-02-01 23:00:00");
		List<ProfileDTO> profileList = new ArrayList<ProfileDTO>();
		profileList.add(profileDTO);
		ResponseDTO response = new ResponseDTO();
		response.setStatus("" + HttpStatus.OK.value());
		response.setProfiles(profileList);
		return ResponseEntity.ok(response);
	}
}
