package com.ibm.mvs.skill.controller;

import java.util.ArrayList;
import java.util.List;

import com.ibm.mvs.skill.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mvs.skill.dto.SkillDTO;

@RestController
@RequestMapping(value = "/sm", produces = "application/vnd.api.v1+json")
public class SkillController {

	// private static final Logger LOG =
	// LoggerFactory.getLogger(SkillController.class);

	@PostMapping("/skills")
	public ResponseEntity<ResponseDTO> certificates() {
		SkillDTO skillDTO = new SkillDTO(1, "Java", "OCJP", "Y", "2023-02-01 23:00:00", "2023-02-01 23:00:00");
		List<SkillDTO> skillList = new ArrayList<SkillDTO>();
		skillList.add(skillDTO);
		ResponseDTO response = new ResponseDTO();
		response.setStatus("" + HttpStatus.OK.value());
		response.setSkills(skillList);
		return ResponseEntity.ok(response);
	}
}
