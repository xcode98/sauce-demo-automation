package pages;

import java.util.List;

/**
 * Page Object para la página de Resumen del Checkout de Sauce Demo
 */
public class CheckoutOverviewPage extends BasePage {
    
    // Selectores de elementos
    private final String CHECKOUT_SUMMARY_CONTAINER = "#checkout_summary_container";
    private final String CART_ITEMS = ".cart_item";
    private final String CART_ITEM_NAME = ".inventory_item_name";
    private final String CART_ITEM_PRICE = ".inventory_item_price";
    private final String SUBTOTAL = ".summary_subtotal_label";
    private final String TAX = ".summary_tax_label";
    private final String TOTAL = ".summary_total_label";
    private final String FINISH_BUTTON = "#finish";
    private final String CANCEL_BUTTON = "#cancel";

    /**
     * Verificar si la página de resumen está cargada
     */
    @Override
    public boolean isPageLoaded() {
        return isElementVisible(CHECKOUT_SUMMARY_CONTAINER) && 
               isElementVisible(SUBTOTAL) && 
               isElementVisible(TOTAL) && 
               isElementVisible(FINISH_BUTTON);
    }

    /**
     * Obtener lista de productos en el resumen
     */
    public List<String> getOrderItems() {
        return page.locator(CART_ITEM_NAME).allTextContents();
    }

    /**
     * Obtener subtotal
     */
    public String getSubtotal() {
        return getText(SUBTOTAL);
    }

    /**
     * Obtener impuestos
     */
    public String getTax() {
        return getText(TAX);
    }

    /**
     * Obtener total
     */
    public String getTotal() {
        return getText(TOTAL);
    }

    /**
     * Finalizar la compra
     */
    public void finishOrder() {
        clickElement(FINISH_BUTTON);
    }

    /**
     * Cancelar la orden
     */
    public void cancelOrder() {
        clickElement(CANCEL_BUTTON);
    }

    /**
     * Verificar si un producto está en el resumen
     */
    public boolean isProductInSummary(String productName) {
        return getOrderItems().contains(productName);
    }

    /**
     * Obtener número de productos en el resumen
     */
    public int getOrderItemCount() {
        return page.locator(CART_ITEMS).count();
    }
}
