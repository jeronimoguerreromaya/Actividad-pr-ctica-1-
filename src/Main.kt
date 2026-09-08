fun main() {

    println(clasificarNota(2.5))

    clasificarNota2(5.0)


}


fun clasificarNota(nota: Double): String {
    var resultado: String
    if (nota >= 4.5) {
        resultado = "Excelente"
    } else if (nota >= 3.0) {
        resultado = "Aprobado"
    } else {
        resultado = "Reprobado"
    }
    return resultado
}

fun clasificarNota2(nota: Double): String {
    val resultado = if (nota >= 4.5) {
        "Excelente"
    } else if (nota >= 3.0) {
        "Aprobado"
    } else {
        "Reprobado"
    }
    return resultado
}