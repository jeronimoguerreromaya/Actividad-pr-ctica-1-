data class Producto(
    val nombre: String,
    val precio: Double,
    val cantidad: Int
)

fun Producto.valorTotal(): Double = precio * cantidad

fun aplicarDescuento(
    producto: Producto,
    porcentaje: Double = 10.0
): Producto {
    val precioConDescuento = producto.precio * (1 - porcentaje / 100)
    return producto.copy(precio = precioConDescuento)
}

fun List<Producto>.resumen(): String {
    val resultado = StringBuilder()
    var totalInventario = 0.0

    resultado.append("Inventario (${this.size} productos):\n")

    this.forEach { producto ->
        val totalProducto = producto.valorTotal()

        resultado.append(
            "- ${producto.nombre}: ${producto.precio} x " +
            "${producto.cantidad} = $totalProducto\n"
        )

        totalInventario += totalProducto
    }

    resultado.append("Total inventario: $totalInventario")

    return resultado.toString()
}

fun Producto.estaAgotado(): Boolean {
    if(cantidad == 0){
        return true
    }else{
        return false
    }
}

fun main() {
    val productos = listOf(
        Producto("Teclado", 80000.0, 2),
        Producto("Mouse", 45000.0, 5),
        Producto("Monitor", 750000.0, 1),
        Producto("Audífonos", 120000.0, 0),
        Producto("Webcam", 180000.0, 3)
    )

    println(productos.resumen())
    println(productos[3].estaAgotado())

    val productoDescuentoTeclado = aplicarDescuento(productos[0], 25.0)
    val productoDescuentoMouse = aplicarDescuento(productos[1])

    println(productoDescuentoTeclado)
    println(productos[0])

    println(productos[1])
    println(productoDescuentoMouse) 

    /* 
    Se verifica gracias a que en un caso imprimimos el descuento primero y despues el producto,
    por lo que se demuestra que ambos productos son variables diferentes, esto ya que en la función
    para aplicar el descuento se utiliza la función copy, en lugar de editar el producto entrante y devolverlo
    */
    

    for ((nombre, precio, _) in productos) { // _ significa que el valor existe pero no se usa
        println("Nombre: $nombre, Precio: $precio")
    }

    val productoA = Producto("Mouse", 45000.0, 5)
    val productoB = Producto("Mouse", 45000.0, 5)
    println(productoA == productoB)
    println(productoA === productoB)

    /*
    Cuando se usa == verifica solamente los valores y cuando se utiliza === verifica si son el mismo objeto,
    en cambio si se pusiera ProductoB = ProductoA si daria true en ambos casos, ya que es el mismo objeto en 
    diferentes variables 
    */ 
}
