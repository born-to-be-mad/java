package patterns;

import java.io.IOException;

public class ExecuteAroundMethodPatternDemo {
    public static void main(String[] args) {
        //ARM(Automatic Resource Management) = try with resource
        try (Resource resource = new Resource()) {
            resource.simpleOperation();
            resource.complexOperation();
            resource.close();
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }
}

class Resource implements AutoCloseable {
    Resource() {
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

    @Override
    public void close() throws IOException {
        System.out.println("close resources");
    }
}