package pers.ken.shiro.chapter3;

import org.junit.Test;

import junit.framework.Assert;

public class PermissionTest extends BaseTest {

	@Test
	public void testIsPermitted(){
		login("classpath:shiro-permission.ini", "zhang", "123");
		
		Assert.assertTrue(subject().isPermitted("user:create"));
		
		Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
		
		Assert.assertFalse(subject().isPermitted("user:view"));
	}
	
	@Test
	public void testCheckPermission(){
		login("classpath:shiro-permission.ini", "zhang", "123");
		
		subject().checkPermission("user:create");
		
		subject().checkPermissions("user:update", "user:delete");
		
		subject().checkPermission("user:view");
	}
}
