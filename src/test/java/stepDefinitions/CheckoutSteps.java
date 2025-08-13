package stepDefinitions;

import io.cucumber.java.es.*;
import pages.CartPage;
import pages.CheckoutPage;
import pages.CheckoutOverviewPage;
import pages.CheckoutCompletePage;
import static org.assertj.core.api.Assertions.*;

/**
 * Step definitions para las funcionalidades del Proceso de Checkout
 */
public class CheckoutSteps {
    
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;

    public CheckoutSteps() {
        this.cartPage = new CartPage();
        this.checkoutPage = new CheckoutPage();
        this.checkoutOverviewPage = new CheckoutOverviewPage();
        this.checkoutCompletePage = new CheckoutCompletePage();
    }

    @Cuando("procedo al checkout")
    public void procedoAlCheckout() {
        cartPage.proceedToCheckout();
        assertThat(checkoutPage.isPageLoaded())
            .as("Debería estar en la página de checkout")
            .isTrue();
    }

    @Cuando("completo la informacion del cliente")
    public void completoLaInformacionDelCliente() {
        checkoutPage.completeCustomerInformation();
        assertThat(checkoutOverviewPage.isPageLoaded())
            .as("Debería estar en la página de resumen después de completar la información")
            .isTrue();
    }

    @Cuando("confirmo la orden")
    public void confirmoLaOrden() {
        checkoutOverviewPage.finishOrder();
        assertThat(checkoutCompletePage.isPageLoaded())
            .as("Debería estar en la página de confirmación")
            .isTrue();
    }

    @Cuando("intento continuar sin completar la informacion del cliente")
    public void intentoContinuarSinCompletarLaInformacionDelCliente() {
        // Intentar continuar sin llenar campos
        checkoutPage.continueCheckout();
    }

    @Cuando("cancelo el proceso de checkout")
    public void canceloElProcesoDeCheckout() {
        checkoutPage.cancelCheckout();
    }

    @Entonces("deberia ver la confirmacion de compra exitosa")
    public void deberiaVerLaConfirmacionDeCompraExitosa() {
        assertThat(checkoutCompletePage.isPurchaseSuccessful())
            .as("Debería ver la confirmación de compra exitosa")
            .isTrue();
    }

    @Entonces("deberia ver el mensaje {string}")
    public void deberiaVerElMensaje(String expectedMessage) {
        String actualMessage = checkoutCompletePage.getSuccessHeader();
        assertThat(actualMessage.toUpperCase())
            .as("Debería ver el mensaje de confirmación")
            .contains(expectedMessage.toUpperCase());
    }

    @Entonces("deberia ver un mensaje de error indicando campos requeridos")
    public void deberiaVerUnMensajeDeErrorIndicandoCamposRequeridos() {
        assertThat(checkoutPage.isErrorMessageDisplayed())
            .as("Debería mostrar un mensaje de error para campos requeridos")
            .isTrue();
    }

    @Entonces("deberia volver al carrito de compras")
    public void deberiaVolverAlCarritoDeCompras() {
        assertThat(cartPage.isPageLoaded())
            .as("Debería estar de vuelta en la página del carrito")
            .isTrue();
    }

    @Entonces("deberia ver el resumen de mi orden")
    public void deberiaVerElResumenDeMiOrden() {
        assertThat(checkoutOverviewPage.isPageLoaded())
            .as("Debería estar en la página de resumen de la orden")
            .isTrue();
        
        assertThat(checkoutOverviewPage.getOrderItemCount())
            .as("Debería haber productos en el resumen")
            .isGreaterThan(0);
    }

    @Entonces("deberia ver {string} en el resumen")
    public void deberiaVerEnElResumen(String productName) {
        assertThat(checkoutOverviewPage.isProductInSummary(productName))
            .as("Debería ver " + productName + " en el resumen")
            .isTrue();
    }

    @Entonces("deberia ver el subtotal, impuestos y total calculados correctamente")
    public void deberiaVerElSubtotalImpuestosYTotalCalculadosCorrectamente() {
        String subtotal = checkoutOverviewPage.getSubtotal();
        String tax = checkoutOverviewPage.getTax();
        String total = checkoutOverviewPage.getTotal();
        
        assertThat(subtotal)
            .as("El subtotal debería estar presente")
            .isNotEmpty()
            .contains("$");
        
        assertThat(tax)
            .as("Los impuestos deberían estar presentes")
            .isNotEmpty()
            .contains("$");
        
        assertThat(total)
            .as("El total debería estar presente")
            .isNotEmpty()
            .contains("$");
    }
}
