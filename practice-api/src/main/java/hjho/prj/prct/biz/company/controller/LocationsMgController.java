package hjho.prj.prct.biz.company.controller;

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

import hjho.prj.prct.biz.company.model.LocationsMgPagingPVO;
import hjho.prj.prct.biz.company.model.LocationsMgPagingRVO;
import hjho.prj.prct.biz.company.model.LocationsMgVO;
import hjho.prj.prct.biz.company.service.LocationsMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/company/locations")
@Api(tags="LocationsMg", value="자사 위치 관리", description="자사 위치 관리")
public class LocationsMgController extends CommonController {
	
	@Autowired 
	private LocationsMgService locationsMgService;
	
	@GetMapping()
	@ApiOperation(value="getLocations", notes="자사 위치 조회", response=LocationsMgPagingRVO.class)
	public CommonMessage getLocations(@ModelAttribute LocationsMgPagingPVO locationsMgPagingPVO) {
		this.parameterLog("LocationsMg[getLocations]", locationsMgPagingPVO);
		CommonMessage output = new CommonMessage();
		
		List<LocationsMgPagingRVO> listLoc = locationsMgService.getLocations(locationsMgPagingPVO);
		
		output.setOk();
		output.setData(listLoc);
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postLocations", notes="자사 위치 등록", response=Integer.class)
	public CommonMessage postLocations(@RequestBody LocationsMgVO locationsMgVO) throws UserException {
		this.parameterLog("LocationsMg[postLocations]", locationsMgVO);
		CommonMessage output = new CommonMessage();
		
		int insCnt = locationsMgService.postLocations(locationsMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putLocations", notes="자사 위치 수정", response=Integer.class)
	public CommonMessage putLocations(@RequestBody LocationsMgVO locationsMgVO) throws UserException {
		this.parameterLog("LocationsMg[putLocations]", locationsMgVO);
		CommonMessage output = new CommonMessage();
		
		int updCnt = locationsMgService.putLocations(locationsMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteLocations", notes="자사 위치 삭제", response=Integer.class)
	public CommonMessage deleteLocations(@RequestBody LocationsMgVO locationsMgVO) throws UserException {
		this.parameterLog("LocationsMg[deleteLocations]", locationsMgVO);
		CommonMessage output = new CommonMessage();
		
		int delCnt = locationsMgService.deleteLocations(locationsMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}

