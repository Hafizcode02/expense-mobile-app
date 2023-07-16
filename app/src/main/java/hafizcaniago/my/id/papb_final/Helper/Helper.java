package hafizcaniago.my.id.papb_final.Helper;


import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {
    @SuppressLint("SimpleDateFormat")
    public String convertDate(String date, String to) throws ParseException {
        if (to.equals("SEND")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = sdf.parse(date);
            assert newDate != null;
            date = sdf.format(newDate);
            return date;

        } else if (to.equals("GET")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = sdf.parse(date);
            assert newDate != null;
            sdf = new SimpleDateFormat("dd MMM yyyy");
            date = sdf.format(newDate);
            return date;
        }
        return date;
    }
}
