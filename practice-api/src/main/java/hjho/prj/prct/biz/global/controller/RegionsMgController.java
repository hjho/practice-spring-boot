package hjho.prj.prct.biz.global.controller;

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

import hjho.prj.prct.biz.global.model.RegionsMgPagingPVO;
import hjho.prj.prct.biz.global.model.RegionsMgPagingRVO;
import hjho.prj.prct.biz.global.model.RegionsMgVO;
import hjho.prj.prct.biz.global.service.RegionsMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/global/regions")
@Api(tags="RegionsMg", value="대륙 관리", description="대륙 관리")
public class RegionsMgController extends CommonController {
	
	@Autowired 
	private RegionsMgService regionsMgService;
	
	@GetMapping()
	@ApiOperation(value="getRegions", notes="대륙 조회", response=RegionsMgPagingRVO.class)
	public CommonMessage getRegions(@ModelAttribute RegionsMgPagingPVO regionsMgPagingPVO) {
		this.parameterLog("RegionsMg[getRegions]", regionsMgPagingPVO);
		CommonMessage output = new CommonMessage();
		
		List<RegionsMgPagingRVO> listCunt = regionsMgService.getRegions(regionsMgPagingPVO);
		
		output.setOk();
		output.setData(listCunt);
		return output;
	}
	
	@GetMapping("/box")
	@ApiOperation(value="getRegionsBox", notes="대륙 조회(selectBox)", response=RegionsMgPagingRVO.class)
	public CommonMessage getRegionsBox() {
		CommonMessage output = new CommonMessage();
		output.setOk();
		output.setData(regionsMgService.getRegionsBox());
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postRegions", notes="대륙 등록", response=Integer.class)
	public CommonMessage postRegions(@RequestBody RegionsMgVO regionsMgVO) throws UserException {
		this.parameterLog("RegionsMg[postRegions]", regionsMgVO);
		CommonMessage output = new CommonMessage();
		
		int insCnt = regionsMgService.postRegions(regionsMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putRegions", notes="대륙 수정", response=Integer.class)
	public CommonMessage putRegions(@RequestBody RegionsMgVO regionsMgVO) throws UserException {
		this.parameterLog("RegionsMg[putRegions]", regionsMgVO);
		CommonMessage output = new CommonMessage();
		
		int updCnt = regionsMgService.putRegions(regionsMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteRegions", notes="대륙 삭제", response=Integer.class)
	public CommonMessage deleteRegions(@RequestBody RegionsMgVO regionsMgVO) throws UserException {
		this.parameterLog("RegionsMg[deleteRegions]", regionsMgVO);
		CommonMessage output = new CommonMessage();
		
		int delCnt = regionsMgService.deleteRegions(regionsMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}

