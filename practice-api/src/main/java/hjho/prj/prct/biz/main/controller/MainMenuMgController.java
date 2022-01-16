package hjho.prj.prct.biz.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.main.model.LoginRVO;
import hjho.prj.prct.biz.table.MenuInfo;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/main/menu")
@Api(tags="MainMenuMg", value="메뉴 관리", description="메뉴 관리")
public class MainMenuMgController extends CommonController {

	@GetMapping()
	@ApiOperation(value="getMainMenu", notes="메뉴 조회", response=LoginRVO.class)
	public CommonMessage getMainMenu(@ModelAttribute MenuInfo menuInfo) throws UserException {
		this.parameterLog("MainMenuMg[getMainMenu]", menuInfo);
		CommonMessage message = new CommonMessage();
		
		Map<String, List<MenuInfo>> returnVO = this.getMainMenuList();// loginService.loginProc(loginPVO);
		
		message.setOk();
		message.setData(returnVO);
		return message;
	}
	
	private Map<String, List<MenuInfo>> getMainMenuList() {
		
		Map<String, List<MenuInfo>> returnMap = new HashMap<String, List<MenuInfo>>();
		List<MenuInfo> listBs = new ArrayList<MenuInfo>();
		List<MenuInfo> listDt = new ArrayList<MenuInfo>();
		MenuInfo menuInfoVO = null;
		
		menuInfoVO = new MenuInfo();
		menuInfoVO.setMenuId("place");
		menuInfoVO.setHrMenuId("");
		menuInfoVO.setMenuNm("공용 관리");
		menuInfoVO.setMenuCtnt("");
		menuInfoVO.setPageUrl("/place");
		menuInfoVO.setOrdByCls(Long.valueOf(1));
		menuInfoVO.setIconUrl("");
		menuInfoVO.setUseYn("Y");
		listBs.add(menuInfoVO);
		
		menuInfoVO = new MenuInfo();
		menuInfoVO.setMenuId("placeRegi");
		menuInfoVO.setHrMenuId("place");
		menuInfoVO.setMenuNm("대륙 관리");
		menuInfoVO.setMenuCtnt("");
		menuInfoVO.setPageUrl("/place/regi");
		menuInfoVO.setOrdByCls(Long.valueOf(1));
		menuInfoVO.setIconUrl("globe");
		menuInfoVO.setUseYn("Y");
		listDt.add(menuInfoVO);
		
		menuInfoVO = new MenuInfo();
		menuInfoVO.setMenuId("placeCunt");
		menuInfoVO.setHrMenuId("place");
		menuInfoVO.setMenuNm("국가 관리");
		menuInfoVO.setMenuCtnt("");
		menuInfoVO.setPageUrl("/place/cunt");
		menuInfoVO.setOrdByCls(Long.valueOf(2));
		menuInfoVO.setIconUrl("flag");
		menuInfoVO.setUseYn("Y");
		listDt.add(menuInfoVO);
		////////////////////////////////////////////
		menuInfoVO = new MenuInfo();
		menuInfoVO.setMenuId("mgr");
		menuInfoVO.setHrMenuId("");
		menuInfoVO.setMenuNm("관리자 관리");
		menuInfoVO.setMenuCtnt("");
		menuInfoVO.setPageUrl("/mgr");
		menuInfoVO.setOrdByCls(Long.valueOf(2));
		menuInfoVO.setIconUrl("");
		menuInfoVO.setUseYn("Y");
		listBs.add(menuInfoVO);
		
		menuInfoVO = new MenuInfo();
		menuInfoVO.setMenuId("mgrLoc");
		menuInfoVO.setHrMenuId("mgr");
		menuInfoVO.setMenuNm("위치 관리");
		menuInfoVO.setMenuCtnt("");
		menuInfoVO.setPageUrl("/mgr/loc");
		menuInfoVO.setOrdByCls(Long.valueOf(1));
		menuInfoVO.setIconUrl("compass");
		menuInfoVO.setUseYn("Y");
		listDt.add(menuInfoVO);
		
		menuInfoVO = new MenuInfo();
		menuInfoVO.setMenuId("mgrDept");
		menuInfoVO.setHrMenuId("mgr");
		menuInfoVO.setMenuNm("부서 관리");
		menuInfoVO.setMenuCtnt("");
		menuInfoVO.setPageUrl("/mgr/dept");
		menuInfoVO.setOrdByCls(Long.valueOf(2));
		menuInfoVO.setIconUrl("globe");
		menuInfoVO.setUseYn("Y");
		listDt.add(menuInfoVO);
		
		menuInfoVO = new MenuInfo();
		menuInfoVO.setMenuId("mgrJob");
		menuInfoVO.setHrMenuId("mgr");
		menuInfoVO.setMenuNm("직책 관리");
		menuInfoVO.setMenuCtnt("");
		menuInfoVO.setPageUrl("/mgr/job");
		menuInfoVO.setOrdByCls(Long.valueOf(3));
		menuInfoVO.setIconUrl("briefcase");
		menuInfoVO.setUseYn("Y");
		listDt.add(menuInfoVO);
		
		menuInfoVO = new MenuInfo();
		menuInfoVO.setMenuId("mgrEmp");
		menuInfoVO.setHrMenuId("mgr");
		menuInfoVO.setMenuNm("사원 관리");
		menuInfoVO.setMenuCtnt("");
		menuInfoVO.setPageUrl("/mgr/emp");
		menuInfoVO.setOrdByCls(Long.valueOf(4));
		menuInfoVO.setIconUrl("flag");
		menuInfoVO.setUseYn("Y");
		listDt.add(menuInfoVO);
		
		returnMap.put("menuBs", listBs);
		returnMap.put("menuDt", listDt);
		
		return returnMap;
	}
	
}
