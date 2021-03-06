package example

import junit.framework.Assert.assertTrue
import org.junit.Test

class MyAppTest {
    @Test
    fun `test that aspect is called`() {
        MyApp.sayHi()
        assertTrue(StaticStateVariable.wasSayHiAspectCalled)
    }

    @Test
    fun `test that return nothing aspect is called`() {
        MyApp.returnNothing()
        assertTrue(StaticStateVariable.wasReturnNothingAspectCalled)
    }
}