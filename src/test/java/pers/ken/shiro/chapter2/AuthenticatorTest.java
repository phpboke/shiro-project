package pers.ken.shiro.chapter2;

import junit.framework.Assert;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AuthenticatorTest {

	/**
	 * @desc 只有所有Realm成功后才返回。验证success情况
	 */
	@Test
	public void testAllSuccessfulStrategyWithSuccess(){
		login("classpath:shiro-authenticator-all-success.ini");
		Subject subject = SecurityUtils.getSubject();
		
		//获身份集合，其中包含了Realm身份验证成功的信息
		PrincipalCollection principalCollection = subject.getPrincipals();
		Assert.assertEquals(2, principalCollection.asList().size());
	}
	
	/**
	 * @desc 只有所有Realm成功后才返回。验证fail情况
	 */
	@Test(expected = UnknownAccountException.class)
	public void testAllSuccessfulStrategyWithFail(){
		login("classpath:shiro-authenticator-all-fail.ini");
	}
	
	/**
	 * 只要有一个验证成功，则返回所有验证成功的账号信息
	 */
	@Test
	public void testAtLeastOneSuccessfulStrategyWithSuccess(){
		 login("classpath:shiro-authenticator-atLeastOne-success.ini");
         Subject subject = SecurityUtils.getSubject();
         
         PrincipalCollection principalCollection = subject.getPrincipals();
         Assert.assertEquals(2, principalCollection.asList().size());
	}
	
	/**
	 * 返回第一个验证成功的账号信息
	 */
	@Test
	public void testFirstOneSuccessfulStrategyWithSuccess(){
		login("classpath:shiro-authenticator-first-success.ini");
		Subject subject = SecurityUtils.getSubject();

		PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(1, principalCollection.asList().size());
	}
	/**
	 * @desc 登陆模块
	 */
	private void login(String configFile){
		Factory<org.apache.shiro.mgt.SecurityManager>factory =
				new IniSecurityManagerFactory(configFile);
		
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
		
		subject.login(token);
	}
}
