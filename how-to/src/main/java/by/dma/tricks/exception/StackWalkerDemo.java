package by.dma.tricks.exception;

import java.util.NoSuchElementException;

import com.sun.jdi.StackFrame;

/**
 * StackWalker to as modern way to deal with stack traces.
 *
 * @author dzmitry.marudau
 * @since 2022.02
 */
public class StackWalkerDemo {

    public static void main(String[] args) {
        StackWalker stackWalker = StackWalker.getInstance();
        System.out.println("#".repeat(10) + " stackWalker.StackWalker.getInstance");
        stackWalker.forEach(System.out::println);

        stackWalker = StackWalker.getInstance(StackWalker.Option.SHOW_HIDDEN_FRAMES);
        System.out.println(
                "#".repeat(10) + " stackWalker.StackWalker.getInstance with StackWalker.Option.SHOW_HIDDEN_FRAMES");
        stackWalker.forEach(System.out::println);
    }

    public static StackFrame getCallerFrame() {
        StackWalker.getInstance()
                   .walk(stackFrameStream -> stackFrameStream.skip(2).findFirst())
                   .orElseThrow(NoSuchElementException::new);
    }

}
