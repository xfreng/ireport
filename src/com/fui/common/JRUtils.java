package com.fui.common;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.FileBufferedOutputStream;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JRUtils {
	
	public static <T> void printToPdfView(HttpServletResponse response, String filePath, Map<String, Object> params,
			List<T> list) {
		JasperPrint jasperPrint = null;
		try {
			File reportFile = new File(filePath);
			JasperReport outputJasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportFile.getPath());
			// 将集合对象数据填充到JasperReport中.
			jasperPrint = JasperFillManager.fillReport(outputJasperReport, params,
					new JRBeanCollectionDataSource(list));
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 预览显示
		if (null != jasperPrint) {
			FileBufferedOutputStream fbos = new FileBufferedOutputStream();
			try {
				JasperExportManager.exportReportToPdfStream(jasperPrint, fbos);
				fbos.close();
				if (fbos.size() > 0) {
					response.setContentType("application/pdf");
					response.setContentLength(fbos.size());
					ServletOutputStream outputStream = response.getOutputStream();
					try {
						fbos.writeData(outputStream);
						fbos.dispose();
						outputStream.flush();
					} finally {
						if (null != outputStream) {
							outputStream.close();
						}
					}
				}
			} catch (JRException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (null != fbos) {
					try {
						fbos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					fbos.dispose();
				}
			}
		}
	}
}
