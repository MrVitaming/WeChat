package lwm.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by LWM on 2020/1/5.
 */
public class ExceptionUtil {

    public static String toStackTrace(Exception e)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        try
        {
            e.printStackTrace(pw);
            return sw.toString();
        }
        catch(Exception e1)
        {
            return "";
        }
    }

}
