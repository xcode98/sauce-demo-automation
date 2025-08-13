package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Scenario;
import utils.BrowserManager;
import config.TestConfig;

/**
 * Hooks para setup y teardown de los tests
 */
public class Hooks {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("🚀 Iniciando Suite de Tests de Sauce Demo");
        System.out.println("📝 Configuración: Java 15 + Playwright + Cucumber");
        System.out.println("🌐 Target URL: " + TestConfig.BASE_URL);
    }

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("⚡ Iniciando escenario: " + scenario.getName());
        try {
            BrowserManager.initializeBrowser();
            System.out.println("✅ Navegador iniciado exitosamente");
        } catch (Exception e) {
            System.err.println("❌ Error iniciando navegador: " + e.getMessage());
            throw new RuntimeException("No se pudo inicializar el navegador", e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                System.out.println("❌ Escenario falló: " + scenario.getName());
                // Aquí podrías agregar captura de pantalla
                takeScreenshot(scenario);
            } else {
                System.out.println("✅ Escenario exitoso: " + scenario.getName());
            }
        } finally {
            System.out.println("🔄 Cerrando navegador...");
            BrowserManager.closeBrowser();
        }
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("🎯 Finalizando Suite de Tests");
        BrowserManager.closePlaywright();
        System.out.println("✅ Tests completados");
    }

    /**
     * Tomar captura de pantalla en caso de fallo
     */
    private void takeScreenshot(Scenario scenario) {
        try {
            if (BrowserManager.getPage() != null) {
                byte[] screenshot = BrowserManager.getPage().screenshot();
                scenario.attach(screenshot, "image/png", "Screenshot");
                System.out.println("📸 Captura de pantalla adjuntada al reporte");
            }
        } catch (Exception e) {
            System.err.println("❌ Error tomando captura de pantalla: " + e.getMessage());
        }
    }
}
