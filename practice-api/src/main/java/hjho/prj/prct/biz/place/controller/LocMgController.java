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

import hjho.prj.prct.biz.place.model.LocMgPagingPVO;
import hjho.prj.prct.biz.place.model.LocMgPagingRVO;
import hjho.prj.prct.biz.place.model.LocMgVO;
import hjho.prj.prct.biz.place.service.LocMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/place/loc")
@Api(tags="LocMg", value="위치 관리")
public class LocMgController extends CommonController {
	
	@Autowired 
	private LocMgService locMgService;
	
	@GetMapping()
	@ApiOperation(value="getLoc", notes="위치 조회", response=LocMgPagingRVO.class)
	public CommonMessage getLoc(@ModelAttribute LocMgPagingPVO locMgPagingPVO) {
		this.parameterLog("LocMg[getLoc]", locMgPagingPVO);
		
		CommonMessage output = new CommonMessage();
		
		List<LocMgPagingRVO> listLoc = locMgService.getLoc(locMgPagingPVO);
		
		output.setOk();
		output.setData(listLoc);
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postLoc", notes="위치 등록", response=Integer.class)
	public CommonMessage postLoc(@RequestBody LocMgVO locMgVO) {
		this.parameterLog("LocMg[postLoc]", locMgVO);
		
		CommonMessage output = new CommonMessage();
		
		output.setOk();
		output.setData(0);
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putLoc", notes="위치 수정", response=Integer.class)
	public CommonMessage putLoc(@RequestBody LocMgVO locMgVO) {
		this.parameterLog("LocMg[putLoc]", locMgVO);
		
		CommonMessage output = new CommonMessage();
		
		output.setOk();
		output.setData(0);
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteLoc", notes="위치 삭제", response=Integer.class)
	public CommonMessage deleteLoc(@RequestBody LocMgVO locMgVO) {
		this.parameterLog("LocMg[deleteLoc]", locMgVO);
		
		CommonMessage output = new CommonMessage();
		
		output.setOk();
		output.setData(0);
		return output;
	}
	
}

