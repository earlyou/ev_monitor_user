package com.user.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SSEVO {
	private String id; // uid+localdatetime
	private String uid;
	private String statid;
	private String alarmtime;
	private String notifyContent;
	private String notifyType; // aset: 알람설정, achec: 알람확인
}
