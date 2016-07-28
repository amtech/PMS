package com.cjhb.main;/*
* Copyright (c) 2016 www.51cjhb.com. All Rights Reserved.
*/
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>Title:逆向工程执行类 </p>
 * <p>Description:运行官方提供的GeneratorSqlmap.java这个程序，生成对应的model和mapper </p>
 * @author edgar
 * @version 1.0
 * @create 2016 07 21 10:56
 */
public class GeneratorSqlmap {

	public void generator() throws Exception{

		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;

		//执行generatorConfig.xml配置文件
		File configFile = new File("D:\\Develop\\git\\repository\\github\\PMS\\mybatis\\src\\main\\resources\\generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
				callback, warnings);
		myBatisGenerator.generate(null);

	}

	//程序入口
	public static void main(String[] args) throws Exception {
		try {
			GeneratorSqlmap generatorSqlmap = new GeneratorSqlmap();
			generatorSqlmap.generator();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
