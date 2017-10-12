package example;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class KotlinTestAspect {

    @Around("pointcutSayMessage()")
    public Object adviceSayMessage(final ProceedingJoinPoint call) throws Throwable {
        System.out.println("Test");
        StaticStateVariable.INSTANCE.setWasSayHiAspectCalled(true);
        final Object returned = call.proceed();
        return returned;
    }

    @Pointcut("execution(static String MyApp.sayHi*(..))")
    public void pointcutSayMessage() {
        // No code required here
    }

    @Around("pointcutReturnNothing()")
    public Object adviceReturnNothing(final ProceedingJoinPoint call) throws Throwable {
        StaticStateVariable.INSTANCE.setWasReturnNothingAspectCalled(true);
        return call.proceed();
    }

    @Pointcut("execution(static void MyApp.returnNothing*(..))")
    public void pointcutReturnNothing() {

    }
}
