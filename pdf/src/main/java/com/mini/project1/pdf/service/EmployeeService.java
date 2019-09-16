package com.mini.project1.pdf.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mini.project1.pdf.model.Employee;

public interface EmployeeService {
	
	List<Employee>getAllEmployees();

	boolean createPdf(List<Employee> employees, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

	boolean createExcel(List<Employee> employees, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

}
