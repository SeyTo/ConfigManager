package random;

import org.junit.Test;

/**
 * Created by rj on 15/12/16.
 */
public class StringTest {

    @Test
    public void isEmptyByContext() throws Exception {
        MyClass val = new MyClass();
        method(val);
        System.out.println(val.a);
    }

    private void method(MyClass val) {
        val.a = 10;
    }

    class MyClass {
        int a = 0;
    }

}
