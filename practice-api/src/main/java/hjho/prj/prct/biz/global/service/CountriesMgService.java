package hjho.prj.prct.biz.global.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.global.mapper.CountriesMgMapper;
import hjho.prj.prct.biz.global.model.CountriesMgPagingPVO;
import hjho.prj.prct.biz.global.model.CountriesMgPagingRVO;
import hjho.prj.prct.biz.global.model.CountriesMgVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class CountriesMgService {
	
	@Autowired 
	private CountriesMgMapper countriesMgMapper;
	
	@Transactional(readOnly=true)
	public List<CountriesMgPagingRVO> getCountries(CountriesMgPagingPVO countriesMgPagingPVO) {
		return countriesMgMapper.getCountries(countriesMgPagingPVO);
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int postCountries(CountriesMgVO countriesMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(countriesMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(countriesMgVO)) {
				throw new UserException("9005", new String[] {"국가아이디"});
			} else {
				insCnt = countriesMgMapper.postCountries(countriesMgVO);
			}
		}
		
		return insCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putCountries(CountriesMgVO countriesMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(countriesMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(countriesMgVO)) {
				updCnt = countriesMgMapper.putCountries(countriesMgVO);
			} else {
				throw new UserException("9006", new String[] {"국가아이디"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteCountries(CountriesMgVO countriesMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(countriesMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(countriesMgVO)) {
				delCnt = countriesMgMapper.deleteCountries(countriesMgVO);
			} else {
				throw new UserException("9006", new String[] {"국가아이디"});
			}
		}
		
		return delCnt;
	} 
	
	// 국가 관리 입력값 확인.
	private boolean isInputValOk(CountriesMgVO countriesMgVO) throws UserException {
		
		if(countriesMgVO != null) {
			
			// 국가아이디
			if(StringUtils.isEmpty(countriesMgVO.getCountryId())) {
				throw new UserException("9002", new String[] {"국가아이디"});
			} 
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// 국가 관리 PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(CountriesMgVO countriesMgVO) {
		
		int cnt = countriesMgMapper.pkCheck(countriesMgVO);;
		
		return (cnt == 1);
	}
	
}
