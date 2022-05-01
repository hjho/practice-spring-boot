package hjho.prj.prct.common.clazz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonController {
	
	public final static String CRET = "CRET";	// 등록
	public final static String READ = "READ"; 	// 조회
	public final static String UPD  = "UPD";	// 수정
	public final static String DEL  = "DEL";	// 삭제
	public final static String PRIV = "PRIV";	// 개인정보 조회
	public final static String EXPT = "EXPT";	// 출력

	public void parameterLog(String methodName, Object obj) {
		log.debug("============= [Request] ================");
		log.debug("=== Method Name : {}", methodName);
		log.debug("=== Parameter   : {}", obj);
		log.debug("========================================");
	}
	
	public void parameterLog(String methodName, Object[] obj) {
		log.debug("============= [Request] ================");
		log.debug("=== Method Name : {}", methodName);
		for(int i=0; i<obj.length; i++) {
			log.debug("=== Parameter{}  : {}", (i+1), obj[i]);
		}
		log.debug("========================================");
	}
	
}
