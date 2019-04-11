package pers.ken.shiro.chapter2;

import com.alibaba.druid.pool.DruidDataSource;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniFactorySupport;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.sql.Connection;

public class LoginLogoutTest {

	/**
	 * @desc 通过ini文件写死Realm 信任数据
	 */
	@Test
	public void testHelloword(){
		//1、获取SecurityManager工厂，使用ini文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//2、获取SecurityManager实例并绑定给SecurityUtils
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//3、通过SecurityUtils得到Subject,并创建用户名/密码token
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		
		try {
			System.out.print(token);
			//4、登陆验证
			subject.login(token);
		} catch (Exception e) {
			//5、身份验证失败
			e.printStackTrace();
		}
		
		Assert.assertEquals(true, subject.isAuthenticated());
		//6、退出
		//subject.logout();
	}
	
	/**
	 * @desc 通过指定自定义的Realm指定信任数据
	 */
	@Test
	public void testCustomRealm(){
		//1、获取SecurityManager工厂，使用ini文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = 
				new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		//2、获取SecurityManager实例并绑定给SecurityUtil
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//3、通过SecurityUtil得到Subject,并创建token
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		
		try {
			//登陆成功
			subject.login(token);
			System.out.print(token);
		} catch (AuthenticationException e) {
			// 身份验证失败
			e.printStackTrace();
		}
		//判定用户已经登陆
		Assert.assertEquals(true, subject.isAuthenticated());
		//退出
		subject.logout();
	}
	
	/**
	 * @desc 指定多个维度的自定义 Realm 信任数据。通过这种方式可以验证不同数据表的用户信息
	 */
	@Test
	public void testCustomMutilRealm(){
		//1、获取SecurityManager工厂，使用ini文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = 
				new IniSecurityManagerFactory("classpath:shiro-mutil-realm.ini");
		//2、获取SecurityManager实例并绑定给SecurityUtil
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//3、通过SecurityUtil得到Subject,并创建token
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("wang","123");
		
		try {
			//登陆成功
			subject.login(token);
			System.out.print(token);
		} catch (AuthenticationException e) {
			// 身份验证失败
			e.printStackTrace();
		}
		//判定用户已经登陆
		Assert.assertEquals(true, subject.isAuthenticated());
		//退出
		subject.logout();
	}
	
	/**
	 * @desc 通过数据库获取到Realm信任数据
	 */
	@Test
	public void testJDBCRealm(){
		//1、获取SecurityManager工厂，使用ini文件初始化SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = 
				new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
		//2、获取SecurityManager实例并绑定给SecurityUtil
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		//3、通过SecurityUtil得到Subject,并创建token
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		
		try {
			//登陆成功
			subject.login(token);
			System.out.print(token);
		} catch (AuthenticationException e) {
			// 身份验证失败
			e.printStackTrace();
		}
		//判定用户已经登陆
		Assert.assertEquals(true, subject.isAuthenticated());
		//退出
		subject.logout();
	}
	
}
