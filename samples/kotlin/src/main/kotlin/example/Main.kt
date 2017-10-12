package example

object StaticStateVariable {
    var wasAspectCalled = false
}

object MyApp {
    @JvmStatic
    fun sayHi() : String {
        println("Well hello there!")
        return "Hi!"
    }
}

fun main(vararg args : String) {
    MyApp.sayHi()
    println("Hello World")
}