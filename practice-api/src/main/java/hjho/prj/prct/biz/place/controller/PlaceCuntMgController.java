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

import hjho.prj.prct.biz.place.model.PlaceCuntMgPagingPVO;
import hjho.prj.prct.biz.place.model.PlaceCuntMgPagingRVO;
import hjho.prj.prct.biz.place.model.PlaceCuntMgVO;
import hjho.prj.prct.biz.place.service.PlaceCuntMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/place/cunt")
@Api(tags="PlaceCuntMg", value="국가 관리", description="국가 관리")
public class PlaceCuntMgController extends CommonController {
	
	@Autowired 
	private PlaceCuntMgService placeCuntMgService;
	
	@GetMapping()
	@ApiOperation(value="getPlaceCunt", notes="국가 조회", response=PlaceCuntMgPagingRVO.class)
	public CommonMessage getPlaceCunt(@ModelAttribute PlaceCuntMgPagingPVO placeCuntMgPagingPVO) {
		this.parameterLog("PlaceCuntMg[getPlaceCunt]", placeCuntMgPagingPVO);
		CommonMessage output = new CommonMessage();
		
		List<PlaceCuntMgPagingRVO> listCunt = placeCuntMgService.getPlaceCunt(placeCuntMgPagingPVO);
		
		output.setOk();
		output.setData(listCunt);
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postPlaceCunt", notes="국가 등록", response=Integer.class)
	public CommonMessage postPlaceCunt(@RequestBody PlaceCuntMgVO placeCuntMgVO) throws UserException {
		this.parameterLog("PlaceCuntMg[postPlaceCunt]", placeCuntMgVO);
		CommonMessage output = new CommonMessage();
		
		int insCnt = placeCuntMgService.postPlaceCunt(placeCuntMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putPlaceCunt", notes="국가 수정", response=Integer.class)
	public CommonMessage putPlaceCunt(@RequestBody PlaceCuntMgVO placeCuntMgVO) throws UserException {
		this.parameterLog("PlaceCuntMg[putPlaceCunt]", placeCuntMgVO);
		CommonMessage output = new CommonMessage();
		
		int updCnt = placeCuntMgService.putPlaceCunt(placeCuntMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deletePlaceCunt", notes="국가 삭제", response=Integer.class)
	public CommonMessage deletePlaceCunt(@RequestBody PlaceCuntMgVO placeCuntMgVO) throws UserException {
		this.parameterLog("PlaceCuntMg[deletePlaceCunt]", placeCuntMgVO);
		CommonMessage output = new CommonMessage();
		
		int delCnt = placeCuntMgService.deletePlaceCunt(placeCuntMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}

