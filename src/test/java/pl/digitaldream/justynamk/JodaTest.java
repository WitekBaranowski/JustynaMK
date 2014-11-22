package pl.digitaldream.justynamk;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.Test;

/**
 * Created by Witek on 2014-11-22.
 */
public class JodaTest {
    final static DateTimeFormatter parser = ISODateTimeFormat.localDateOptionalTimeParser();
    @Test
    public void testJodaTime(){
        DateTime localDateTime = parser.parseDateTime("2014-10-23T14:26:15");
        System.out.print(localDateTime);
    }
}
