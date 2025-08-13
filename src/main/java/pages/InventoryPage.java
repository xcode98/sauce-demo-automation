package pages;

import java.util.List;

/**
 * Page Object para la página de Inventario/Productos de Sauce Demo
 */
public class InventoryPage extends BasePage {
    
    // Selectores de elementos
    private final String INVENTORY_CONTAINER = ".inventory_container";
    private final String PRODUCT_ITEMS = ".inventory_item";
    private final String PRODUCT_NAME = ".inventory_item_name";
    private final String PRODUCT_PRICE = ".inventory_item_price";
    private final String ADD_TO_CART_BUTTON = ".btn_inventory";
    private final String SHOPPING_CART_LINK = ".shopping_cart_link";
    private final String SHOPPING_CART_BADGE = ".shopping_cart_badge";
    private final String BURGER_MENU = "#react-burger-menu-btn";
    private final String LOGOUT_LINK = "#logout_sidebar_link";
    
    // Productos específicos
    private final String SAUCE_LABS_BACKPACK_ADD_BUTTON = "#add-to-cart-sauce-labs-backpack";
    private final String SAUCE_LABS_BIKE_LIGHT_ADD_BUTTON = "#add-to-cart-sauce-labs-bike-light";
    private final String SAUCE_LABS_BOLT_TSHIRT_ADD_BUTTON = "#add-to-cart-sauce-labs-bolt-t-shirt";

    /**
     * Verificar si la página de inventario está cargada
     */
    @Override
    public boolean isPageLoaded() {
        return isElementVisible(INVENTORY_CONTAINER) && 
               getProducts().size() > 0;
    }

    /**
     * Obtener lista de productos
     */
    public List<String> getProducts() {
        return page.locator(PRODUCT_NAME).allTextContents();
    }

    /**
     * Agregar producto específico al carrito
     */
    public void addProductToCart(String productName) {
        switch (productName.toLowerCase()) {
            case "sauce labs backpack":
                clickElement(SAUCE_LABS_BACKPACK_ADD_BUTTON);
                break;
            case "sauce labs bike light":
                clickElement(SAUCE_LABS_BIKE_LIGHT_ADD_BUTTON);
                break;
            case "sauce labs bolt t-shirt":
                clickElement(SAUCE_LABS_BOLT_TSHIRT_ADD_BUTTON);
                break;
            default:
                // Para otros productos, buscar por nombre
                addProductByName(productName);
        }
    }

    /**
     * Agregar producto por nombre (método genérico)
     */
    private void addProductByName(String productName) {
        var products = page.locator(PRODUCT_ITEMS);
        for (int i = 0; i < products.count(); i++) {
            var productItem = products.nth(i);
            var nameElement = productItem.locator(PRODUCT_NAME);
            if (nameElement.textContent().equals(productName)) {
                productItem.locator(ADD_TO_CART_BUTTON).click();
                break;
            }
        }
    }

    /**
     * Ir al carrito de compras
     */
    public void goToShoppingCart() {
        clickElement(SHOPPING_CART_LINK);
    }

    /**
     * Obtener número de elementos en el carrito
     */
    public int getCartItemCount() {
        if (isElementVisible(SHOPPING_CART_BADGE)) {
            return Integer.parseInt(getText(SHOPPING_CART_BADGE));
        }
        return 0;
    }

    /**
     * Verificar si el carrito tiene elementos
     */
    public boolean hasItemsInCart() {
        return isElementVisible(SHOPPING_CART_BADGE);
    }

    /**
     * Obtener precio de un producto específico
     */
    public String getProductPrice(String productName) {
        var products = page.locator(PRODUCT_ITEMS);
        for (int i = 0; i < products.count(); i++) {
            var productItem = products.nth(i);
            var nameElement = productItem.locator(PRODUCT_NAME);
            if (nameElement.textContent().equals(productName)) {
                return productItem.locator(PRODUCT_PRICE).textContent();
            }
        }
        return "";
    }

    /**
     * Realizar logout
     */
    public void logout() {
        clickElement(BURGER_MENU);
        waitFor(1000); // Esperar que el menú se abra
        clickElement(LOGOUT_LINK);
    }
}
