package pages;

import config.TestConfig;

/**
 * Page Object para la página de Checkout (Información del Cliente) de Sauce Demo
 */
public class CheckoutPage extends BasePage {
    
    // Selectores de elementos
    private final String CHECKOUT_CONTAINER = "#checkout_info_container";
    private final String FIRST_NAME_FIELD = "#first-name";
    private final String LAST_NAME_FIELD = "#last-name";
    private final String ZIP_CODE_FIELD = "#postal-code";
    private final String CONTINUE_BUTTON = "#continue";
    private final String CANCEL_BUTTON = "#cancel";
    private final String ERROR_MESSAGE = "[data-test='error']";

    /**
     * Verificar si la página de checkout está cargada
     */
    @Override
    public boolean isPageLoaded() {
        return isElementVisible(CHECKOUT_CONTAINER) && 
               isElementVisible(FIRST_NAME_FIELD) && 
               isElementVisible(LAST_NAME_FIELD) && 
               isElementVisible(ZIP_CODE_FIELD);
    }

    /**
     * Llenar información del cliente
     */
    public void fillCustomerInformation(String firstName, String lastName, String zipCode) {
        fillText(FIRST_NAME_FIELD, firstName);
        fillText(LAST_NAME_FIELD, lastName);
        fillText(ZIP_CODE_FIELD, zipCode);
    }

    /**
     * Llenar información con datos de prueba por defecto
     */
    public void fillDefaultCustomerInformation() {
        fillCustomerInformation(
            TestConfig.TEST_FIRST_NAME, 
            TestConfig.TEST_LAST_NAME, 
            TestConfig.TEST_ZIP_CODE
        );
    }

    /**
     * Continuar al siguiente paso del checkout
     */
    public void continueCheckout() {
        clickElement(CONTINUE_BUTTON);
    }

    /**
     * Cancelar el checkout
     */
    public void cancelCheckout() {
        clickElement(CANCEL_BUTTON);
    }

    /**
     * Llenar información y continuar
     */
    public void completeCustomerInformation() {
        fillDefaultCustomerInformation();
        continueCheckout();
    }

    /**
     * Verificar si hay mensaje de error
     */
    public boolean isErrorMessageDisplayed() {
        return isElementVisible(ERROR_MESSAGE);
    }

    /**
     * Obtener mensaje de error
     */
    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    /**
     * Verificar si todos los campos están llenos
     */
    public boolean areAllFieldsFilled() {
        return !getElement(FIRST_NAME_FIELD).inputValue().isEmpty() &&
               !getElement(LAST_NAME_FIELD).inputValue().isEmpty() &&
               !getElement(ZIP_CODE_FIELD).inputValue().isEmpty();
    }
}
