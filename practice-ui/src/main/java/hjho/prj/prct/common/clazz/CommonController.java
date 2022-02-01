package hjho.prj.prct.common.clazz;

import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public class CommonController {
	
	public final String PAGE = "/page";
	
	public final String SEL = "get";
	public final String INS = "post";
	public final String UPD = "put";
	public final String DEL = "delete";
	
//	protected final String MAIN_PAGE = "index";
	public final String MAIN_PAGE = "mainPage";
	
	public final String LOGIN_PAGE = "loginPage";
	
	public final String JSON_VIEW = "jsonView";
	
	private final String REPONSE_DATA = "result";
	
	/**
	 * use page move 
	 */
	protected ModelAndView getPageMav() {
		return new ModelAndView(MAIN_PAGE);
	}
	protected ModelAndView pageView(String path, String page) {
		ModelAndView mav = this.getPageMav();
		return this.pageView(mav, path, page);
	}
	protected ModelAndView pageView(ModelAndView mav, String path, String page) {
		String content = "/".concat(path).concat("/").concat(page).concat("View");
		mav.addObject("viewPath", content);	// FRAGMENT PATH
		mav.addObject("viewName", page);	// FRAGMENT NAME
		return mav;
	}
	
	/**
	 * use data list
	 */	
	protected ModelAndView getJsonMav() {
		return new ModelAndView(JSON_VIEW);
	}
	protected ModelAndView jsonView(CommonMessage msg) {
		ModelAndView mav = this.getJsonMav();
		return this.jsonView(mav, msg);
	}
	protected ModelAndView jsonView(ModelAndView mav, CommonMessage msg) {
		mav.addObject(REPONSE_DATA, msg);
		return mav;
	}
	
	/**
	 * use paging data list
	 */	
	@SuppressWarnings("unchecked")
	protected ModelAndView pagingJsonView(CommonMessage msg) {
		ModelAndView mav = this.getJsonMav();
		
		// 페이징 처리 
		mav.addObject(REPONSE_DATA, msg.getData());
		
		if(msg.getData() instanceof List) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) msg.getData();
			int total = 0;
			if(list.size() > 0) {
				total = (list.get(0).get("totalCnt") == null) ? 0 : (Integer) list.get(0).get("totalCnt");
			}
			mav.addObject("iTotalRecords"  , total);
			mav.addObject("iTotalDisplayRecords", total);
		}
		return mav;
	}
	
}
