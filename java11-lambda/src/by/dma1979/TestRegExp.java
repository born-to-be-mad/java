package by.dma1979;

import java.util.HashMap;
import java.util.Map;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author dzmitry.marudau
 * @since 2019.4
 */
public class TestRegExp {

    private static final String SPLIT_STYLES_REGEXP = "(.+?):(.+?)(?:;\\W|$)";

    public static void main(String[] args) {

        Map<String, String> styles = new HashMap<>();
        // (.+?): captures the key
        // (?:,\\W|$): captures a comma followed by a space (these two symbols together are our actual separator)
        //             OR the end of the string

        //final String input = "a-b:123; cc-dd:100%; eee-fff:test";
        final String input = "abc:456;def:;eee:145;:";

        for (String styleString : input.split(";")) {
            String[] styleValuePair = styleString.trim().split(":");
            if (styleValuePair.length == 2) {
                System.out.println(styleValuePair[0].trim() + "=" + styleValuePair[1].trim());
            }
        }
/*

        final Pattern pattern = Pattern.compile(SPLIT_STYLES_REGEXP);
        final Matcher matcher = Pattern.compile(SPLIT_STYLES_REGEXP).matcher(input);

        while (matcher.find()) {
            if (matcher.groupCount() == 2) {
                styles.put(matcher.group(1), matcher.group(2));
            }
        }
        //System.out.println(styles);
        Map<String, Integer> styles2= Pattern.compile("\\W+")
            .splitAsStream("one fish two fish red fish blue fish")
            .collect(Collectors.groupingBy(String::toLowerCase,
                                           Collectors.summingInt(s -> 1)));
        //System.out.println(styles2);

        Map<String, String> styles3 = matcherStream(Pattern.compile(SPLIT_STYLES_REGEXP).matcher(input))
            .collect(Collectors.toMap(gs -> gs[1], gs -> gs[2]));
        System.out.println(styles3);
        System.out.println(styles3);

        final String emails = "kid@gmail.com, stray@yahoo.com, miks@tijuana.com";
        System.out.println("User has e-mail accounts on these domains: " +
                           matcherStream(Pattern.compile("([^,]+?)@([^,]+)").matcher(emails))
                               .map(gs->gs[2])
                               .collect(joining(", ")));
        Set<String> set = matcherStream(Pattern.compile("([^,]+?)@([^,]+)").matcher(emails))
            .map(gs -> gs[0].toLowerCase())
            .collect(toSet());
        System.out.println(set);*/
    }

    public static Stream<String[]> matcherStream(Matcher matcher) {
        return StreamSupport.stream(new MatcherSpliterator(matcher), false);
    }
}

class MatcherSpliterator extends Spliterators.AbstractSpliterator<String[]> {
    private final Matcher matcher;

    public MatcherSpliterator(Matcher matcher) {
        super(Long.MAX_VALUE, ORDERED | NONNULL | IMMUTABLE);
        this.matcher = matcher;
    }

    @Override
    public boolean tryAdvance(Consumer<? super String[]> action) {
        if (!matcher.find()) {
            return false;
        }
        final String[] groups = new String[matcher.groupCount() + 1];
        for (int groupIndex = 0; groupIndex <= matcher.groupCount(); groupIndex++) {
            groups[groupIndex] = matcher.group(groupIndex);
        }
        action.accept(groups);
        return true;
    }
}