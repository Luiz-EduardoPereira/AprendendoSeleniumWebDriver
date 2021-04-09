package br.ce.luiz.suites;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.luiz.test.Cadastro;
import br.ce.luiz.test.CampoTreinamento;
import br.ce.luiz.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	Cadastro.class,
	TesteRegrasCadastro.class,
	CampoTreinamento.class
})
public class SuiteTeste {
	
}
