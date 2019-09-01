package com.jmh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jmh.model.Visitor;

public interface WebsiteDataService {
    int deleteByPrimaryKey(String id);

    int insert(Visitor record);

    int insertVisitor(Visitor record);
    
    List<Visitor> getAllVisitor(@Param("startPage")int startPage, @Param("pageSize")int pageSize);

    Visitor selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Visitor record);

    int updateByPrimaryKey(Visitor record);
    
    List<Visitor> getAll();

	void updateVisitor(Visitor visitor);

	void deleteVisitor(String id);

	int getUserCount();
}