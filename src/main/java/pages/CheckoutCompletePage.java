package pages;

/**
 * Page Object para la página de Confirmación de Compra de Sauce Demo
 */
public class CheckoutCompletePage extends BasePage {
    
    // Selectores de elementos
    private final String CHECKOUT_COMPLETE_CONTAINER = "#checkout_complete_container";
    private final String SUCCESS_HEADER = ".complete-header";
    private final String SUCCESS_TEXT = ".complete-text";
    private final String BACK_HOME_BUTTON = "#back-to-products";
    private final String PONY_EXPRESS_IMAGE = ".pony_express";

    /**
     * Verificar si la página de confirmación está cargada
     */
    @Override
    public boolean isPageLoaded() {
        return isElementVisible(CHECKOUT_COMPLETE_CONTAINER) && 
               isElementVisible(SUCCESS_HEADER) && 
               isElementVisible(BACK_HOME_BUTTON);
    }

    /**
     * Obtener el mensaje de éxito del header
     */
    public String getSuccessHeader() {
        return getText(SUCCESS_HEADER);
    }

    /**
     * Obtener el texto de confirmación
     */
    public String getSuccessText() {
        return getText(SUCCESS_TEXT);
    }

    /**
     * Verificar si la compra fue exitosa
     */
    public boolean isPurchaseSuccessful() {
        return isPageLoaded() && 
               getSuccessHeader().contains("Thank you for your order") ||
               getSuccessHeader().contains("THANK YOU FOR YOUR ORDER");
    }

    /**
     * Volver a la página de productos
     */
    public void backToProducts() {
        clickElement(BACK_HOME_BUTTON);
    }

    /**
     * Verificar si la imagen de confirmación está presente
     */
    public boolean isConfirmationImageDisplayed() {
        return isElementVisible(PONY_EXPRESS_IMAGE);
    }
}
