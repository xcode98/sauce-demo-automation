# 🧪 Sauce Demo Automation Testing

Suite de pruebas automatizadas para [Sauce Demo](https://www.saucedemo.com/) utilizando **Playwright**, **Cucumber** y **Page Object Model**.

## 📋 Descripción del Proyecto

Este proyecto implementa una suite completa de pruebas automatizadas que cubre:
- ✅ Autenticación de usuarios (estándar y bloqueado)
- ✅ Gestión del carrito de compras
- ✅ Proceso completo de checkout
- ✅ Diferentes escenarios de usuario

## 🛠️ Tecnologías Utilizadas

- **Java 15** - Lenguaje de programación
- **Maven** - Gestor de dependencias y build
- **Playwright 1.42.0** - Framework de automatización web
- **Cucumber 7.15.0** - Framework BDD (Behavior Driven Development)
- **JUnit 5** - Framework de testing
- **AssertJ** - Librería de assertions fluidas

## 📁 Estructura del Proyecto

```
sauce-demo-automation/
├── src/
│   ├── main/java/
│   │   ├── config/
│   │   │   └── TestConfig.java          # Configuraciones centralizadas
│   │   ├── pages/                       # Page Object Model
│   │   │   ├── BasePage.java           # Clase base para todas las páginas
│   │   │   ├── LoginPage.java          # Página de login
│   │   │   ├── InventoryPage.java      # Página de productos
│   │   │   ├── CartPage.java           # Página del carrito
│   │   │   ├── CheckoutPage.java       # Página de checkout
│   │   │   ├── CheckoutOverviewPage.java # Página de resumen
│   │   │   └── CheckoutCompletePage.java # Página de confirmación
│   │   └── utils/
│   │       └── BrowserManager.java     # Gestor del navegador (Singleton)
│   └── test/
│       ├── java/
│       │   ├── runners/
│       │   │   ├── TestRunner.java     # Runner principal
│       │   │   └── SmokeTestRunner.java # Runner para smoke tests
│       │   └── stepDefinitions/
│       │       ├── LoginSteps.java     # Steps de autenticación
│       │       ├── ShoppingCartSteps.java # Steps del carrito
│       │       ├── CheckoutSteps.java  # Steps del checkout
│       │       ├── CommonSteps.java    # Steps comunes
│       │       └── Hooks.java          # Setup y teardown
│       └── resources/features/
│           ├── login.feature           # Scenarios de login
│           ├── shopping_cart.feature   # Scenarios del carrito
│           └── checkout.feature        # Scenarios de checkout
├── reports/                            # Reportes generados
├── pom.xml                            # Configuración Maven
└── README.md                          # Este archivo
```

## Configuración e Instalación Detallada

### Prerrequisitos OBLIGATORIOS

- **Java 15** (instalado y configurado como JAVA_HOME)
- **Maven 3.6+** 
- **Git** (para clonar el repositorio)

### Instalación

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/[tu-usuario]/sauce-demo-automation.git
   cd sauce-demo-automation
   ```

2. **Verificar Java 15:**
   ```bash
   java -version
   ```

3. **Instalar dependencias:**
   ```bash
   mvn clean compile
   ```

4. **Instalar navegadores de Playwright:**
   ```bash
   mvn exec:java -e -D exec.mainClass="com.microsoft.playwright.CLI" -D exec.args="install"
   ```
   
   > ⚠️ **Importante:** Este paso descarga los navegadores necesarios (~100MB). Es indispensable para el funcionamiento del proyecto.

## 🧪 Ejecución de Pruebas

### Ejecutar Solo Smoke Tests
```bash
mvn test -Dtest=SmokeTestRunner
```

### Ejecutar con Tags Específicos
```bash
# Solo tests de login
mvn test -Dcucumber.filter.tags="@login"

# Solo tests del carrito
mvn test -Dcucumber.filter.tags="@cart"

# Solo tests de checkout
mvn test -Dcucumber.filter.tags="@checkout"
```

## 📊 Reportes

Los reportes se generan automáticamente en:
- **HTML Report:** `target/cucumber-reports/cucumber.html`
- **JSON Report:** `target/cucumber-reports/cucumber.json`
- **JUnit Report:** `target/cucumber-reports/cucumber.xml`

Para ver el reporte HTML:
```bash
open target/cucumber-reports/smoke-tests.html
```


## 📈 Métricas de Calidad

- ✅ **Cobertura de funcionalidad:** 100% de los flujos principales
- ✅ **Mantenibilidad:** Page Object Model implementado
- ✅ **Legibilidad:** Gherkin en español para stakeholders
- ✅ **Reutilización:** Steps comunes centralizados
- ✅ **Reportes:** HTML, JSON y JUnit generados



