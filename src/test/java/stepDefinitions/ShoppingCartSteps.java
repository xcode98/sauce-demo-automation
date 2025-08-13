package stepDefinitions;

import io.cucumber.java.es.*;
import io.cucumber.datatable.DataTable;
import pages.InventoryPage;
import pages.CartPage;
import static org.assertj.core.api.Assertions.*;
import java.util.List;

/**
 * Step definitions para las funcionalidades del Carrito de Compras
 */
public class ShoppingCartSteps {
    
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    public ShoppingCartSteps() {
        this.inventoryPage = new InventoryPage();
        this.cartPage = new CartPage();
    }

    @Dado("estoy en la pagina de productos")
    public void estoyEnLaPaginaDeProductos() {
        assertThat(inventoryPage.isPageLoaded())
            .as("Debería estar en la página de productos")
            .isTrue();
    }

    @Cuando("agrego {string} al carrito")
    public void agregoAlCarrito(String productName) {
        inventoryPage.addProductToCart(productName);
    }

    @Cuando("agrego los siguientes productos al carrito:")
    public void agregoLosSiguientesProductosAlCarrito(DataTable dataTable) {
        List<String> products = dataTable.asList();
        for (String product : products) {
            inventoryPage.addProductToCart(product);
        }
    }

    @Cuando("navego al carrito de compras")
    public void navegoAlCarritoDeCompras() {
        inventoryPage.goToShoppingCart();
        assertThat(cartPage.isPageLoaded())
            .as("Debería estar en la página del carrito")
            .isTrue();
    }

    @Cuando("remuevo {string} del carrito")
    public void remuevoDelCarrito(String productName) {
        cartPage.removeProductFromCart(productName);
    }

    @Entonces("el contador del carrito deberia mostrar {int} producto")
    public void elContadorDelCarritoDeberiaMostrarProductos(int expectedCount) {
        if (expectedCount > 0) {
            assertThat(inventoryPage.getCartItemCount())
                .as("El contador del carrito debería mostrar " + expectedCount + " productos")
                .isEqualTo(expectedCount);
        } else {
            assertThat(inventoryPage.hasItemsInCart())
                .as("El carrito no debería tener elementos")
                .isFalse();
        }
    }

    @Entonces("el contador del carrito deberia mostrar {int} productos")
    public void el_contador_del_carrito_deberia_mostrar_productos(int expectedCount) {
        elContadorDelCarritoDeberiaMostrarProductos(expectedCount);
    }

    @Entonces("el carrito deberia contener {string}")
    public void elCarritoDeberiaContener(String productName) {
        inventoryPage.goToShoppingCart();
        assertThat(cartPage.isProductInCart(productName))
            .as("El carrito debería contener " + productName)
            .isTrue();
    }

    @Entonces("el carrito deberia contener todos los productos seleccionados")
    public void elCarritoDeberiaContenerTodosLosProductosSeleccionados() {
        inventoryPage.goToShoppingCart();
        assertThat(cartPage.getCartItemCount())
            .as("El carrito debería tener productos")
            .isGreaterThan(0);
    }

    @Entonces("deberia ver {string} en el carrito")
    public void deberiaVerEnElCarrito(String productName) {
        assertThat(cartPage.isProductInCart(productName))
            .as("Debería ver " + productName + " en el carrito")
            .isTrue();
    }

    @Entonces("deberia ver el precio correcto del producto")
    public void deberiaVerElPrecioCorrectoDelProducto() {
        List<String> cartItems = cartPage.getCartItems();
        assertThat(cartItems)
            .as("Debería haber productos en el carrito para verificar precios")
            .isNotEmpty();
        
        for (String product : cartItems) {
            String price = cartPage.getProductPrice(product);
            assertThat(price)
                .as("El precio del producto " + product + " debería estar presente")
                .isNotEmpty()
                .startsWith("$");
        }
    }

    @Entonces("el carrito deberia estar vacio")
    public void elCarritoDeberiaEstarVacio() {
        assertThat(cartPage.isCartEmpty())
            .as("El carrito debería estar vacío")
            .isTrue();
    }

    @Entonces("el contador del carrito no deberia ser visible")
    public void elContadorDelCarritoNoDeberiaSerVisible() {
        assertThat(inventoryPage.hasItemsInCart())
            .as("El contador del carrito no debería ser visible")
            .isFalse();
    }

    @Dado("he agregado {string} al carrito")
    public void heAgregadoAlCarrito(String productName) {
        inventoryPage.addProductToCart(productName);
        assertThat(inventoryPage.hasItemsInCart())
            .as("El producto debería estar agregado al carrito")
            .isTrue();
    }

    @Dado("que he agregado {string} al carrito")
    public void que_he_agregado_al_carrito(String productName) {
        heAgregadoAlCarrito(productName);
    }

    @Dado("estoy en el carrito de compras")
    public void estoyEnElCarritoDeCompras() {
        inventoryPage.goToShoppingCart();
        assertThat(cartPage.isPageLoaded())
            .as("Debería estar en la página del carrito")
            .isTrue();
    }

    @Dado("que estoy en el carrito de compras")
    public void queEstoyEnElCarritoDeCompras() {
        inventoryPage.goToShoppingCart();
        assertThat(cartPage.isPageLoaded())
            .as("Debería estar en la página del carrito")
            .isTrue();
    }
}
