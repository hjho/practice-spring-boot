package hjho.prj.prct.biz.place.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.place.mapper.PlaceRegiMgMapper;
import hjho.prj.prct.biz.place.model.PlaceRegiMgPagingPVO;
import hjho.prj.prct.biz.place.model.PlaceRegiMgPagingRVO;
import hjho.prj.prct.biz.place.model.PlaceRegiMgVO;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.LongUtil;

@Service
public class PlaceRegiMgService {
	
	@Autowired 
	private PlaceRegiMgMapper placeRegiMgMapper;
	
	@Transactional(readOnly=true)
	public List<PlaceRegiMgPagingRVO> getPlaceRegi(PlaceRegiMgPagingPVO placeRegiMgPagingPVO) {
		return placeRegiMgMapper.getPlaceRegi(placeRegiMgPagingPVO);
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int postPlaceRegi(PlaceRegiMgVO placeRegiMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(placeRegiMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(placeRegiMgVO)) {
				throw new UserException("9005", new String[] {"대륙번호"});
			} else {
				insCnt = placeRegiMgMapper.postPlaceRegi(placeRegiMgVO);
			}
		}
		
		return insCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putPlaceRegi(PlaceRegiMgVO placeRegiMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(placeRegiMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(placeRegiMgVO)) {
				updCnt = placeRegiMgMapper.putPlaceRegi(placeRegiMgVO);
			} else {
				throw new UserException("9006", new String[] {"대륙번호"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deletePlaceRegi(PlaceRegiMgVO placeRegiMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(placeRegiMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(placeRegiMgVO)) {
				delCnt = placeRegiMgMapper.deletePlaceRegi(placeRegiMgVO);
			} else {
				throw new UserException("9006", new String[] {"대륙번호"});
			}
		}
		
		return delCnt;
	} 
	
	// 국가 관리 입력값 확인.
	private boolean isInputValOk(PlaceRegiMgVO placeRegiMgVO) throws UserException {
		
		if(placeRegiMgVO != null) {
			
			// 대륙번호
			if(LongUtil.isEmpty(placeRegiMgVO.getRegionId())) {
				throw new UserException("9002", new String[] {"대륙번호"});
			} 
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// 국가 관리 PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(PlaceRegiMgVO placeRegiMgVO) {
		
		int cnt = placeRegiMgMapper.pkCheck(placeRegiMgVO);;
		
		return (cnt == 1);
	}
}
