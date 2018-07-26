package com.teamtek.admin.comm.upload.filepath;

import java.util.ArrayList;
import java.util.List;

import com.teamtek.core.vo.Record;

public interface ExcelDownloadPath {
	/**
	 * 定义excel 导入模板路径
	 * 为了 兼容起见  excel 为2003版的
	 * @author syj
	 * @createTime：2018年3月21日 上午8:56:24
	 */
	public enum ExcelPath {

		user_import_excel(1,"/excel/user_import.xls", "用户导入模板.xls"), 
		project_import_excel(2,"/excel/project_import.xls","项目导入模板.xls"),
		score_data_import_excel(3,"/excel/score_data_import.xls", "业务数据导入模板.xls"),;
		private final int type;
		private final String path;
		private final String title;
		private ExcelPath(int type,String path, String title) {
			this.path = path;
			this.title = title;
			this.type = type;
		}

		public int type() {
			return type;
		}

		public String path() {
			return path;
		}


		public String title() {
			return title;
		}

		public static List<Record> getList() {
			List<Record> list = new ArrayList<>(ExcelPath.values().length);
			for (ExcelPath s : ExcelPath.values()) {
				Record r = new Record();
				r.set("type", s.type());
				r.set("title", s.title());
				list.add(r);
			}
			return list;
		}
		public static String getPathByType(int type) {
			for (ExcelPath s : ExcelPath.values()) {
				if(s.type()==type){
					return s.path();
				}
			}
			return null;
		}
		
		public static String getTitleByType(int type) {
			for (ExcelPath s : ExcelPath.values()) {
				if(s.type()==type){
					return s.title();
				}
			}
			return null;
		}
	}

}
