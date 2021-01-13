package top.alvinsite.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Alvin
 */
public class TimeUtils {

    /**
     * 获取两个时间节点之间的月份列表
     * @param minDate
     * @param maxDate
     * @return
     */
    public static List<String> getMonthBetween(Date minDate, Date maxDate){
        ArrayList<String> result = new ArrayList<String>();
        //格式化为年月
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat resItemFormat = new SimpleDateFormat("M");

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(minDate);
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(maxDate);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(resItemFormat.format(curr.getTime()));
            curr.add(Calendar.MONTH, 1);
        }

        return result;
    }


    public static List<String> getMonthBetween(String minDate, String maxDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return getMonthBetween(sdf.format(minDate), sdf.format(maxDate));
    }
    public static int getAnnualNum(Date startedTime, Date finishedTime) {
        String START_MONTH = "9";
        int annualNum = 1;
        boolean bStart = false;
        List<String> mouths = getMonthBetween(startedTime, finishedTime);
        for (String mouth : mouths) {
            if (bStart && START_MONTH.equals(mouth)) {
                annualNum++;
            }
            if (!bStart) {
                bStart = true;
            }
        }

        return annualNum;
    }

    public static int getAnnualYear(Date date) {
        int START_MONTH = 9;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);

        if (month >= START_MONTH) {
            return year + 1;
        }
        return year;
    }
}
