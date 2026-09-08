### Ejercicio 1: Predecir y corregir — Solución

#### Parte A: ¿Qué imprime?

Código del enunciado:

```kotlin
fun main() {
    val a: Int? = 127
    val b: Int? = 127
    println(a == b)
    println(a === b)

    val c: Int? = 128
    val d: Int? = 128
    println(c == d)
    println(c === d)

    val texto1 = "Kotlin"
    val texto2 = StringBuilder("Kot").append("lin").toString()
    println(texto1 == texto2)
    println(texto1 === texto2)

    val nombres: List<String?> = listOf("Ana", null, "Beatriz")
    for (nombre in nombres) {
        println(nombre?.length ?: -1)
    }
}
```

- Lo que esperaba ver:
  - `a == b` → true
  - `a === b` → false
  - `c == d` → true
  - `c === d` → false
  - `texto1 == texto2` → true
  - `texto1 === texto2` → false

- Lo que realmente salió al ejecutar:
  - true
  - true
  - true
  - false
  - true
  - false

- Explicación corta de las diferencias:
  - `==` compara por valor (contenido). `===` compara por referencia (misma dirección en memoria).
  - En la JVM hay caché de enteros para -128..127. Por eso, con `127`, `a` y `b` apuntan al mismo objeto y `a === b` da `true`. Con `128`, queda fuera del caché y `c === d` da `false`.
  - Con strings: `texto1 == texto2` es `true` porque el contenido es igual. `texto1 === texto2` es `false` porque `texto2` viene de `StringBuilder.toString()` y no usa el mismo objeto que el literal. Nota breve: existe el "String pool"; los literales como "Kotlin" suelen estar internados, pero `toString()` crea un objeto nuevo.

- Sobre el `for` con posibles `null`:

```kotlin
val nombres: List<String?> = listOf("Ana", null, "Beatriz")
for (nombre in nombres) {
    println(nombre?.length ?: -1)
}
```

- Salida:
  - 3
  - -1
  - 7

- ¿Por qué? `nombre?.length` da `null` cuando `nombre` es `null`, y el operador Elvis `?:` reemplaza ese `null` por `-1`.

- ¿Qué pasa si uso `nombre!!.length`?
  - Falla en la segunda iteración (cuando `nombre` es `null`) con `NullPointerException`, porque `!!` exige no-nulo.

---

#### Parte B: Corregir el código

Código original con errores:

```kotlin
fun main() {
    val contador = 0
    for (i in 1..5) {
        contador = contador + i
    }
    println("Suma: $contador")

    var apellido: String = null
    println(apellido.length)

    val numeros = listOf(1, 2, 3)
    numeros.add(4)

    val texto = "42"
    val total: Int = texto + 8
    println(total)
}
```

A continuación, para cada punto muestro el fragmento original y la solución, así el lector ve exactamente qué parte se corrige.

1) Reasignación con `val` (contador)
- Fragmento original:
```kotlin
val contador = 0
for (i in 1..5) {
    contador = contador + i
}
println("Suma: $contador")
```
- Solución:
```kotlin
var contador = 0
for (i in 1..5) {
    contador = contador + i
}
println("Suma: $contador")
```
En estap aprte sale un error por  al declarar la varible con val  esta no puede cambiar. Se tiene que cambiar val por var para que en el contador se pueda modificar

En java se tiene el final que hace que una varible no pueda cambiar como el val en kotlin.

2) Manejo de null en el tipo (apellido)
- Fragmento original:
```kotlin
var apellido: String = null
println(apellido.length)
```
- Solución mínima (tipo nullable y acceso seguro):
```kotlin
var apellido: String? = null
println(apellido?.length)
```
aca sale eerro porque no se están maneja manejando los null, para que no salga error en la línea var apellido: String = null se debe cambiar a var apellido: String? = null y      println(apellido.length) debe ser     println(apellido?.length)

en java la declaración de la varible var apellido: String = null no falla porque se pueden colocar valores nulos pero donde se debe volocar cuidado es en     println(apellido.length) ya la JVM no sabe como manejar lo valores null.

3) Lista inmutable vs. mutable (`add`)
- Fragmento original:
```kotlin
val numeros = listOf(1, 2, 3)
numeros.add(4)
```
- Solución:
```kotlin
val numeros = mutableListOf(1, 2, 3)
numeros.add(4)
```
aca el erro es que listOf solo es para leer orsea que no tiene el metood .add() para poder agreglar el elemento se debe cambiar a mutableListOf la cual si tiene el método de .add

en java no pasa esto poque no tiene .List que sean inmutables, ósea todas se pueden modificar.

4) Suma `String` + `Int` (conversión)
- Fragmento original:
```kotlin
val texto = "42"
val total: Int = texto + 8
```
- Solución:
```kotlin
val texto = "42"
val total: Int = texto.toInt() + 8
```
Sale erro al intentar sumar un String con un Int ya que en     val texto = "42" kotlin deteta que es un String asi que la vairble texto es un string y al crarl val total:=int esta diciendo que total es un int y este va tomar el valor de texto + 8 peri texti es un string lo cual no se puede y tocar usar un texto.toInt() parta parsar el texto a int y pode hacer la operación de suma.

en java esto es diferente porque primero todas las varibles tien que ser declaradas de que tipo va a ser y para hacer la transfomacion de stirng a int es diferente ya que en java int es un primitivo y no tiene los métodos de conversión para eso se debe usar el Integetet que si tiene el método par apasar stirng a int

Versión corregida completa 

```kotlin
fun main() {
    var contador = 0
    for (i in 1..5) {
        contador = contador + i
    }
    println("Suma: $contador")

    var apellido: String? = null
    println(apellido?.length ?: -1)

    val numeros = mutableListOf(1, 2, 3)
    numeros.add(4)
    println(numeros)

    val texto = "42"
    val total: Int = texto.toInt() + 8
    println(total)
}
```

---

#### Parte C: Reescribir con expresiones

Versión usando `if` como expresión (una sola asignación, sin `var` ni `return` dentro de ramas):

```kotlin
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
```

