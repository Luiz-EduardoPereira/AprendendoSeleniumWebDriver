package br.ce.luiz.test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	Cadastro.class,
	TesteRegrasCadastro.class,
	CampoTreinamento.class
})
public class SuiteTeste {
	
}
