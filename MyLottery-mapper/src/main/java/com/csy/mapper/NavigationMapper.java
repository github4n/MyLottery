package com.csy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.csy.domain.Navigation;

@Mapper
public interface NavigationMapper {
	void save(Navigation nav);
	Navigation getById(Long id);
	List<Navigation> getAll(); 
}
