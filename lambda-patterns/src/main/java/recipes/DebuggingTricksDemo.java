package recipes;

import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.10
 *
 * <p>
 * COMMANDS:
 *
 * > Evaluate expression:
 * - Ctrl+Shift+F8: View breakpoints
 * - Shift + mouse click: Add breakpoint without "Suspend"(orange breakpoint)
 * - Alt+mouse click(or Ctrl+Alt+F8): quick evaluate expression
 * - Alt+F8: quick evaluate the selection
 *
 * > Debug operation:
 * - 'Delete' delete(drop) frame
 * - 'Reload changed classes' separate action in 'Debug', it is not possible to do for the current method,
 *    but it works of you drop the frame and then reload the class
 *
 * > Remote debugging:
 * - Add New Configuration -> Remote JVM Debug
 *   # 5005 is default port
 * - Remote JVM should be open for debugging:
 *   # 'suspend=y" - suspend on start
 *   # start with '-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005'
 *     f.e. `java -cp target/classes "-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5005" recipes.DebuggingTricksDemo`
 * - Attach to remote JVM
 */
public class DebuggingTricksDemo {

    private static final String DEFAULT = "World";

    public static void main(String[] args) {
        System.out.println(createMessage(args));
    }

    private static String createMessage(String[] args) {
        String name;
        if (args.length != 0) {
            name = args[0];
        } else {
            name = DEFAULT;
        }

        // simple right shifting algorithm: easy to show stream transformation in IntelliJ debugger
        name = name.chars()
                   .map(i -> i + 1)
                   .mapToObj(ch -> String.valueOf((char) ch))
                   .collect(Collectors.joining());
        return String.format("Hello %s!", name);
    }

}
