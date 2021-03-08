package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {

	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp, String scorep) {
		// Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Seleccionamos el alumnos userOrder
		new Select(driver.findElement(By.id("user"))).selectByIndex(userOrder);
		// Rellenemos el campo de descripción
		WebElement description = driver.findElement(By.name("description"));
		description.clear();
		description.sendKeys(descriptionp);
		WebElement score = driver.findElement(By.name("score"));
		score.click();
		score.clear();
		score.sendKeys(scorep);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

	public static void login(WebDriver driver, String dni, String password) {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, dni, password);
		// COmprobamos que entramos en la pagina privada del usuario
		// PO_View.checkElement(driver, "text", "dni");
	}

	public static void logout(WebDriver driver) {
		// Ahora nos desconectamos
		PO_PrivateView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_LoginView.checkKey(driver, "login.message", PO_Properties.getSPANISH());
	}

	public static List<WebElement> clickMenuOption(WebDriver driver, String menuId, String optionHref) {
		// Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, '" + menuId + "')]/a");
		elementos.get(0).click();
		// Esperamos a aparezca la opción de añadir nota: //a[contains(@href,
		// 'mark/add')]
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, '" + optionHref + "')]");
		// Pinchamos en agregar Nota.
		elementos.get(0).click();
		return elementos;
	}

	static public void fillFormAddProfessor(WebDriver driver, String dnip, String nombrep, String apellidosp,
			String categoriap) {
		// Esperamos 5 segundos a que carge el DOM porque en algunos equipos falla
		SeleniumUtils.esperarSegundos(driver, 5);
		// Rellenemos el campo de dni
		WebElement dni = driver.findElement(By.name("dni"));
		dni.click();
		dni.clear();
		dni.sendKeys(dnip);
		// Rellenemos el campo de nombre
		WebElement nombre = driver.findElement(By.name("nombre"));
		nombre.click();
		nombre.clear();
		nombre.sendKeys(nombrep);
		// Rellenemos el campo de apellidos
		WebElement apellidos = driver.findElement(By.name("apellidos"));
		apellidos.click();
		apellidos.clear();
		apellidos.sendKeys(apellidosp);
		// Rellenemos el campo de categoria
		WebElement categoria = driver.findElement(By.name("categoria"));
		categoria.click();
		categoria.clear();
		categoria.sendKeys(categoriap);
		By boton = By.className("btn");
		driver.findElement(boton).click();
	}

}