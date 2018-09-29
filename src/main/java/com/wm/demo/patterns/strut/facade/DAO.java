package com.wm.demo.patterns.strut.facade;

/**
 * 示意生成数据库层的模块
 * @author dream
 *
 */
public class DAO {

	public void generate(){
		//1:从配置管理里面获取相应的配置信息
		ConfigModel cm = ConfigManager.getInstance().getConfigData();
		if(cm.isNeedGenDAO()){
			//2:按照要求去生成相应的代码，并保存成文件
			System.out.println("正在生成数据层代码文件");
		}
	}
}
