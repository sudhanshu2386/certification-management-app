package com.ibm.mvs.cm.certs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mvs.cm.certs.dto.CertificationDTO;
import com.ibm.mvs.cm.certs.dto.ResponseDTO;

@RestController
@RequestMapping(value = "/cm", produces = "application/vnd.api.v1+json")
public class CertificationController {

	// private static final Logger LOG =
	// LoggerFactory.getLogger(CertificationController.class);

	@PostMapping("/certificates")
	public ResponseEntity<ResponseDTO> certificates() {
		CertificationDTO certDTO = new CertificationDTO(1, "CKAD", "1230183",
				"Certified Kubernetes Application Developer", "URL of file server path", "2021-02-01 23:00:00",
				"2023-02-01 23:00:00", "Y", "N", "Y", "2021-02-01 23:00:00", "2021-02-01 23:00:00");
		List<CertificationDTO> certList = new ArrayList<CertificationDTO>();
		certList.add(certDTO);
		ResponseDTO response = new ResponseDTO();
		response.setStatus("" + HttpStatus.OK.value());
		response.setCertifications(certList);
		return ResponseEntity.ok(response);
	}
}
