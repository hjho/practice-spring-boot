package hjho.prj.prct.common.clazz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonController {

	public void parameterLog(String methodName, Object obj) {
		log.debug("============= [Request] ================");
		log.debug("=== Method Name : {}", methodName);
		log.debug("=== Parameter   : {}", obj);
		log.debug("========================================");
	}
	
}
