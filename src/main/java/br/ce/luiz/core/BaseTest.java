package br.ce.luiz.core;

import static br.ce.luiz.core.DriverFactory.getDriver;
import static br.ce.luiz.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BaseTest {
	@Rule
	public TestName nomeTeste = new TestName();
	
	@After
	public void fecharBrowser() throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) getDriver();
		File print = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(print, new File("target"+File.separator+"screenshot"+File.separator+nomeTeste.getMethodName()+".jpg"));
		
		if (Propriedades.FECHAR_BROWSER) {
				killDriver();
		}
	}
}
