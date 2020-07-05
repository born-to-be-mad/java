package streams;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
     * @param parent the parent npde
     * @return the stream of children
     */
    public static Stream<Node> children(Node parent) {
        NodeList nodeList = parent.getChildNodes();
        return IntStream.range(0, nodeList.getLength())
                        .mapToObj(nodeList::item);
    }

    /**
     * Create the source of list elements with indexes.
     *
     * @param list the input list
     * @return the stream of elements with indexes
     */
    public static <T> Stream<IndexedValue<T>> withIndices(List<T> list) {
        return IntStream.range(0, list.size())
                        .mapToObj(idx -> new IndexedValue<>(idx, list.get(idx)));
    }
}


class Group {
    private User[] users;
    private List<Mentor> mentors;
    private Map<String, Group> nameToGroups;

    public Stream<User> users() {
        return Arrays.stream(users);
    }

    public Stream<Group> neighbors() {
        return nameToGroups.values().stream();
    }

    public Stream<Mentor> mentors() {
        return mentors.stream();
    }
}

class User {
    String name;
}

class Mentor {
    String surName;
    int rating;
}
