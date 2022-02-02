package hjho.prj.prct.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final String DTM_19 = "yyyy-MM-dd HH:mm:ss";
	public static final String DTM_14 = "yyyyMMddHHmmss";
	
	public static final String DATE_10 = "yyyy-MM-dd";
	public static final String DATE_8  = "yyyyMMdd";
	
	public static final String TIME_8 = "HH:mm:ss";
	public static final String TIME_6 = "HHmmss";
	
	public static String now() {
		return DateUtil.now(DateUtil.DTM_19);
	}
	public static String now(String format) {
		Date now = new Date();
		SimpleDateFormat simple = new SimpleDateFormat(format);
		return simple.format(now);
	}
	
	public static String getDtm(Long l) {
		return DateUtil.getDtm(l, DateUtil.DTM_19);
	}
	public static String getDtm(Long l, String format) {
		Date get = new Date(l);
		SimpleDateFormat simple = new SimpleDateFormat(format);
		return simple.format(get);
	}
	
	public static String getFormat(Date d) {
		return DateUtil.getFormat(d, DTM_19);
	}
	public static String getFormat(Date d, String format) {
		SimpleDateFormat simple = new SimpleDateFormat(format);
		return simple.format(d);
	}
	
	
}
