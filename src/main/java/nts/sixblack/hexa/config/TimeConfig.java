package nts.sixblack.hexa.config;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConfig {
    public static String getTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return simpleDateFormat.format(date);
    }


}
