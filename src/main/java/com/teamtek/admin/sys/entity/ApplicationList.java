package com.teamtek.admin.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.sargeraswang.util.ExcelUtil.ExcelCell;

/**
 * <p>
 * 
 * </p>
 *
 * @author syj
 * @since 2018-05-10 10:09:34
 */
@TableName("application_list")
public class ApplicationList implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Id
	@TableId(type = IdType.UUID)
	private String objectid;

	/**
	 * 项目名称
	 */
	@ExcelCell(index = 1)
	@TableField(value = "Title")
	private String title;

	/**
	 * 申请人
	 */
	@TableField(value = "SQR")
	private String sqr;

	/**
	 * 申请部门
	 */
	@TableField(value = "SQBM")
	private String sqbm;

	/**
	 * 附件
	 */
	@TableField(value = "FJ")
	private String fj;

	/**
	 * 处理意见
	 */
	@TableField(value = "CLYJ")
	private String clyj;

	/**
	 * 申请人联系方式
	 */
	@TableField(value = "SQRLXFS")
	private String sqrlxfs;

	/**
	 * 区域
	 */
	@ExcelCell(index = 2)
	@TableField(value = "InstanceFlag")
	private String instanceflag;

	/**
	 * SSGF拟申请试点项目
	 */
	@ExcelCell(index = 3)
	@TableField(value = "SSGFNSQSDXM")
	private String ssgfnsqsdxm;

	/**
	 * 批次名称
	 */
	@TableField(value = "PCMC")
	private String pcmc;

	/**
	 * 楼层数范围
	 */
	@ExcelCell(index = 4)
	@TableField(value = "LCSFW")
	private String lcsfw;

	/**
	 * 待申请项目批次最早开工时间
	 */
	@TableField(value = "KGSJ")
	private Date kgsj;

	/**
	 * 里程碑交楼工期
	 */
	@TableField(value = "LCBJLGQ")
	private String lcbjlgq;

	/**
	 * 是否单独竣备
	 */
	@TableField(value = "SFDDJB")
	private String sfddjb;

	/**
	 * 省份/城市
	 */
	@ExcelCell(index = 5)
	@TableField(value = "SFCS")
	private String sfcs;

	/**
	 * 立项指引说明
	 */
	@TableField(value = "LXZYSM")
	private String lxzysm;

	/**
	 * 进展
	 */
	@TableField(value = "JZ")
	private String jz;

	/**
	 * 项目负责人
	 */
	@TableField(value = "XMFZR")
	private String xmfzr;

	/**
	 * 区域新体系推进团组长
	 */
	@TableField(value = "QYXTXTJTDZZ")
	private String qyxtxtjtdzz;

	/**
	 * 备注
	 */
	@TableField(value = "BZ")
	private String bz;

	/**
	 * 
	 */
	@TableField(value = "create_time")
	private String createTime;

	/**
	 * 项目信息
	 */
	@ExcelCell(index = 7)
	@TableField(value = "XMXX")
	private String xmxx;

	/**
	 * 拟申请批次（标段）楼栋、层数信息
	 */
	@TableField(value = "NSQPCLDCSXX")
	private String nsqpcldcsxx;

	/**
	 * 申报楼栋交付标准
	 */
	@TableField(value = "SBLDJFBZ")
	private String sbldjfbz;

	/**
	 * 是否具备精装修穿插施工验收条件
	 */
	@TableField(value = "SFJBJZX")
	private String sfjbjzx;

	/**
	 * 拟申请批次（标段）最早开工时间
	 */
	@ExcelCell(index = 6)
	@TableField(value = "NSQPCZZKGSJ")
	private String nsqpczzkgsj;

	/**
	 * 开工至竣备工期（传统）
	 */
	@TableField(value = "KGZJBRQ")
	private String kgzjbrq;

	/**
	 * 开工至竣备工期（SSGF）
	 */
	@TableField(value = "KGZJBRQSSGF")
	private String kgzjbrqssgf;

	/**
	 * 工期缩短时间
	 */
	@TableField(value = "GQSDSJ")
	private String gqsdsj;

	/**
	 * 设计进展
	 */
	@TableField(value = "SJJZ")
	private String sjjz;

	/**
	 * 招标进展
	 */
	@TableField(value = "ZBJZ")
	private String zbjz;

	/**
	 * 采购进展
	 */
	@TableField(value = "CGJZ")
	private String cgjz;

	/**
	 * 现场进展
	 */
	@TableField(value = "XCJZ")
	private String xcjz;

	/**
	 * 全天候工地开放日时间
	 */
	@TableField(value = "QTHGDKFRSJ")
	private Date qthgdkfrsj;

	/**
	 * 当地标杆观摩工地时间
	 */
	@TableField(value = "DDBGGMGDSJ")
	private Date ddbggmgdsj;

	/**
	 * 交付前工地开放日
	 */
	@TableField(value = "JFQGDKFR")
	private Date jfqgdkfr;

	/**
	 * 合同交付日期
	 */
	@TableField(value = "HRJFRQ")
	private Date hrjfrq;

	/**
	 * 申请表（22层及以上项目）
	 */
	@TableField(value = "SQB")
	private String sqb;

	/**
	 * 建筑面积(万m2)
	 */
	@ExcelCell(index = 8)
	@TableField(value = "JZMJ")
	private String jzmj;

	/**
	 * 
	 */
	@TableField(value = "is_enable")
	private String isEnable;

	/**
	 * 
	 */
	@TableField(value = "phone")
	private String phone;

	/**
	 * 
	 */
	@TableField(value = "jwd")
	private String jwd;

	/**
	 * 
	 */
	@ExcelCell(index = 9)
	@TableField(value = "project_shenbao")
	private String projectShenbao;

	/**
	 * h3审批数据后传过来的唯一标识的ID
	 */
	@TableField(value = "uuid")
	private String uuid;

	/**
	 * 
	 */
	@ExcelCell(index = 11)
	@TableField(value = "project_address")
	private String projectAddress;

	/**
	 * 
	 */
	@ExcelCell(index = 10)
	@TableField(value = "project_id")
	private String projectId;

	/**
	 * 省份
	 */
	@ExcelCell(index = 12)
	@TableField(value = "area")
	private String area;

	/**
	 * 城市
	 */
	@ExcelCell(index = 13)
	@TableField(value = "city")
	private String city;

	public String getObjectid() {
		return objectid;
	}

	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	public String getSqbm() {
		return sqbm;
	}

	public void setSqbm(String sqbm) {
		this.sqbm = sqbm;
	}

	public String getFj() {
		return fj;
	}

	public void setFj(String fj) {
		this.fj = fj;
	}

	public String getClyj() {
		return clyj;
	}

	public void setClyj(String clyj) {
		this.clyj = clyj;
	}

	public String getSqrlxfs() {
		return sqrlxfs;
	}

	public void setSqrlxfs(String sqrlxfs) {
		this.sqrlxfs = sqrlxfs;
	}

	public String getInstanceflag() {
		return instanceflag;
	}

	public void setInstanceflag(String instanceflag) {
		this.instanceflag = instanceflag;
	}

	public String getSsgfnsqsdxm() {
		return ssgfnsqsdxm;
	}

	public void setSsgfnsqsdxm(String ssgfnsqsdxm) {
		this.ssgfnsqsdxm = ssgfnsqsdxm;
	}

	public String getPcmc() {
		return pcmc;
	}

	public void setPcmc(String pcmc) {
		this.pcmc = pcmc;
	}

	public String getLcsfw() {
		return lcsfw;
	}

	public void setLcsfw(String lcsfw) {
		this.lcsfw = lcsfw;
	}

	public Date getKgsj() {
		return kgsj;
	}

	public void setKgsj(Date kgsj) {
		this.kgsj = kgsj;
	}

	public String getLcbjlgq() {
		return lcbjlgq;
	}

	public void setLcbjlgq(String lcbjlgq) {
		this.lcbjlgq = lcbjlgq;
	}

	public String getSfddjb() {
		return sfddjb;
	}

	public void setSfddjb(String sfddjb) {
		this.sfddjb = sfddjb;
	}

	public String getSfcs() {
		return sfcs;
	}

	public void setSfcs(String sfcs) {
		this.sfcs = sfcs;
	}

	public String getLxzysm() {
		return lxzysm;
	}

	public void setLxzysm(String lxzysm) {
		this.lxzysm = lxzysm;
	}

	public String getJz() {
		return jz;
	}

	public void setJz(String jz) {
		this.jz = jz;
	}

	public String getXmfzr() {
		return xmfzr;
	}

	public void setXmfzr(String xmfzr) {
		this.xmfzr = xmfzr;
	}

	public String getQyxtxtjtdzz() {
		return qyxtxtjtdzz;
	}

	public void setQyxtxtjtdzz(String qyxtxtjtdzz) {
		this.qyxtxtjtdzz = qyxtxtjtdzz;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getXmxx() {
		return xmxx;
	}

	public void setXmxx(String xmxx) {
		this.xmxx = xmxx;
	}

	public String getNsqpcldcsxx() {
		return nsqpcldcsxx;
	}

	public void setNsqpcldcsxx(String nsqpcldcsxx) {
		this.nsqpcldcsxx = nsqpcldcsxx;
	}

	public String getSbldjfbz() {
		return sbldjfbz;
	}

	public void setSbldjfbz(String sbldjfbz) {
		this.sbldjfbz = sbldjfbz;
	}

	public String getSfjbjzx() {
		return sfjbjzx;
	}

	public void setSfjbjzx(String sfjbjzx) {
		this.sfjbjzx = sfjbjzx;
	}

	public String getNsqpczzkgsj() {
		return nsqpczzkgsj;
	}

	public void setNsqpczzkgsj(String nsqpczzkgsj) {
		this.nsqpczzkgsj = nsqpczzkgsj;
	}

	public String getKgzjbrq() {
		return kgzjbrq;
	}

	public void setKgzjbrq(String kgzjbrq) {
		this.kgzjbrq = kgzjbrq;
	}

	public String getKgzjbrqssgf() {
		return kgzjbrqssgf;
	}

	public void setKgzjbrqssgf(String kgzjbrqssgf) {
		this.kgzjbrqssgf = kgzjbrqssgf;
	}

	public String getGqsdsj() {
		return gqsdsj;
	}

	public void setGqsdsj(String gqsdsj) {
		this.gqsdsj = gqsdsj;
	}

	public String getSjjz() {
		return sjjz;
	}

	public void setSjjz(String sjjz) {
		this.sjjz = sjjz;
	}

	public String getZbjz() {
		return zbjz;
	}

	public void setZbjz(String zbjz) {
		this.zbjz = zbjz;
	}

	public String getCgjz() {
		return cgjz;
	}

	public void setCgjz(String cgjz) {
		this.cgjz = cgjz;
	}

	public String getXcjz() {
		return xcjz;
	}

	public void setXcjz(String xcjz) {
		this.xcjz = xcjz;
	}

	public Date getQthgdkfrsj() {
		return qthgdkfrsj;
	}

	public void setQthgdkfrsj(Date qthgdkfrsj) {
		this.qthgdkfrsj = qthgdkfrsj;
	}

	public Date getDdbggmgdsj() {
		return ddbggmgdsj;
	}

	public void setDdbggmgdsj(Date ddbggmgdsj) {
		this.ddbggmgdsj = ddbggmgdsj;
	}

	public Date getJfqgdkfr() {
		return jfqgdkfr;
	}

	public void setJfqgdkfr(Date jfqgdkfr) {
		this.jfqgdkfr = jfqgdkfr;
	}

	public Date getHrjfrq() {
		return hrjfrq;
	}

	public void setHrjfrq(Date hrjfrq) {
		this.hrjfrq = hrjfrq;
	}

	public String getSqb() {
		return sqb;
	}

	public void setSqb(String sqb) {
		this.sqb = sqb;
	}

	public String getJzmj() {
		return jzmj;
	}

	public void setJzmj(String jzmj) {
		this.jzmj = jzmj;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJwd() {
		return jwd;
	}

	public void setJwd(String jwd) {
		this.jwd = jwd;
	}

	public String getProjectShenbao() {
		return projectShenbao;
	}

	public void setProjectShenbao(String projectShenbao) {
		this.projectShenbao = projectShenbao;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
