package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorComplementariosTests {

	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\Andrea\\Desktop\\SegundoCuatrimestre\\SDI\\Lab\\L5. Web testing de un proyecto Spring Boot\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";

	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	} /* Resto del código de la clase */

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {

	}// Al finalizar la última prueba

	// Prueba 1. Registro de profesores con datos válidos.
	@Test
	public void Prueba1() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999988F", "123456");
		// COmprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Notas del usuario");

		// Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'professors-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de añadir nota: //a[contains(@href,
		// 'mark/add')]
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'professor/add')]");
		// Pinchamos en agregar Nota.
		elementos.get(0).click();

		PO_PrivateView.fillFormAddProfessor(driver, "12345678A", "Ejemplo", "Ejemplo", "Ejemplo");
		elementos = PO_View.checkElement(driver, "text", "Profesores");

		List<WebElement> profesores = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(profesores.size() == 1);

		PO_PrivateView.logout(driver);// ​
	}

	// Prueba 2. Registro de profesores con datos inválidos (nombre y categoría
	// inválidos).
	@Test
	public void Prueba2() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999988F", "123456");
		// COmprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Notas del usuario");

		// Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'professors-menu')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de añadir nota: //a[contains(@href,
		// 'mark/add')]
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'professor/add')]");
		// Pinchamos en agregar Nota.
		elementos.get(0).click();

		PO_PrivateView.fillFormAddProfessor(driver, "12345678A", "Ejem", "Ejemplo", "Ejemplo");
		PO_RegisterView.checkKey(driver, "Error.professor.name.length", PO_Properties.getSPANISH());

		PO_PrivateView.fillFormAddProfessor(driver, "12345678A", "Ejemplooooooooooooooooooooooooooooo", "Ejemplo",
				"Ejemplo");
		PO_RegisterView.checkKey(driver, "Error.professor.name.length", PO_Properties.getSPANISH());

		PO_PrivateView.fillFormAddProfessor(driver, "12345678A", "Ejemplo", "Ejemplo", "Ejem");
		PO_RegisterView.checkKey(driver, "Error.professor.name.length", PO_Properties.getSPANISH());

		PO_PrivateView.fillFormAddProfessor(driver, "12345678A", "Ejemplo", "Ejemplo",
				"Ejemploooooooooooooooooooooooooooooooo");
		PO_RegisterView.checkKey(driver, "Error.professor.categoria.length", PO_Properties.getSPANISH());

		PO_PrivateView.logout(driver);// ​
	}

	// Prueba 3. Verificar que solo los usuarios autorizados pueden dar de alta un
	// profesor.
	@Test
	public void Prueba3() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456");
		// COmprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Notas del usuario");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'professors-menu')]/a");
		elementos.get(0).click();

		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Agregar Profesor", PO_View.getTimeout());

		PO_PrivateView.logout(driver);// ​
	}

	@AfterClass
	static public void end() { // Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

}
