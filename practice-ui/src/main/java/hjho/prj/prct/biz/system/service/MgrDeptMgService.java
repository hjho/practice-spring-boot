package hjho.prj.prct.biz.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.biz.system.model.MgrDeptMgVO;
import hjho.prj.prct.common.clazz.CommonMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MgrDeptMgService extends CommonService {

	
	public CommonMessage get(MgrDeptMgVO mgrDeptMgVO) {
		return this.getMgrMgResponseData(mgrDeptMgVO);
	}
	
	public CommonMessage post(MgrDeptMgVO mgrDeptMgVO) {
		return this.getMgrMgResponseData(mgrDeptMgVO);
	}
	
	public CommonMessage put(MgrDeptMgVO mgrDeptMgVO) {
		return this.getMgrMgResponseData(mgrDeptMgVO);
	}
	
	public CommonMessage delete(MgrDeptMgVO mgrDeptMgVO) {
		return this.getMgrMgResponseData(mgrDeptMgVO);
	}
	
	private CommonMessage getMgrMgResponseData(MgrDeptMgVO paramVO) {
		log.debug("##### Mgr Mg Parameter : {}", paramVO);
		
		List<Map<String, Object>> list = new ArrayList<>();
		for(int i=0; i<5; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("mgrNo", (10000+i));
			map.put("mgrNm", "name".concat(Integer.toString(i)));
			list.add(map);
		}
		
		CommonMessage rspnData = new CommonMessage();
		rspnData.setOk();
		rspnData.setData(list);
		
		return rspnData;
	}

}
