package ejercicio2

fun main() {
    //1. crear arreglo
    val arreglo = arrayOfNulls<Int>(10)
    var auxiliar = 0
    for (i in 2..20 step 2){
         arreglo[auxiliar] =i
        auxiliar++
     }
    //2. recorrer posiciones impares
    for ((indice,valor) in arreglo.withIndex()){
        if (indice % 2 != 0){
            println("[$indice] $valor")
        }
    }

    //3.cuenta regresiva de 20 a 0 de 5 en 5 usando downTo y step
    for (i in 20 downTo 0 step 5){
        print("$i, ")
    }

    //4. explicacion
    /*
     0..arreglo.size incluye el valor final. Tomando como ejemplo este ejercicio, el arreglo tiene 10 elementos,
     es decir, los índices van de 0 a 9. Como arreglo.size es 10, indica que irá hasta el índice 10. Por lo tanto,
     como el último índice real es 9 y se le indica que vaya hasta el 10, va a salir una excepción ArrayIndexOutOfBoundsException.

     En cambio, 0 until arreglo.size excluye el último valor, es decir, se puede ver como 0..arreglo.size - 1.
    */



}