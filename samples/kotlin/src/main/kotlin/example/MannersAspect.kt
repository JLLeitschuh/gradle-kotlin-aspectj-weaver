package example

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

@Aspect
open class MannersAspect {

    @Around("pointcutSayMessage()")
    @Throws(Throwable::class)
    fun adviceSayMessage(call: ProceedingJoinPoint): Any {
        println("Test")
        return call.proceed()
    }

    @Pointcut("execution(public static void MyApp.sayHi*(..))")
    fun pointcutSayMessage() {
        // No code required here
    }
}