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

## ⚡ Quick Start (Para Desarrolladores Impacientes)

```bash
# 1. Clonar y navegar al proyecto
git clone https://github.com/[tu-usuario]/sauce-demo-automation.git
cd sauce-demo-automation

# 2. Verificar prerrequisitos
java -version  # Debe ser Java 15+
mvn -version   # Debe ser Maven 3.6+

# 3. Instalar todo de una vez
mvn clean compile
mvn exec:java -e -D exec.mainClass="com.microsoft.playwright.CLI" -D exec.args="install"

# 4. Ejecutar smoke tests para verificar que todo funciona
mvn test -Dtest=SmokeTestRunner
```

## 🚀 Configuración e Instalación Detallada

### Prerrequisitos OBLIGATORIOS

- **Java 15** (instalado y configurado como JAVA_HOME)
- **Maven 3.6+** 
- **Git** (para clonar el repositorio)
- **Conexión a Internet** (para descargar navegadores de Playwright)

### Instalación

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/[tu-usuario]/sauce-demo-automation.git
   cd sauce-demo-automation
   ```

2. **Verificar Java 15:**
   ```bash
   java -version
   # Debería mostrar: java version "15.0.2" o superior
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

### Ejecutar Todas las Pruebas
```bash
mvn test
```

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

### Ejecutar en Modo Headless
Para ejecutar sin mostrar el navegador, modificar en `TestConfig.java`:
```java
public static final boolean HEADLESS = true;
```

## 📊 Reportes

Los reportes se generan automáticamente en:
- **HTML Report:** `target/cucumber-reports/cucumber.html`
- **JSON Report:** `target/cucumber-reports/cucumber.json`
- **JUnit Report:** `target/cucumber-reports/cucumber.xml`

Para ver el reporte HTML:
```bash
open target/cucumber-reports/cucumber.html
```

## 🎯 Escenarios de Prueba Implementados

### 🔐 Autenticación (`login.feature`)
- ✅ Login exitoso con usuario estándar
- ✅ Login fallido con usuario bloqueado
- ✅ Login fallido con credenciales inválidas
- ✅ Login fallido con campos vacíos

### 🛒 Carrito de Compras (`shopping_cart.feature`)
- ✅ Agregar producto al carrito
- ✅ Agregar múltiples productos
- ✅ Ver productos en el carrito
- ✅ Remover productos del carrito

### 💳 Proceso de Checkout (`checkout.feature`)
- ✅ Completar compra exitosamente
- ✅ Checkout con información incompleta
- ✅ Cancelar proceso de checkout
- ✅ Verificar resumen de la orden

## 👥 Usuarios de Prueba

El sistema utiliza las siguientes credenciales predefinidas:

| Usuario | Contraseña | Comportamiento |
|---------|------------|----------------|
| `standard_user` | `secret_sauce` | ✅ Acceso completo |
| `locked_out_user` | `secret_sauce` | ❌ Usuario bloqueado |

## 🏗️ Patrones de Diseño Implementados

### 1. **Page Object Model (POM)**
- Cada página de la aplicación tiene su propia clase
- Encapsula elementos y acciones específicas de cada página
- Facilita el mantenimiento y reutilización

### 2. **Singleton Pattern**
- `BrowserManager` gestiona una única instancia del navegador
- Evita múltiples instancias y conflictos

### 3. **Factory Pattern**
- Creación centralizada de páginas y configuraciones
- Flexibilidad para diferentes tipos de navegadores

## ⚙️ Configuración Avanzada

### Modificar Configuraciones
Editar `src/main/java/config/TestConfig.java`:

```java
// Cambiar URL base
public static final String BASE_URL = "https://www.saucedemo.com/";

// Modificar timeouts
public static final int TIMEOUT_SECONDS = 30;

// Cambiar velocidad de ejecución
public static final int SLOW_MO = 1000; // milliseconds
```

### Agregar Nuevos Navegadores
En `BrowserManager.java`, modificar el método `initializeBrowser()` para soportar Firefox o Safari.

## 🐛 Troubleshooting

### ❌ Error: "No browser found" o "Chrome not found"
```bash
# Solución: Reinstalar navegadores de Playwright
mvn exec:java -e -D exec.mainClass="com.microsoft.playwright.CLI" -D exec.args="install"
```

### ❌ Error: Java version incompatible
```bash
# Verificar versión actual
java -version
echo $JAVA_HOME

# Debe mostrar Java 15+. Si no:
# macOS: brew install openjdk@15
# Linux: sudo apt install openjdk-15-jdk
# Windows: Descargar desde Oracle/OpenJDK
```

### ❌ Tests fallan después de clonar desde GitHub
```bash
# 1. Limpiar y reinstalar todo
mvn clean
rm -rf target/
mvn clean compile
mvn exec:java -e -D exec.mainClass="com.microsoft.playwright.CLI" -D exec.args="install"

# 2. Verificar que funciona con smoke tests
mvn test -Dtest=SmokeTestRunner
```

### ❌ Error: "Maven command not found"
```bash
# Instalar Maven:
# macOS: brew install maven
# Linux: sudo apt install maven
# Windows: Descargar desde apache.maven.org

# Verificar instalación:
mvn -version
```

### ❌ Tests fallan por timeouts
1. **Conexión lenta:** Aumentar timeout en `TestConfig.java`
2. **Problemas gráficos:** Cambiar a modo headless
3. **Recursos limitados:** Ejecutar tests de uno en uno

### ❌ Error de compilación después de pull
```bash
# Refresh completo del proyecto
mvn clean install -U
```

## 📈 Métricas de Calidad

- ✅ **Cobertura de funcionalidad:** 100% de los flujos principales
- ✅ **Mantenibilidad:** Page Object Model implementado
- ✅ **Legibilidad:** Gherkin en español para stakeholders
- ✅ **Reutilización:** Steps comunes centralizados
- ✅ **Reportes:** HTML, JSON y JUnit generados

## 🤝 Contribución

1. Fork el proyecto
2. Crear feature branch (`git checkout -b feature/nueva-funcionalidad`)
3. Commit cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crear Pull Request

## 📝 Notas del Desarrollador

- **Problema resuelto:** Chrome se cerraba prematuramente con Java 11. **Solución:** Upgrade a Java 15 + Playwright 1.42.0
- **Optimización macOS:** Configuraciones específicas para estabilidad en Mac
- **Pattern BDD:** Features escritas desde perspectiva del usuario final
- **Assertions:** AssertJ utilizado para assertions más legibles

## 🎉 Estado del Proyecto

**✅ PROYECTO COMPLETAMENTE FUNCIONAL**

- 🌐 Chrome abre y funciona estable
- 🧪 Tests ejecutándose exitosamente  
- 📊 Reportes generándose automáticamente
- 🔄 CI/CD ready

---

**Desarrollado con ❤️ usando Java 15, Playwright y Cucumber**
