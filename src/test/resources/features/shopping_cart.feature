# language: es
Característica: Carrito de Compras en Sauce Demo
  Como un cliente autenticado de Sauce Demo
  Quiero poder gestionar productos en mi carrito
  Para poder realizar compras de los productos que necesito

  Antecedentes:
    Dado que estoy autenticado como usuario estandar
    Y estoy en la pagina de productos

  @smoke @cart
  Escenario: Agregar producto caro al carrito
    Cuando agrego "Sauce Labs Fleece Jacket" al carrito
    Entonces el contador del carrito deberia mostrar 1 producto
    Y el carrito deberia contener "Sauce Labs Fleece Jacket"

  @cart
  Escenario: Agregar multiples productos diferentes al carrito
    Cuando agrego los siguientes productos al carrito:
      | Sauce Labs Bike Light  |
      | Sauce Labs Bolt T-Shirt |
      | Sauce Labs Onesie |
    Entonces el contador del carrito deberia mostrar 3 productos
    Y el carrito deberia contener todos los productos seleccionados

  @cart
  Escenario: Ver producto mas barato en el carrito
    Dado que he agregado "Sauce Labs Onesie" al carrito
    Cuando navego al carrito de compras
    Entonces deberia ver "Sauce Labs Onesie" en el carrito
    Y deberia ver el precio correcto del producto

  @cart
  Escenario: Remover producto del carrito
    Dado que he agregado "Sauce Labs Bike Light" al carrito
    Y estoy en el carrito de compras
    Cuando remuevo "Sauce Labs Bike Light" del carrito
    Entonces el carrito deberia estar vacio
    Y el contador del carrito no deberia ser visible
