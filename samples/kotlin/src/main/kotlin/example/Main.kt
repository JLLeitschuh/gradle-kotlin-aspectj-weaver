package example

object StaticStateVariable {
    var wasAspectCalled = false
}

object MyApp {
    @JvmStatic
    fun sayHi() {
        println("Hello World!")
    }
}

fun main(vararg args : String) {
    println("Hello World")
}