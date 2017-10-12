package example

object StaticStateVariable {
    var wasSayHiAspectCalled = false
    var wasReturnNothingAspectCalled = false
}

object MyApp {
    @JvmStatic
    fun sayHi() : String {
        println("Well hello there!")
        return "Hi!"
    }

    @JvmStatic
    fun returnNothing() {
        // Do nothing here either
    }
}

fun main(vararg args : String) {
    MyApp.sayHi()
    println("Hello World")
}