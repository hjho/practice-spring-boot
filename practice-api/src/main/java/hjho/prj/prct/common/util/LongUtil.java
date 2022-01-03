package hjho.prj.prct.common.util;

public class LongUtil {
	

	public static boolean isEmpty(Long l) {
		if(l == null || l == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotEmpty(Long l) {
		return !LongUtil.isEmpty(l);
	}
}
