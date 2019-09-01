package com.jmh.service;

import java.util.List;

import com.jmh.model.Visitor;

public interface WebsiteService {
	
	int newVisitor(Visitor visitor);
	
	List<Visitor> getAllVisitor(int startPage, int pageSize);
	
	List<Visitor> getAll();
	
	String delete(String id);
	
	Visitor findById(String id);
	
	String update(Visitor addInfo);

	void updateVisitor(Visitor visitor);

	void deleteVisitor(String id);

	int getVisitorCount();

}
