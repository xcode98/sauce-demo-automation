package stepDefinitions;

import io.cucumber.java.es.*;
import pages.LoginPage;
import pages.InventoryPage;
import config.TestConfig;
import static org.assertj.core.api.Assertions.*;

/**
 * Step definitions comunes utilizados en múltiples features
 */
public class CommonSteps {
    
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    public CommonSteps() {
        this.loginPage = new LoginPage();
        this.inventoryPage = new InventoryPage();
    }

    @Dado("que estoy autenticado como usuario estandar")
    public void queEstoyAutenticadoComoUsuarioEstandar() {
        loginPage.navigateToLogin();
        assertThat(loginPage.isPageLoaded())
            .as("La página de login debería estar cargada")
            .isTrue();
        
        loginPage.loginAsStandardUser();
        
        assertThat(inventoryPage.isPageLoaded())
            .as("Debería estar autenticado y en la página de productos")
            .isTrue();
        
        System.out.println("✅ Usuario estándar autenticado exitosamente");
    }

    @Dado("que estoy autenticado como usuario bloqueado")
    public void queEstoyAutenticadoComoUsuarioBloqueado() {
        loginPage.navigateToLogin();
        assertThat(loginPage.isPageLoaded())
            .as("La página de login debería estar cargada")
            .isTrue();
        
        loginPage.loginAsLockedOutUser();
        
        // Para usuario bloqueado, debería permanecer en login con error
        assertThat(loginPage.isErrorMessageDisplayed())
            .as("Debería mostrar error para usuario bloqueado")
            .isTrue();
        
        System.out.println("❌ Usuario bloqueado - login fallido como se esperaba");
    }
}
