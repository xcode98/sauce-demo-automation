# language: es
Característica: Proceso de Compra en Sauce Demo
  Como un cliente autenticado de Sauce Demo
  Quiero poder completar el proceso de compra
  Para adquirir los productos que he seleccionado

  Antecedentes:
    Dado que estoy autenticado como usuario estandar
    Y he agregado "Sauce Labs Fleece Jacket" al carrito

  @smoke @checkout
  Escenario: Completar proceso de compra con producto caro exitosamente
    Dado que estoy en el carrito de compras
    Cuando procedo al checkout
    Y completo la informacion del cliente
    Y confirmo la orden
    Entonces deberia ver la confirmacion de compra exitosa
    Y deberia ver el mensaje "Thank you for your order!"

  @checkout
  Escenario: Checkout con informacion incompleta del cliente
    Dado que estoy en el carrito de compras
    Cuando procedo al checkout
    Y intento continuar sin completar la informacion del cliente
    Entonces deberia ver un mensaje de error indicando campos requeridos

  @checkout
  Escenario: Cancelar proceso de checkout
    Dado que estoy en el carrito de compras
    Cuando procedo al checkout
    Y cancelo el proceso de checkout
    Entonces deberia volver al carrito de compras

  @checkout
  Escenario: Verificar resumen de orden con producto premium
    Dado que estoy en el carrito de compras
    Cuando procedo al checkout
    Y completo la informacion del cliente
    Entonces deberia ver el resumen de mi orden
    Y deberia ver "Sauce Labs Fleece Jacket" en el resumen
    Y deberia ver el subtotal, impuestos y total calculados correctamente
