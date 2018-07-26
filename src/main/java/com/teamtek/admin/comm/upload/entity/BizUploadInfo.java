package com.teamtek.admin.comm.upload.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author syj
 * @since 2018-03-08 08:56:01
 */
@TableName("biz_upload_info")
public class BizUploadInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long id;

	/**
	 * 文件原名
	 */
	@TableField(value = "original_name")
	private String originalName;

	/**
	 * 保存在服务器的文件名字
	 */
	@TableField(value = "curr_name")
	private String currName;

	/**
	 * 文件大小（字节）
	 */
	@TableField(value = "file_size")
	private Integer fileSize;

	/**
	 * 文件后缀
	 */
	@TableField(value = "file_suffix")
	private String fileSuffix;

	/**
	 * 文件在服务器硬盘的路径
	 */
	@TableField(value = "file_path")
	private String filePath;

	/**
	 * 文件上传者的id
	 */
	@TableField(value = "upload_user_id")
	private Long uploadUserId;

	/**
	 * 上传时间
	 */
	@TableField(value = "upload_time")
	private Date uploadTime;
	/**
	 * 上传时间
	 */
	@TableField(value = "from_module")
	private String fromModule;

	public BizUploadInfo() {
	}

	public BizUploadInfo(String originalName, String currName, Integer fileSize, String fileSuffix, String filePath,
			Long uploadUserId, Date uploadTime, String fromModule) {
		this.originalName = originalName;
		this.currName = currName;
		this.fileSize = fileSize;
		this.fileSuffix = fileSuffix;
		this.filePath = filePath;
		this.uploadUserId = uploadUserId;
		this.uploadTime = uploadTime;
		this.fromModule = fromModule;
	}

	public String getFromModule() {
		return fromModule;
	}

	public void setFromModule(String fromModule) {
		this.fromModule = fromModule;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getCurrName() {
		return currName;
	}

	public void setCurrName(String currName) {
		this.currName = currName;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getUploadUserId() {
		return uploadUserId;
	}

	public void setUploadUserId(Long uploadUserId) {
		this.uploadUserId = uploadUserId;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

}
