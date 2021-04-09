package br.ce.luiz.suites;
import static br.ce.luiz.core.DriverFactory.killDriver;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.luiz.test.Cadastro;
import br.ce.luiz.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	Cadastro.class,
	TesteRegrasCadastro.class
	//CampoTreinamento.class
})
public class SuiteTeste {
	@AfterClass
	public static void finalizaTudo() {
		killDriver();
	}
}
