package com.yedam.app.dept.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.dept.service.DeptVO;

public interface DeptMapper {
	//전체조회
	public List<DeptVO> selectDeptAll();
	//단건조회
	public DeptVO selectDeptInfo(DeptVO deptVO);
	//등록
	public int insertDeptInfo(DeptVO deptVO);
	//수정
	public int updateDeptInfo(@Param("did")int deptId,
								@Param("dept")DeptVO deptVO);
	
	//삭제
	public int deleteDeptInfo(int deptId);
}
