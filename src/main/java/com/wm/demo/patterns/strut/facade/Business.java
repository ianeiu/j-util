package com.wm.demo.patterns.strut.facade;
/**
 * 示意生成逻辑层的模块
 * @author dream
 *
 */
public class Business {

	public void generate(){
		//1:从配置管理里面获取相应的配置信息
		ConfigModel cm = ConfigManager.getInstance().getConfigData();
		if(cm.isNeedGenBusiness()){
			System.out.println("正在生成逻辑层代码文件");
		}
	}
}
