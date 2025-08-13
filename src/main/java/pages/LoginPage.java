package pages;

import config.TestConfig;

/**
 * Page Object para la página de Login de Sauce Demo
 * Implementa el patrón Page Object Model
 */
public class LoginPage extends BasePage {
    
    // Selectores de elementos de la página de login
    private final String USERNAME_FIELD = "#user-name";
    private final String PASSWORD_FIELD = "#password";
    private final String LOGIN_BUTTON = "#login-button";
    private final String ERROR_MESSAGE = "[data-test='error']";
    private final String LOGO = ".login_logo";

    /**
     * Navegar a la página de login
     */
    public void navigateToLogin() {
        navigateTo(TestConfig.BASE_URL);
    }

    /**
     * Realizar login con credenciales específicas
     */
    public void login(String username, String password) {
        fillText(USERNAME_FIELD, username);
        fillText(PASSWORD_FIELD, password);
        clickElement(LOGIN_BUTTON);
    }

    /**
     * Login con usuario estándar
     */
    public void loginAsStandardUser() {
        login(TestConfig.STANDARD_USER, TestConfig.PASSWORD);
    }

    /**
     * Login con usuario bloqueado
     */
    public void loginAsLockedOutUser() {
        login(TestConfig.LOCKED_OUT_USER, TestConfig.PASSWORD);
    }

    /**
     * Verificar si hay un mensaje de error
     */
    public boolean isErrorMessageDisplayed() {
        return isElementVisible(ERROR_MESSAGE);
    }

    /**
     * Obtener el texto del mensaje de error
     */
    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    /**
     * Verificar que los campos están vacíos
     */
    public void clearLoginFields() {
        fillText(USERNAME_FIELD, "");
        fillText(PASSWORD_FIELD, "");
    }

    /**
     * Verificar si el botón de login está habilitado
     */
    public boolean isLoginButtonEnabled() {
        return !getElement(LOGIN_BUTTON).isDisabled();
    }

    /**
     * Verificar si la página de login está cargada
     */
    @Override
    public boolean isPageLoaded() {
        return isElementVisible(LOGO) && 
               isElementVisible(USERNAME_FIELD) && 
               isElementVisible(PASSWORD_FIELD) && 
               isElementVisible(LOGIN_BUTTON);
    }
}
