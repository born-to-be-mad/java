package by.dma.invokedynamic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Try MethodHandles.
 *
 * @author dzmitry.marudau
 * @since 2021.1
 */
public class LookupSample {

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        {
            MethodType methodType = MethodType.methodType(int.class, new Class<?>[] {String.class});
            MethodHandle methodHandle = lookup.findStatic(Counter.class, "count", methodType);
            int count = (int) methodHandle.invokeExact("test");
            System.out.println("Count via methodHandle=" + count);

            int count2 = (int) methodHandle.invokeExact("test");
            System.out.println("count2=" + count2);

            //error "expected (String)int but found (String)void"
            //methodHandle.invokeExact("test");

            //error "expected (String)int but found (Object)int"
            int count1 = (int) methodHandle.invokeExact((Object) "test");
        }

        System.out.println("############ Bean.class #################### ");
        {
            MethodHandle fieldHandle = lookup.findSetter(Bean.class, "value", String.class);
            MethodType methodType = MethodType.methodType(void.class, new Class<?>[] {String.class});
            MethodHandle methodHandle = lookup.findVirtual(Bean.class, "print", methodType);
        }
    }

}

class Bean {

    String value;

    void print(String x) {
        System.out.println(x);
    }
}

class Counter {

    public static int count(String str) {
        return str.length();
    }
}
