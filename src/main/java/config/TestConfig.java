package config;

/**
 * Configuraciones centralizadas para los tests de Sauce Demo
 */
public class TestConfig {
    
    // URLs de la aplicación
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String INVENTORY_URL = BASE_URL + "inventory.html";
    public static final String CART_URL = BASE_URL + "cart.html";
    public static final String CHECKOUT_URL = BASE_URL + "checkout-step-one.html";
    
    // Credenciales de usuarios
    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_OUT_USER = "locked_out_user";
    public static final String PASSWORD = "secret_sauce";
    
    // Configuraciones del navegador
    public static final boolean HEADLESS = false;
    public static final int TIMEOUT_SECONDS = 30;
    public static final int SLOW_MO = 1000;
    
    // Datos de prueba
    public static final String TEST_FIRST_NAME = "John";
    public static final String TEST_LAST_NAME = "Doe";
    public static final String TEST_ZIP_CODE = "12345";
}
