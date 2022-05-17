package com.ibm.mvs.cm.certs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CertificationDTO {
	public int cert_id;
	public String cert_name;
	public String cert_number;
	public String cert_desc;
	public String cert_metadata;
	public String cert_validfrom;
	public String cert_validtill;
	public String is_certificate;
	public String is_Badge;
	public String is_active;
	public String creation_time;
	public String last_updated;
}
