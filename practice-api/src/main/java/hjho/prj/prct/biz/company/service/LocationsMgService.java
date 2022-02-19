package hjho.prj.prct.biz.company.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.company.mapper.LocationsMgMapper;
import hjho.prj.prct.biz.company.model.LocationsMgPagingPVO;
import hjho.prj.prct.biz.company.model.LocationsMgPagingRVO;
import hjho.prj.prct.biz.company.model.LocationsMgVO;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.LongUtil;

@Service
public class LocationsMgService {
	
	@Autowired 
	private LocationsMgMapper locationsMgMapper;

	@Transactional(readOnly=true)
	public List<LocationsMgPagingRVO> getLocations(LocationsMgPagingPVO locationsMgPagingPVO) {
		return locationsMgMapper.getLocations(locationsMgPagingPVO);
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int postLocations(LocationsMgVO locationsMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(locationsMgVO, false)) {
			
			insCnt = locationsMgMapper.postLocations(locationsMgVO);
			
		}
		
		return insCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putLocations(LocationsMgVO locationsMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(locationsMgVO, true)) {
			
			// PK 확인.
			if(this.isDataOne(locationsMgVO)) {
				updCnt = locationsMgMapper.putLocations(locationsMgVO);
			} else {
				throw new UserException("9006", new String[] {"위치번호"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteLocations(LocationsMgVO locationsMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(locationsMgVO, true)) {
			
			// PK 확인.
			if(this.isDataOne(locationsMgVO)) {
				delCnt = locationsMgMapper.deleteLocations(locationsMgVO);
			} else {
				throw new UserException("9006", new String[] {"위치번호"});
			}
		}
		
		return delCnt;
	} 
	
	// 위치 관리 입력값 확인.
	private boolean isInputValOk(LocationsMgVO locationsMgVO, boolean isNotSeq) throws UserException {
		
		if(locationsMgVO != null) {
			
			// 위치 ID 
			if(isNotSeq && LongUtil.isEmpty(locationsMgVO.getLocationId())) {
				throw new UserException("9002", new String[] {"위치번호"});
				
			// 도시 명
			} else if(StringUtils.isEmpty(locationsMgVO.getCity())) {
				throw new UserException("9002", new String[] {"도시명"});
			}
			
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// 관리자 관리 PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(LocationsMgVO locationsMgVO) {
		
		int cnt = locationsMgMapper.pkCheck(locationsMgVO);;
		
		return (cnt == 1);
	}
}
