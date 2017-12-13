package com.dayuanit.atm.db;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataBase {
	
	private static SqlSessionFactory ssf;
	
	static {
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
			ssf = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public static SqlSession getSession() {
		return ssf.openSession();
	}
	
	public static SqlSession getSession(boolean autuCommit) {
		return ssf.openSession(autuCommit);
	}

}
