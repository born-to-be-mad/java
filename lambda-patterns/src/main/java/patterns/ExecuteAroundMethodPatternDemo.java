package patterns;

import java.io.Closeable;
import java.io.IOException;

public class ExecuteAroundMethodPatternDemo {
    public static void main(String[] args) throws IOException {
        Resource resource = new Resource();
        resource.simpleOperation();
        resource.complexOperation();
        resource.close();
    }
}

class Resource implements Closeable {
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