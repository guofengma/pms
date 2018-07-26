package com.teamtek.admin.comm.upload.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamtek.admin.comm.upload.entity.BizUploadInfo;
import com.teamtek.admin.comm.upload.filepath.ExcelDownloadPath;
import com.teamtek.core.ann.MenuOrBtnAnnotation;
import com.teamtek.core.controller.BaseController;
import com.teamtek.core.vo.ResponseEntity;


/**
 * <p>
 * 公共上传组件 所有文件上传 都可以调用此接口
 * 
 * @author syj
 * @since 2018-03-01 08:45:48
 */
@RestController
@RequestMapping("/admin/comm/excel")
@MenuOrBtnAnnotation(menu = "导入模板下载", code = "excel_download")
public class ExcelDownloadController extends BaseController<BizUploadInfo> {

	/**
	 * type @see com.teamtek.admin.comm.upload.filepath.ExcelDownloadPath
	 * @return
	 */
	@RequestMapping(value = "/download/{type}")
	public void downloadExcel(HttpServletResponse response,@PathVariable("type") Integer type) {
		try {
			String excelTitle = ExcelDownloadPath.ExcelPath.getTitleByType(type);
			excelTitle = URLEncoder.encode(excelTitle, "UTF-8");
			
			InputStream in = getClass().getResourceAsStream(ExcelDownloadPath.ExcelPath.getPathByType(type));
			
			System.out.println("in 长度---"+in.available());
			response.reset();
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + excelTitle);
			response.setHeader("Content-Length", in.available()+"");
			OutputStream out = response.getOutputStream();
			
			// 一次读多个字节
			byte[] tempbytes = new byte[1024];
			int byteread = 0;
			// 读入多个字节到字节数组中，byteread为一次读入的字节数
			while ((byteread = in.read(tempbytes)) != -1) {
				out.write(tempbytes, 0, byteread);
			}
			in.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			log.error("下载excel导入模板异常【{}】",e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * 查询
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ResponseEntity list() {
		
		return ResponseEntity.ok().putData(ExcelDownloadPath.ExcelPath.getList());
	}

}
