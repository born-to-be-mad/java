package by.dma.modules.main;
 
import by.dma.modules.hello.HelloModules;
 
public class MainApp {
    public static void main(String[] args) {
        HelloModules.doSomething();

        System.out.println("### Example of service call ###");
        HelloModules module = new HelloModules();
        module.sayHello();

    }
}
