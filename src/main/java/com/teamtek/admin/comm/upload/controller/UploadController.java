package com.teamtek.admin.comm.upload.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.teamtek.admin.comm.upload.entity.BizUploadInfo;
import com.teamtek.admin.sys.entity.SysUser;
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
@RequestMapping("/admin/comm")
public class UploadController extends BaseController<BizUploadInfo> {

	/**
	 * 统一 文件上传接口
	 * 
	 * @param file
	 * @param module
	 *            菜单模块名 （比如：若果从 用户管理 菜单里上传的文件 那么 module就是 user）
	 * @param rootPath
	 *            根路径
	 * @return
	 */
	@RequestMapping(value = "/{module}/upload")
	public ResponseEntity add(MultipartFile file, @PathVariable("module") String module,
			@Value("${server.fileUpload.rootPath}") String rootPath, HttpServletRequest request) {
		SysUser uploadUser = this.getCurrUser();
		// 源文件名字
		String originalName = file.getOriginalFilename();
		String fileSuffix = originalName.substring(originalName.lastIndexOf(".") + 1);
		// 实际保存的文件名字

		String fullCurrName = UUID.randomUUID().toString() + "." + fileSuffix;
		// 上传目录
		String parent = rootPath + "/" + module;
		File dest = new File(parent, fullCurrName);
		// 判断路径是否存在，如果不存在就创建一个
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		// 文件大小（字节）
		Integer fileSize = null;
		String uploadUserName = uploadUser == null ? null : uploadUser.getName();
		try {
			fileSize = file.getBytes().length;
			file.transferTo(dest);
		} catch (IllegalStateException | IOException e) {
			log.error("--文件上传错误，上传人【{}】，文件【{}】，异常信息【{}】！！--", uploadUserName, originalName, e.getMessage());
			return ResponseEntity.error("文件上传错误！！");
		}
		BizUploadInfo uploadInfo = new BizUploadInfo(originalName, fullCurrName, fileSize, fileSuffix,
				parent + "/" + fullCurrName, uploadUser.getUserId(), new Date(), module);
		this.baseService.insert(uploadInfo);

		log.info("--文件上传成功，上传人【{}】,userId:【{}】，源文件名【{}】，服务器文件路径【{}】！！--", uploadUserName, uploadUser.getUserId(),
				originalName, parent+"/"+fullCurrName);
		return ResponseEntity.ok().putData(module + "/" + fullCurrName);
	}

}
