package example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MannersAspect {

    @Around("pointcutSayMessage()")
    public Object adviceSayMessage(final ProceedingJoinPoint call) throws Throwable {
        System.out.println("Test");
        StaticStateVariable.wasAspectCalled = true;
        return call.proceed();
    }

    @Pointcut("execution(public static void MyApp.sayHi*(..))")
    public void pointcutSayMessage() {
        // No code required here
    }
}
