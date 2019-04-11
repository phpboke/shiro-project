package pers.ken.shiro.chapter3;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

public class BaseTest {
	
	protected void login(String configFile, String username, String password) {
		Factory<org.apache.shiro.mgt.SecurityManager> factory = 
				new IniSecurityManagerFactory(configFile);
		
		org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject =  SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		
		subject.login(token);
	}
	
	public Subject subject(){
		return SecurityUtils.getSubject();
	}
}
