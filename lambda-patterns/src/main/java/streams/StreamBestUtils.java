package streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * How to solve different tasks via Stream API based on best practices
 *
 * @author dzmitry.marudau
 * @since 2020.3
 */
public class StreamBestUtils {

    /**
     * Create the source of children nodes for parent DOM XML node.
     *
     * @param parent
     * @return
     */
    public static Stream<Node> children(Node parent) {
        NodeList nodeList = parent.getChildNodes();
        return IntStream.range(0, nodeList.getLength())
                        .mapToObj(nodeList::item);
    }
}
