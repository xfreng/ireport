package com.fui.ireport;

import com.fui.common.JRUtils;
import com.fui.entity.Output;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Servlet implementation class OutputReportServlet
 */
public class OutputReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OutputReportServlet() {
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
		params.put("title", "XXX单位出库单");
		params.put("id", "CK2017000001");
		params.put("outputtime", new Date(System.currentTimeMillis()));
		params.put("operator", "abc");
		params.put("username", "王五");
		List<Output> list = new ArrayList<Output>();
		Output output = new Output();
		output.setMaterialid("WZ20170001");
		output.setMaterialname("扳手");
		output.setAssetno("ZC00000001");
		output.setCategory("资产类物资");
		output.setModel("大号");
		output.setSpecification("13mm");
		output.setUnit("把");
		output.setAmount("1");
		list.add(output);
		output = new Output();
		output.setMaterialid("WZ20170002");
		output.setMaterialname("螺丝");
		output.setAssetno("");
		output.setCategory("生产性消缺");
		output.setModel("长");
		output.setSpecification("5mm");
		output.setUnit("个");
		output.setAmount("10");
		list.add(output);

		ServletContext servletContext = this.getServletConfig().getServletContext();
		String filePath = servletContext.getRealPath("/jasper/OutputReport.jasper");
		JRUtils.printToPdfView(response, filePath, params, list);
	}
}