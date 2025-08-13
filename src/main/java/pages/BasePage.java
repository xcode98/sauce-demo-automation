package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.BrowserManager;

/**
 * Clase base para todas las páginas - implementa el patrón Page Object Model
 * Contiene métodos comunes para interactuar con elementos
 */
public abstract class BasePage {
    
    protected Page page;

    public BasePage() {
        this.page = BrowserManager.getPage();
    }

    /**
     * Navegar a una URL específica
     */
    protected void navigateTo(String url) {
        page.navigate(url);
    }

    /**
     * Hacer clic en un elemento
     */
    protected void clickElement(String selector) {
        page.locator(selector).click();
    }

    /**
     * Escribir texto en un campo
     */
    protected void fillText(String selector, String text) {
        page.locator(selector).fill(text);
    }

    /**
     * Obtener texto de un elemento
     */
    protected String getText(String selector) {
        return page.locator(selector).textContent();
    }

    /**
     * Verificar si un elemento está visible
     */
    protected boolean isElementVisible(String selector) {
        return page.locator(selector).isVisible();
    }

    /**
     * Esperar que un elemento sea visible
     */
    protected void waitForElement(String selector) {
        page.locator(selector).waitFor();
    }

    /**
     * Presionar una tecla en un elemento
     */
    protected void pressKey(String selector, String key) {
        page.locator(selector).press(key);
    }

    /**
     * Obtener un locator de Playwright
     */
    protected Locator getElement(String selector) {
        return page.locator(selector);
    }

    /**
     * Esperar un tiempo específico
     */
    protected void waitFor(int milliseconds) {
        page.waitForTimeout(milliseconds);
    }

    /**
     * Obtener el título de la página
     */
    public String getPageTitle() {
        return page.title();
    }

    /**
     * Obtener la URL actual
     */
    public String getCurrentUrl() {
        return page.url();
    }

    /**
     * Verificar si estamos en la página correcta
     */
    public abstract boolean isPageLoaded();
}
