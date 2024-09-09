package com.yedam.app.aop.mapper;

import org.apache.ibatis.annotations.Insert;

public interface AaaMapper {
	@Insert("INSERT INTO aaa VALUES (#{value})")
	public int insert(String value);
}
