package by.dma.interview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NonBlockingStackTest {

    @Test
    void testPopAndPush() {
        // given:
        var emptyStack = new NonBlockingStack<String>();
        var stack = new NonBlockingStack<String>();
        stack.push("One");
        stack.push("Two");
        // when:
        // then:
        Assertions.assertNull(emptyStack.pop());
        Assertions.assertEquals(stack.pop(), "Two");
        Assertions.assertEquals(stack.pop(), "One");
        Assertions.assertNull(stack.pop());
    }
}
