package com.ibm.mvs.skill.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillDTO {

	public int skill_id;
	public String skill_name;
	public String skill_desc;
	public String is_active;
	public String creation_time;
	public String last_updated;

}
