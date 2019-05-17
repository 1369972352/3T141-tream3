package cn.smbms.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
	private static SqlSessionFactory factory;
	static{
		try {
			//1获取mybatis-config.xml的输入流
			InputStream is;
			is = Resources.getResourceAsStream("mybatis-config.xml");
			//2.创建SqlSessionFactory对象，完成对配置文件的读取
			factory= new SqlSessionFactoryBuilder().build(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public static SqlSession createSqlession(){
		return factory.openSession(true);
	}
	public static void closeSqlSession(SqlSession sqlSession){
		if(null!=sqlSession){
			sqlSession.close();
		}
		
	}
	
}
