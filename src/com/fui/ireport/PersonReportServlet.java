package com.fui.ireport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fui.common.IreportUtils;
import com.fui.entity.Person;

/**
 * Servlet implementation class PersonReportServlet
 */
public class PersonReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", "人员基本信息");
		params.put("USER_NAME", "王五");
		// 将Person对象， 放入集合中
		List<Person> personList = new ArrayList<Person>();
		// 这里是给Person对象赋值
		Person person = new Person();
		person.setId(1L);
		person.setName("张三");
		person.setGender("男");
		person.setAge(12);
		person.setAddress("黑龙江哈尔滨");
		personList.add(person);
		// 这里是给Person对象赋值
		person = new Person();
		person.setId(2L);
		person.setName("李四");
		person.setGender("女");
		person.setAge(20);
		person.setAddress("北京中关村");
		personList.add(person);
		// 这里是给Person对象赋值
		person = new Person();
		person.setId(3L);
		person.setName("何小虎");
		person.setGender("男");
		person.setAddress("北京中关村软件园");
		person.setAge(20);
		personList.add(person);

		params.put("personList", personList);

		List<List<Person>> list = new ArrayList<List<Person>>();
		list.add(personList);

		ServletContext servletContext = this.getServletConfig().getServletContext();
		String filePath = servletContext.getRealPath("/jasper/personReport.jasper");
		IreportUtils.printToPdfView(response, filePath, params, list);
	}
}
