package stepDefinitions;

import io.cucumber.java.es.*;
import io.cucumber.datatable.DataTable;
import pages.LoginPage;
import pages.InventoryPage;
import static org.assertj.core.api.Assertions.*;

/**
 * Step definitions para las funcionalidades de Login
 */
public class LoginSteps {
    
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    public LoginSteps() {
        this.loginPage = new LoginPage();
        this.inventoryPage = new InventoryPage();
    }

    @Dado("que estoy en la pagina de login de Sauce Demo")
    public void que_estoy_en_la_pagina_de_login_de_sauce_demo() {
        loginPage.navigateToLogin();
        assertThat(loginPage.isPageLoaded())
            .as("La página de login debería estar cargada")
            .isTrue();
    }

    @Cuando("ingreso las credenciales del usuario estandar")
    public void ingreso_las_credenciales_del_usuario_estandar() {
        loginPage.loginAsStandardUser();
    }

    @Cuando("ingreso las credenciales del usuario bloqueado")
    public void ingreso_las_credenciales_del_usuario_bloqueado() {
        loginPage.loginAsLockedOutUser();
    }

    @Cuando("ingreso credenciales inválidas")
    public void ingresoCredencialesInvalidas(DataTable dataTable) {
        var credentials = dataTable.asMaps();
        var credential = credentials.get(0);
        loginPage.login(credential.get("usuario"), credential.get("contraseña"));
    }

    @Cuando("intento hacer login sin ingresar credenciales")
    public void intentoHacerLoginSinIngresarCredenciales() {
        loginPage.clearLoginFields();
        // Usar método público de la página
        loginPage.login("", "");
    }

    @Entonces("deberia ser redirigido a la pagina de productos")
    public void deberia_ser_redirigido_a_la_pagina_de_productos() {
        assertThat(inventoryPage.isPageLoaded())
            .as("Debería estar en la página de productos después del login")
            .isTrue();
    }

    @Entonces("deberia ver la lista de productos disponibles")
    public void deberia_ver_la_lista_de_productos_disponibles() {
        assertThat(inventoryPage.getProducts())
            .as("Debería haber productos disponibles")
            .isNotEmpty()
            .hasSizeGreaterThan(0);
    }

    @Entonces("deberia ver un mensaje de error")
    public void deberia_ver_un_mensaje_de_error() {
        assertThat(loginPage.isErrorMessageDisplayed())
            .as("Debería mostrar un mensaje de error")
            .isTrue();
        
        assertThat(loginPage.getErrorMessage())
            .as("El mensaje de error no debería estar vacío")
            .isNotEmpty();
    }

    @Entonces("debería ver un mensaje de error")
    public void debería_ver_un_mensaje_de_error() {
        deberia_ver_un_mensaje_de_error();
    }

    @Entonces("deberia permanecer en la pagina de login")
    public void deberia_permanecer_en_la_pagina_de_login() {
        assertThat(loginPage.isPageLoaded())
            .as("Debería permanecer en la página de login")
            .isTrue();
    }

    @Entonces("debería permanecer en la página de login")
    public void debería_permanecer_en_la_página_de_login() {
        deberia_permanecer_en_la_pagina_de_login();
    }

    @Entonces("debería ver un mensaje de error indicando campos requeridos")
    public void deberiaVerUnMensajeDeErrorIndicandoCamposRequeridos() {
        assertThat(loginPage.isErrorMessageDisplayed())
            .as("Debería mostrar un mensaje de error para campos requeridos")
            .isTrue();
        
        String errorMessage = loginPage.getErrorMessage().toLowerCase();
        assertThat(errorMessage)
            .as("El mensaje debería indicar que se requiere un username")
            .contains("username is required");
    }
}
