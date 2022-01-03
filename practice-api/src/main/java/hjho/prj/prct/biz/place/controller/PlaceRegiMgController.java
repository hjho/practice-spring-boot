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

import hjho.prj.prct.biz.place.model.PlaceRegiMgPagingPVO;
import hjho.prj.prct.biz.place.model.PlaceRegiMgPagingRVO;
import hjho.prj.prct.biz.place.model.PlaceRegiMgVO;
import hjho.prj.prct.biz.place.service.PlaceRegiMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/place/regi")
@Api(tags="PlaceRegiMg", value="대륙 관리", description="대륙 관리")
public class PlaceRegiMgController extends CommonController {
	
	@Autowired 
	private PlaceRegiMgService placeRegiMgService;
	
	@GetMapping()
	@ApiOperation(value="getPlaceRegi", notes="대륙 조회", response=PlaceRegiMgPagingRVO.class)
	public CommonMessage getPlaceRegi(@ModelAttribute PlaceRegiMgPagingPVO placeRegiMgPagingPVO) {
		this.parameterLog("PlaceRegiMg[getPlaceRegi]", placeRegiMgPagingPVO);
		CommonMessage output = new CommonMessage();
		
		List<PlaceRegiMgPagingRVO> listCunt = placeRegiMgService.getPlaceRegi(placeRegiMgPagingPVO);
		
		output.setOk();
		output.setData(listCunt);
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postPlaceRegi", notes="대륙 등록", response=Integer.class)
	public CommonMessage postPlaceRegi(@RequestBody PlaceRegiMgVO placeRegiMgVO) throws UserException {
		this.parameterLog("PlaceRegiMg[postPlaceRegi]", placeRegiMgVO);
		CommonMessage output = new CommonMessage();
		
		int insCnt = placeRegiMgService.postPlaceRegi(placeRegiMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putPlaceRegi", notes="대륙 수정", response=Integer.class)
	public CommonMessage putPlaceRegi(@RequestBody PlaceRegiMgVO placeRegiMgVO) throws UserException {
		this.parameterLog("PlaceRegiMg[putPlaceRegi]", placeRegiMgVO);
		CommonMessage output = new CommonMessage();
		
		int updCnt = placeRegiMgService.putPlaceRegi(placeRegiMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deletePlaceRegi", notes="대륙 삭제", response=Integer.class)
	public CommonMessage deletePlaceRegi(@RequestBody PlaceRegiMgVO placeRegiMgVO) throws UserException {
		this.parameterLog("PlaceRegiMg[deletePlaceRegi]", placeRegiMgVO);
		CommonMessage output = new CommonMessage();
		
		int delCnt = placeRegiMgService.deletePlaceRegi(placeRegiMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}

