package by.dma.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Contract For HashCode:<BR/>
 *
 * <p>If two objects are equal, then calling hashCode() on both objects must return the same value.</p>
 *
 * @author dzmitry.marudau
 * @since 2019.6
 */
public class HowToOverrideHashCodeAndEquals {

    private String field1;
    private short field2;

    //bad performance: all values will be stored in bucked 1
/*    @Override
    public int hashCode() {
        return 1;
    }*/

    @Override
    public int hashCode() {
        //1. Store some constant nonzero value; say 17, in an int variable called result.
        int result = 17;

        /*2. For each significant field f in your object (each field taken into account by the equals( )), do the following
        2.1. Compute an int hashCode #HASH# for the field:
         A. If the field is a boolean, compute
            #HASH# = (f ? 1 : 0).
         B. If the field is a byte, char, short, or int, compute #HASH# = (int) f.
            iii. If the field is a long, compute #HASH# = (int) (f ^ (f >>> 32)).
            iv. If the field is a float, compute #HASH# = Float.floatToIntBits(f).
            v. If the field is a double, compute
        long l = Double.doubleToLongBits(f),
            #HASH# = (int)(l ^ (l >>> 32))
         C. If the field is an object reference then equals( ) calls equals( ) for this field. compute
            #HASH# = f.hashCode()
         D. If the field is an array, treat it as if each element were a separate field.
        That is, compute a hashCode for each significant element by applying above rules to each element.*/

        //37 is prime number!
        result = 37 * result + field1.hashCode(); //2.1.c
        result = 37 * result + (int) field2; //2.1.b

        //2.2. Combine the hashCode #HASH# computed in step 2.a into the result as follows:
        //result = 37 * result + #HASH#;
        return  result;

    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}
    /*
    memory leak can occur in Java application if equals() and hashcode() are not implemented
    */
class HashcodeLeakExample {
    private String id;

    public HashcodeLeakExample(String id) {
        this.id = id;
    }

        public static void main(String args[]) {
            try {
                Map<HashcodeLeakExample, String> map = new HashMap<>();
                while (true) {
                    //HashMap grows continuously by adding the same key repeatedly and finally throw an OutOfMemoryError
                    map.put(new HashcodeLeakExample("id"), "value");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
}
