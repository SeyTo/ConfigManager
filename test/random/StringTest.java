package random;

import org.junit.Test;

/**
 * Created by rj on 15/12/16.
 */
public class StringTest {

    @Test
    public void isEmptyByContext() throws Exception {
        String val = "\nHello\n   ";
        System.out.println(val);
            val = val.trim();
            if (val.startsWith("<") || val.endsWith(">")) System.out.println(false);

    }
}
