**项目** 
	druid 监控  
			http://localhost:8088/druid/index.html
			用户名：admin
			密码:  admin

	lody 监控 
			 http://localhost:8088/lody/monitoring
			用户名：admin
			密码:  admin
	
	mybatis-plus 注意：
 			当用  2.0.8 版本时  批量插入方法 有bug 会报错  
 			目前 此框架用的 较新的 2.1.6(目前最新版本2.1.8)  批处理  没报错 
 			建议 ：mybatis-plus是第三方的  ，当调用像批处理 这些第三方封装的 方法 时，要留意！！
 			
	前后端分离 注意 ：
   			 token存活时间  应 略小于  shrio 会话过期时间，
           	否则会出现  用户还在线，但是权限过期了！！
           	当前 shrio有效期 比 token 长5分钟

	数据库注意：
		项目配置两套环境   ：开发环境  和  生产环境   ，每个环境用的数据库不一样  
		详情见：application-dev.properties和 application-prod.properties
	菜单基础数据：
		sys_menu.sql是佛山邮储积分系统 菜单 数据，如果项目菜单被改凌乱了，可导入此文件还原