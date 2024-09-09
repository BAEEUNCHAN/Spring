package com.yedam.app.dept.service;

import lombok.Data;

@Data
public class DeptVO {
	private Integer departmentId; //부서번호, 기본키
	private String departmentName; //부서명, NOT NULL
	private Integer managerId; //부서책임자의 사원번호, FK
	private Integer locationId; //위치번호, FK
}
