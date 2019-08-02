package recipes.issues;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Dzmitry Marudau
 * @created at : 00:47
 * @since : 2019.08
 **/
public class DealWithExceptionsDemo {

    public static void main(String[] args) {
        DealWithExceptionsDemo instance = new DealWithExceptionsDemo();
        System.out.println("### Using an Extracted Method for Exception Handling ###");
        instance.encodeValuesUsingExtractedMethod(
                "google.com",
                "example.com?open=main view",
                "mobile.de?id=1 2 3&name=a&b&c")
                .forEach(System.out::println);

        System.out.println("### Using a Generic Exception Wrapper ###");
        instance.encodeValuesWithWrapper(
                "google.com",
                "example.com?open=main view",
                "mobile.de?id=1 2 3&name=a&b&c")
                .forEach(System.out::println);
    }

    //URLEncoder.encode throws UnsupportedEncodingException , which must be handled
    //Throwing the exception from the surrounding method also DOES NOT COMPILE
    public List<String> encodeValuesWithBadHandling(String... values) {
        return Arrays.stream(values)
                .map(s -> {
                    try {
                        return URLEncoder.encode(s, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return "";
                    }
                })
                .collect(Collectors.toList());
    }

    private String encodeStringByTransformingException(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> encodeValuesUsingExtractedMethod(String... values) {
        return Arrays.stream(values)
                .map(this::encodeStringByTransformingException)
                .collect(Collectors.toList());
    }

    public List<String> encodeValuesWithWrapper(String... values) {
        return Arrays.stream(values)
                .map(FunctionWithException.wrapper(s -> URLEncoder.encode(s, "UTF-8")))
                .collect(Collectors.toList());
    }
}
