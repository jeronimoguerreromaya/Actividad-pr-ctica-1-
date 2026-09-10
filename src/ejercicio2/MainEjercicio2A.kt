package ejercicio2
    fun main() {
        val datos: List<Any?> = listOf(15, "42", 3.5, "hola", true, null, -8, "7.5", 100)
        var suma = 0.0

        for((index, valor) in datos.withIndex()){
            val numero = aNumero(valor)
            val rango = if (numero != null && numero % 1.0 == 0.0) {
                rangoDe(numero.toInt())
            } else {
                "no aplica"
            }

            if(numero != null){
                suma += numero
            }

            println("[$index] $valor ->" +
                    " ${clasificar(valor).lowercase()} | " +
                    "numérico: ${numero ?: "no aplica"} | " +
                    "rango: $rango")

        }

        println("Suma de valores numéricos: $suma")
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
        !in 0..Int.MAX_VALUE -> "negativo"
        in 0..9 -> "dígito"
        in 10..99 -> "decena"
        else -> "grande"
    }
    return resultado
}