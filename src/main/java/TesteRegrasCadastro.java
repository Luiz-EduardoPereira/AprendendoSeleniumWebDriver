import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteRegrasCadastro {
	private static WebDriver driver;
	private CampoTreinamentoPage page;
	public String sobrenome;
	public String nome;
	public String sexo;
	public List<String> comidas;
	public String[] esportes;
	public String msg;
	@Before
	public void inicializar() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("file:///" + System.getProperty("user.dir")+ "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage(driver);
	}
	@After
	public void fecharBrowser() {
		driver.quit();
	}
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][] {
			
		});
	}
	public void validarRegras() {
		page.inserirNome(nome);
		page.inserirSobrenome(sobrenome);
		if (sexo.equals("Masculino")) {
			page.setSexoMasculino();
		} else if (sexo.equals("Feminino")) {
			page.setSexoFeminino();
		}
		if (comidas.contains("Carne")) page.setComidaCarne();
		if (comidas.contains("Pizza")) page.setComidaPizza();
		if (comidas.contains("Vegetariano")) page.setComidaVegetariano();
		page.setEsporte(esportes);
		page.cadastrar();
		Assert.assertEquals(msg, page.obterTextoAlertEAceitar());
	}
}
