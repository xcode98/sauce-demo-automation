package utils;

import com.microsoft.playwright.*;
import config.TestConfig;

/**
 * Gestor del navegador siguiendo el patrón Singleton
 * Maneja la creación y gestión de instancias de Playwright
 */
public class BrowserManager {
    
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();
    private static Playwright playwright;

    /**
     * Inicializa el navegador con configuraciones optimizadas para macOS
     */
    public static void initializeBrowser() {
        try {
            if (playwright == null) {
                playwright = Playwright.create();
            }

            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(TestConfig.HEADLESS)
                .setSlowMo(TestConfig.SLOW_MO)
                .setTimeout(TestConfig.TIMEOUT_SECONDS * 1000)
                .setArgs(java.util.List.of(
                    "--no-sandbox",
                    "--disable-dev-shm-usage",
                    "--disable-web-security",
                    "--disable-features=VizDisplayCompositor",
                    "--use-mock-keychain",
                    "--no-first-run",
                    "--disable-background-timer-throttling"
                ));

            browser.set(playwright.chromium().launch(options));
            
            // Pausa para estabilidad en macOS
            Thread.sleep(1000);
            
            context.set(browser.get().newContext(new Browser.NewContextOptions()
                .setViewportSize(1280, 720)
                .setIgnoreHTTPSErrors(true)));
            
            // Pausa antes de crear página
            Thread.sleep(500);
            
            page.set(context.get().newPage());
            
        } catch (Exception e) {
            throw new RuntimeException("Error inicializando el navegador: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene la instancia actual de la página
     */
    public static Page getPage() {
        return page.get();
    }

    /**
     * Navega a una URL específica
     */
    public static void navigateTo(String url) {
        getPage().navigate(url);
    }

    /**
     * Cierra el navegador y limpia recursos
     */
    public static void closeBrowser() {
        try {
            if (page.get() != null) {
                page.get().close();
                page.remove();
            }
            if (context.get() != null) {
                context.get().close();
                context.remove();
            }
            if (browser.get() != null) {
                browser.get().close();
                browser.remove();
            }
        } catch (Exception e) {
            System.err.println("Error cerrando navegador: " + e.getMessage());
        }
    }

    /**
     * Cierra completamente Playwright
     */
    public static void closePlaywright() {
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
