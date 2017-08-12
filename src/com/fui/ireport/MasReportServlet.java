package com.fui.ireport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fui.common.IreportUtils;
import com.fui.entity.Mas;

/**
 * Servlet implementation class MasReportServlet
 */
public class MasReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MasReportServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("MAS_TITLE", "Ⅳ 号货运站货架库-物资调整单");
		params.put("BILL_CODE", "123");
		params.put("CREATE_TIME", new Date(System.currentTimeMillis()));
		params.put("CREATOR_NAME", "王五");
		params.put("USER_NAME", "张三");
		List<Mas> dataList = new ArrayList<Mas>();
		for (int i = 0; i < 10; i++) {
			Mas mas = new Mas();
			mas.setOUTBINCODE("402061902");
			mas.setOUTBINNAME("06-19-02");
			mas.setINBINCODE("401020701");
			mas.setINBINNAME("02-07-01");
			mas.setQUANTITY("12");
			dataList.add(mas);
		}
		params.put("dets", dataList);

		List<List<Mas>> list = new ArrayList<List<Mas>>();
		list.add(dataList);
		ServletContext servletContext = this.getServletConfig().getServletContext();
		String filePath = servletContext.getRealPath("/jasper/MasReport.jasper");
		IreportUtils.printToPdfView(response, filePath, params, list);
	}
}