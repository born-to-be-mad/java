package patterns;

import java.io.IOException;
import java.util.function.Consumer;

public class ExecuteAroundMethodPatternDemo {
    public static void main(String[] args) {
        Resource.use(resource ->
                resource.simpleOperation()
                        .complexOperation());
    }
}

class Resource {
    private Resource() {
        System.out.println("Resource created...");
    }

    Resource simpleOperation() {
        System.out.println("simple operation");
        return this;
    }

    Resource complexOperation() {
        System.out.println("complex operation");
        return this;
    }

    private void close() {
        System.out.println("close resources");
    }

    public static void use(Consumer<Resource> block) {
        Resource resource = new Resource();
        try {
            block.accept(resource);
        } finally {
            resource.close();
        }
    }
}