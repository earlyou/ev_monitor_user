package com.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAuthorityVO {
	
	private int utid;
	private String userstypename;

	public UserAuthorityVO(String userstypename) {
		this.userstypename = userstypename;
	}
	
	
	
}
