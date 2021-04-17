class Printer (private val formatter: (String) -> String) {
    fun printString(str: String) {
        println(formatter(str))
    }
}

val capitalizeString: (String) -> String = { it.toUpperCase() }
val lowerCaseFormatter = { it: String -> it.toLowerCase()  }

fun main() {
    val capitalPrinter = Printer(capitalizeString)
    capitalPrinter.printString("Hello there")

    val lowerPrinter = Printer(lowerCaseFormatter)
    lowerPrinter.printString("Hi there again")
}
