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

import hjho.prj.prct.biz.global.model.CountriesMgPagingPVO;
import hjho.prj.prct.biz.global.model.CountriesMgPagingRVO;
import hjho.prj.prct.biz.global.model.CountriesMgVO;
import hjho.prj.prct.biz.global.service.CountriesMgService;
import hjho.prj.prct.common.clazz.CommonController;
import hjho.prj.prct.common.clazz.CommonMessage;
import hjho.prj.prct.common.exception.UserException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/global/countries")
@Api(tags="CountriesMg", value="국가 관리", description="국가 관리")
public class CountriesMgController extends CommonController {
	
	@Autowired 
	private CountriesMgService countriesMgService;
	
	@GetMapping()
	@ApiOperation(value="getCountries", notes="국가 조회", response=CountriesMgPagingRVO.class)
	public CommonMessage getCountries(@ModelAttribute CountriesMgPagingPVO countriesMgPagingPVO) {
		this.parameterLog("CountriesMg[getCountries]", countriesMgPagingPVO);
		CommonMessage output = new CommonMessage();
		
		List<CountriesMgPagingRVO> listCunt = countriesMgService.getCountries(countriesMgPagingPVO);
		
		output.setOk();
		output.setData(listCunt);
		return output;
	}
	
	@PostMapping()
	@ApiOperation(value="postCountries", notes="국가 등록", response=Integer.class)
	public CommonMessage postCountries(@RequestBody CountriesMgVO countriesMgVO) throws UserException {
		this.parameterLog("CountriesMg[postCountries]", countriesMgVO);
		CommonMessage output = new CommonMessage();
		
		int insCnt = countriesMgService.postCountries(countriesMgVO);
		
		output.setOk();
		output.setData(insCnt);
		if(insCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@PutMapping()
	@ApiOperation(value="putCountries", notes="국가 수정", response=Integer.class)
	public CommonMessage putCountries(@RequestBody CountriesMgVO countriesMgVO) throws UserException {
		this.parameterLog("CountriesMg[putCountries]", countriesMgVO);
		CommonMessage output = new CommonMessage();
		
		int updCnt = countriesMgService.putCountries(countriesMgVO);
		
		output.setOk();
		output.setData(updCnt);
		if(updCnt < 1) {
			output.setError();
		}
		return output;
	}
	
	@DeleteMapping()
	@ApiOperation(value="deleteCountries", notes="국가 삭제", response=Integer.class)
	public CommonMessage deleteCountries(@RequestBody CountriesMgVO countriesMgVO) throws UserException {
		this.parameterLog("CountriesMg[deleteCountries]", countriesMgVO);
		CommonMessage output = new CommonMessage();
		
		int delCnt = countriesMgService.deleteCountries(countriesMgVO);
		
		output.setOk();
		output.setData(delCnt);
		if(delCnt < 1) {
			output.setError();
		}
		return output;
	}
	
}

