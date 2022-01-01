package hjho.prj.prct.biz.place.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hjho.prj.prct.biz.place.model.CuntMgPagingPVO;
import hjho.prj.prct.biz.place.model.CuntMgPagingRVO;
import hjho.prj.prct.biz.place.model.CuntMgVO;
import hjho.prj.prct.biz.place.service.CuntMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/place/cunt")
@Api(tags="CuntMg", value="국가 관리")
public class CuntMgController extends CommonController {
	
	@Autowired 
	private CuntMgService cuntMgService;
	
	@GetMapping()
	@ApiOperation(value="getCunt", notes="국가 조회", response=CuntMgPagingRVO.class)
	public CommonMessage getCunt(@ModelAttribute CuntMgPagingPVO cuntMgPagingPVO) {
		this.parameterLog("CuntMg[getCunt]", cuntMgPagingPVO);
		
		CommonMessage output = new CommonMessage();
		
		List<CuntMgPagingRVO> listCunt = cuntMgService.getCunt(cuntMgPagingPVO);
		
		output.setOk();
		output.setData(listCunt);
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postCunt", notes="국가 등록", response=Integer.class)
	public CommonMessage postCunt(@RequestBody CuntMgVO cuntMgVO) {
		this.parameterLog("CuntMg[postCunt]", cuntMgVO);
		
		CommonMessage output = new CommonMessage();
		
		output.setOk();
		output.setData(0);
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putCunt", notes="국가 수정", response=Integer.class)
	public CommonMessage putCunt(@RequestBody CuntMgVO cuntMgVO) {
		this.parameterLog("CuntMg[putCunt]", cuntMgVO);
		
		CommonMessage output = new CommonMessage();
		
		output.setOk();
		output.setData(0);
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteCunt", notes="국가 삭제", response=Integer.class)
	public CommonMessage deleteCunt(@RequestBody CuntMgVO cuntMgVO) {
		this.parameterLog("CuntMg[deleteCunt]", cuntMgVO);
		
		CommonMessage output = new CommonMessage();
		
		output.setOk();
		output.setData(0);
		return output;
	}
	
}

