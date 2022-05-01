package hjho.prj.prct.biz.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import hjho.prj.prct.biz.system.model.MgrAuthMgPagingVO;
import hjho.prj.prct.biz.system.model.MgrAuthMgVO;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.clazz.CommonService;
import hjho.prj.prct.common.clazz.URI;

@Controller
@RequestMapping("/system/mgr/auth")
public class MgrAuthMgController extends CommonController {
	
	@Autowired 
	private CommonService commonService;
	
	@GetMapping("/get")
	public ModelAndView get(MgrAuthMgPagingVO mgrAuthMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.SYSTEM_MGR_AUTH_API, mgrAuthMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@GetMapping("/menu/get")
	public ModelAndView getMenu(MgrAuthMgPagingVO mgrAuthMgPagingVO) {
		
		CommonMessage output = commonService.get(URI.SYSTEM_MGR_AUTH_API.concat("/menu"), mgrAuthMgPagingVO);
		
		return super.pagingJsonView(output);
	}
	
	@PostMapping("/{method}")
	public ModelAndView post(@PathVariable("method") String method, @RequestBody List<MgrAuthMgVO> mgrAuthMgVO) {
		CommonMessage output = null;
		switch(method) {
			case INS:
				output = commonService.post(URI.SYSTEM_MGR_AUTH_API, mgrAuthMgVO);
				if("0003".equals(output.getCode())) {
					output.setOk();
				}
				break;
			case UPD:
				output = commonService.put(URI.SYSTEM_MGR_AUTH_API, mgrAuthMgVO);
				if("0001".equals(output.getCode())) {
					output.setOk();
				}
				break;
			case DEL:
				output = commonService.delete(URI.SYSTEM_MGR_AUTH_API, mgrAuthMgVO);
				if("0002".equals(output.getCode())) {
					output.setOk();
				}
				break;
			default:
				output = new CommonMessage();
				output.setError();
				break;
		}
		return super.jsonView(output);
	}
	
}
