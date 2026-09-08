package ejercicio2
    fun main() {
        val datos: List<Any?> = listOf(15, "42", 3.5, "hola", true, null, -8, "7.5", 100)

        for((index, valor) in datos.withIndex()){
            println("[$index] $valor ->" +
                    " ${clasificar(valor)} | " +
                    "numerico: ${aNumero(valor) ?: "no aplica"} | " +
                    "rango: ${if(valor is Int) rangoDe(valor) else "No aplica"}")

        }
    }

fun clasificar (valor: Any?): String {
   val resultado = when(valor){
       is Int -> "Entero"
       is Double -> "Decimal"
       is String -> "Texto"
       is Boolean -> "Booleano"
       null -> "Desconocido"
       else -> "Desconocido"
   }
    return resultado
}

fun aNumero(valor: Any?): Double? {
    val contenedor = when (valor){
        is Int -> valor.toDouble()
        is Double -> valor
        is String -> try {
            valor.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
        else -> null
    }
    return contenedor
}

fun rangoDe (numero:Int): String {
    val resultado = when (numero) {
        null -> "Desconocido"
        !in 0..Int.MAX_VALUE-> "Negativo"
        in 0..9-> "Digito"
        in 10..99 -> "Decena"
        else -> "Grande"
    }
    return resultado
}