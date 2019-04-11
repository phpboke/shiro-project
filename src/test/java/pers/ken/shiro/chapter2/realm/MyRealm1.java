package pers.ken.shiro.chapter2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {

	/**
	 * @desc 返回一个唯一的 Realm 名称
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "MyRealm1";
	}

	/**
	 * @desc 判断此Realm 是否支持token
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		// 仅支持 UsernamePasswordToken 类型的Token
		return token instanceof UsernamePasswordToken;
	}

	/**
	 * @desc 根据Token获取认证信息
	 */
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String username = (String)token.getPrincipal();//获取用户名
		String password = new String((char[])token.getCredentials());//获取密码
		
		if (!"zhang".equals(username)) {
			throw new UnknownAccountException();//用户名错误
		}
		if (!"123".equals(password)) {
			throw new IncorrectCredentialsException();//密码错误
		}
		//身份验证成，返回一个 AuthenticationInfo对象
		return new SimpleAuthenticationInfo(username, password, getName());
	}



}
