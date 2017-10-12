package example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MyAppTest {

    @Test
    public void testThatAspectIsCalled() {
        MyApp.sayHi();
        assertTrue("The aspect was not called", StaticStateVariable.wasAspectCalled);
    }
}
