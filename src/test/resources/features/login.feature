# language: es
Característica: Autenticación en Sauce Demo
  Como un cliente de Sauce Demo
  Quiero poder iniciar sesión en la aplicación
  Para acceder a los productos disponibles

  Antecedentes:
    Dado que estoy en la pagina de login de Sauce Demo

  @smoke @login
  Escenario: Login exitoso con usuario estandar
    Cuando ingreso las credenciales del usuario estandar
    Entonces deberia ser redirigido a la pagina de productos
    Y deberia ver la lista de productos disponibles

  @smoke @login
  Escenario: Login fallido con usuario bloqueado
    Cuando ingreso las credenciales del usuario bloqueado
    Entonces deberia ver un mensaje de error
    Y deberia permanecer en la pagina de login

  @login
  Escenario: Login fallido con credenciales inválidas
    Cuando ingreso credenciales inválidas
      | usuario        | contraseña  |
      | invalid_user   | wrong_pass  |
    Entonces debería ver un mensaje de error
    Y debería permanecer en la página de login

  @login
  Escenario: Login fallido con campos vacíos
    Cuando intento hacer login sin ingresar credenciales
    Entonces debería ver un mensaje de error indicando campos requeridos
