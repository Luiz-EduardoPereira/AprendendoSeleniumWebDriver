package br.ce.luiz.core;

import static br.ce.luiz.core.DriverFactory.killDriver;

import org.junit.After;

public class BaseTest {
	@After
	public void fecharBrowser() {
		if (Propriedades.FECHAR_BROWSER) {
				killDriver();
		}
	}
}
