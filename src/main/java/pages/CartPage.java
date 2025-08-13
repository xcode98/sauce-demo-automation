package pages;

import java.util.List;

/**
 * Page Object para la página del Carrito de Compras de Sauce Demo
 */
public class CartPage extends BasePage {
    
    // Selectores de elementos
    private final String CART_CONTAINER = "#cart_contents_container";
    private final String CART_ITEMS = ".cart_item";
    private final String CART_ITEM_NAME = ".inventory_item_name";
    private final String CART_ITEM_PRICE = ".inventory_item_price";
    private final String REMOVE_BUTTON = ".btn_secondary";
    private final String CONTINUE_SHOPPING_BUTTON = "#continue-shopping";
    private final String CHECKOUT_BUTTON = "#checkout";
    private final String CART_QUANTITY = ".cart_quantity";

    /**
     * Verificar si la página del carrito está cargada
     */
    @Override
    public boolean isPageLoaded() {
        return isElementVisible(CART_CONTAINER) && 
               isElementVisible(CONTINUE_SHOPPING_BUTTON) && 
               isElementVisible(CHECKOUT_BUTTON);
    }

    /**
     * Obtener lista de productos en el carrito
     */
    public List<String> getCartItems() {
        return page.locator(CART_ITEM_NAME).allTextContents();
    }

    /**
     * Obtener número de productos en el carrito
     */
    public int getCartItemCount() {
        return page.locator(CART_ITEMS).count();
    }

    /**
     * Verificar si el carrito está vacío
     */
    public boolean isCartEmpty() {
        return getCartItemCount() == 0;
    }

    /**
     * Verificar si un producto específico está en el carrito
     */
    public boolean isProductInCart(String productName) {
        return getCartItems().contains(productName);
    }

    /**
     * Remover producto del carrito por nombre
     */
    public void removeProductFromCart(String productName) {
        var cartItems = page.locator(CART_ITEMS);
        for (int i = 0; i < cartItems.count(); i++) {
            var cartItem = cartItems.nth(i);
            var nameElement = cartItem.locator(CART_ITEM_NAME);
            if (nameElement.textContent().equals(productName)) {
                cartItem.locator(REMOVE_BUTTON).click();
                break;
            }
        }
    }

    /**
     * Obtener precio de un producto en el carrito
     */
    public String getProductPrice(String productName) {
        var cartItems = page.locator(CART_ITEMS);
        for (int i = 0; i < cartItems.count(); i++) {
            var cartItem = cartItems.nth(i);
            var nameElement = cartItem.locator(CART_ITEM_NAME);
            if (nameElement.textContent().equals(productName)) {
                return cartItem.locator(CART_ITEM_PRICE).textContent();
            }
        }
        return "";
    }

    /**
     * Obtener cantidad de un producto específico
     */
    public int getProductQuantity(String productName) {
        var cartItems = page.locator(CART_ITEMS);
        for (int i = 0; i < cartItems.count(); i++) {
            var cartItem = cartItems.nth(i);
            var nameElement = cartItem.locator(CART_ITEM_NAME);
            if (nameElement.textContent().equals(productName)) {
                return Integer.parseInt(cartItem.locator(CART_QUANTITY).textContent());
            }
        }
        return 0;
    }

    /**
     * Continuar comprando (volver a inventario)
     */
    public void continueShopping() {
        clickElement(CONTINUE_SHOPPING_BUTTON);
    }

    /**
     * Proceder al checkout
     */
    public void proceedToCheckout() {
        clickElement(CHECKOUT_BUTTON);
    }
}
