package by.dma.interview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SemaphoreStackTest {

    @Test
    void testPopAndPush() {
        // given:
        var emptyStack = new SemaphoreStack<String>();
        var stack = new SemaphoreStack<String>();
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
