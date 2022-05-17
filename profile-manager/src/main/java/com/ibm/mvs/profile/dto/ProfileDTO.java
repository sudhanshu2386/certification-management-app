package com.ibm.mvs.profile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileDTO {

	public int profile_id;
	public String profile_name;
	public String profile_desc;
	public String is_active;
	public String creation_time;
	public String last_updated;
}
