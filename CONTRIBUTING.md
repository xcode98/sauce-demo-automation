# 🤝 Guía de Contribución

¡Gracias por tu interés en contribuir al proyecto Sauce Demo Automation! Este documento te guiará para realizar contribuciones efectivas.

## 📋 Antes de Contribuir

### Prerrequisitos
- Java 15+
- Maven 3.6+
- Git configurado con tu cuenta

### Setup del Entorno de Desarrollo
```bash
# 1. Fork del repositorio en GitHub
# 2. Clonar tu fork
git clone https://github.com/TU-USUARIO/sauce-demo-automation.git
cd sauce-demo-automation

# 3. Configurar remote upstream
git remote add upstream https://github.com/USUARIO-ORIGINAL/sauce-demo-automation.git

# 4. Instalar dependencias
mvn clean compile
mvn exec:java -e -D exec.mainClass="com.microsoft.playwright.CLI" -D exec.args="install"

# 5. Verificar que todo funciona
mvn test -Dtest=SmokeTestRunner
```

## 🔄 Flujo de Trabajo

### 1. Crear Feature Branch
```bash
git checkout main
git pull upstream main
git checkout -b feature/nombre-descriptivo
```

### 2. Realizar Cambios
- Seguir las convenciones de código existentes
- Escribir tests para nueva funcionalidad
- Actualizar documentación si es necesario

### 3. Testing Local
```bash
# Ejecutar todos los tests
mvn test

# Verificar que smoke tests siguen pasando
mvn test -Dtest=SmokeTestRunner
```

### 4. Commit y Push
```bash
git add .
git commit -m "feat: descripción clara del cambio"
git push origin feature/nombre-descriptivo
```

### 5. Crear Pull Request
- Usar template de PR
- Describir claramente los cambios
- Incluir screenshots si aplica

## 📝 Convenciones de Código

### Estructura de Commits
```
tipo(scope): descripción

feat: nueva funcionalidad
fix: corrección de bug
docs: cambios en documentación
style: formato, punto y coma faltante, etc
refactor: refactoring sin cambio funcional
test: agregar tests faltantes
```

### Estándares Java
- Usar camelCase para métodos y variables
- Usar PascalCase para clases
- Comentarios en español para step definitions
- Seguir pattern Page Object Model

### Estructura de Tests
```java
@Entonces("deberia ver resultado esperado")
public void deberiaVerResultadoEsperado() {
    // Arrange - ya configurado en steps anteriores
    
    // Act - ya ejecutado en steps anteriores
    
    // Assert - verificar resultado
    assertThat(actualResult)
        .as("Descripción clara del assertion")
        .isEqualTo(expectedResult);
}
```

## 🧪 Agregando Nuevos Tests

### 1. Crear Feature File
```gherkin
# src/test/resources/features/nueva-funcionalidad.feature
#language: es
Característica: Descripción de la funcionalidad
  Como [rol]
  Quiero [objetivo]
  Para [beneficio]

  @smoke @nueva-funcionalidad
  Escenario: Caso de prueba exitoso
    Dado que estoy en condición inicial
    Cuando ejecuto acción
    Entonces deberia ver resultado esperado
```

### 2. Implementar Step Definitions
```java
// src/test/java/stepDefinitions/NuevaFuncionalidadSteps.java
@Dado("que estoy en condición inicial")
public void queEstoyEnCondicionInicial() {
    // Implementación
}
```

### 3. Crear/Actualizar Page Objects
```java
// src/main/java/pages/NuevaPagina.java
public class NuevaPagina extends BasePage {
    private final String ELEMENTO_SELECTOR = "#elemento";
    
    public void realizarAccion() {
        clickElement(ELEMENTO_SELECTOR);
    }
}
```

## 🚫 Qué NO Incluir

- Archivos de configuración personal (.idea/, .vscode/)
- Reportes generados (target/cucumber-reports/)
- Credenciales reales (usar solo las de demo)
- Dependencias innecesarias en pom.xml
- Comentarios TODO sin issue tracking

## ✅ Checklist Pre-PR

- [ ] Código compila sin errores: `mvn clean compile`
- [ ] Tests pasan: `mvn test`
- [ ] Smoke tests funcionan: `mvn test -Dtest=SmokeTestRunner`
- [ ] No hay archivos innecesarios en el commit
- [ ] Documentación actualizada si es necesario
- [ ] Commit messages siguen convenciones
- [ ] Branch actualizado con main: `git rebase main`

## 🐛 Reportar Bugs

### Template de Issue
```markdown
**Descripción del Bug**
Descripción clara y concisa del problema.

**Pasos para Reproducir**
1. Navegar a '...'
2. Hacer clic en '....'
3. Scroll down to '....'
4. Ver error

**Comportamiento Esperado**
Descripción de lo que esperabas que sucediera.

**Screenshots**
Si aplica, agregar screenshots del problema.

**Entorno:**
- OS: [e.g. macOS 12.0]
- Java: [e.g. 15.0.2]
- Maven: [e.g. 3.8.1]
```

## 🚀 Tipos de Contribuciones Bienvenidas

- 🐛 **Corrección de bugs**
- ✨ **Nuevas funcionalidades de testing**
- 📚 **Mejoras en documentación**
- 🎨 **Mejoras en estructura de código**
- 🔧 **Mejoras en configuración/build**
- 📊 **Mejoras en reportes**

## 📞 Contacto

Si tienes preguntas sobre cómo contribuir:
- Abrir un issue con label "question"
- Revisar issues existentes con label "good first issue"

¡Gracias por contribuir! 🎉
