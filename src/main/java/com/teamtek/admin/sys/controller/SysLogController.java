package com.teamtek.admin.sys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.teamtek.admin.sys.entity.SysLog;
import com.teamtek.core.ann.MenuOrBtnAnnotation;
import com.teamtek.core.controller.BaseController;
import com.teamtek.core.vo.DataGrid;
import com.teamtek.core.vo.Record;
import com.teamtek.core.vo.ResponseEntity;

/**
 * <p>
 * 系统日志表 前端控制器
 * </p>
 *
 * @author syj
 * @since 2018-03-12 15:57:00
 */
@RestController
@RequestMapping("/admin/sys/log")
@RequiresPermissions("sys_log")
@MenuOrBtnAnnotation(menu = "系统日志", code = "sys_log")
public class SysLogController extends BaseController<SysLog> {

	/**
	 * 分页查询
	 */
	public ResponseEntity datagrid(@RequestBody Record param) {
		// @RequestParam(name = "size", required = true) Integer size,
		// @RequestParam(name = "current", required = true) Integer current,
		// @RequestParam(name = "search", required = false) String search

		Validate.notNull(param.get("current"), "current不能为空");
		Validate.notNull(param.get("size"), "size不能为空");
		Integer current = Integer.valueOf(param.get("current").toString());
		Integer size = Integer.valueOf(param.get("size").toString());
		Page<SysLog> page = new Page<SysLog>(current, size);
		
		Wrapper<SysLog> ew = new EntityWrapper<>();
		if(StringUtils.isNotBlank(param.get("queryDate","").toString())){
			Date date;
			try {
				date = UTCToCST(param.get("queryDate","").toString(), "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			} catch (ParseException e) {
				throw new RuntimeException("queryDate格式错误，正确格式yyyy-MM-dd'T'HH:mm:ss.SSS'Z'（伦敦时间格式）");
			}
//			//当月
//			date=DateUtils.truncate(date, Calendar.MONTH);
//			Date end=DateUtils.addMonths(date, 1);
//			end=DateUtils.addSeconds(end, -1);
			//当天
			date=DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
			Date end=DateUtils.addDays(date, 1);
			end=DateUtils.addSeconds(end, -1);
			ew.between("oper_time", date, end);
		}
		ew.orderBy("oper_time",false);
		baseService.selectPage(page, ew);
		return ResponseEntity.ok().putData(createPaginationResult(new DataGrid<SysLog>(page)));
	}

	protected JSONObject toJSON(SysLog d) {
		JSONObject json = super.toJSON(d);
		if (d.getOperTime() != null)
			json.put("operTime", DateFormatUtils.format(d.getOperTime(), "yyyy-MM-dd HH:mm:ss"));
		return json;
	}

	/**
	 * 删除系统日志
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/del")
	public ResponseEntity del(@RequestBody SysLog data) {
		Validate.notNull(data.getId(), "id不能为空！");
		this.baseService.deleteById(data.getId());
		return ResponseEntity.ok();
	}
	/**
	 * 批量删除系统日志
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/delBatch")
	public ResponseEntity delBatch(@RequestBody Record param) {
		String ids = param.get("ids", "").toString();
		Validate.notBlank(ids, "ids不能为空！");
		this.baseService.deleteBatchIds(Arrays.asList(ids.split(",")));
		return ResponseEntity.ok();
	}
	
    private Date UTCToCST(String UTCStr, String format) throws ParseException {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        date = sdf.parse(UTCStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + 8);
        //calendar.getTime() 返回的是Date类型，也可以使用calendar.getTimeInMillis()获取时间戳
        return calendar.getTime();
    }
}
