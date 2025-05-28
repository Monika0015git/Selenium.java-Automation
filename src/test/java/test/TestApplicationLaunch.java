package test;

import org.testng.annotations.Test;

import base.BaseTest;
import pom.HomePage;

public class TestApplicationLaunch extends BaseTest{
	
	private HomePage home;
	@Test
	public void testLaunch()
	{
		home = new HomePage(driver, wait, actions, js);
		home.verifyLaunchApplication();
	}
}
