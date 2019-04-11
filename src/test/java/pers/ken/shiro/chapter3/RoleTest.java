package pers.ken.shiro.chapter3;

import java.util.Arrays;

import javax.naming.spi.DirStateFactory.Result;

import junit.framework.Assert;

import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class RoleTest extends BaseTest {

	@Test
	public void testHasRole(){
		login("classpath:shiro-role.ini", "zhang", "123");
		//判断拥有角色 role1
		Assert.assertTrue(subject().hasRole("role1"));
		//判断用于角色role1、role2
		Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1","role2")));
		
		boolean[] result = subject().hasRoles(Arrays.asList("role1","role2","role3"));
		Assert.assertEquals(true, result[0]);
		Assert.assertEquals(true, result[1]);
		Assert.assertEquals(false, result[2]);
	}
	
	@Test
	public void testCheckRole(){
		login("classpath:shiro-role.ini", "zhang", "123");
		subject().checkRole("role1");
		subject().checkRoles("role1","role3");
	}
}
