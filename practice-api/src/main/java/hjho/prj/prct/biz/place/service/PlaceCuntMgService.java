package hjho.prj.prct.biz.place.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.place.mapper.PlaceCuntMgMapper;
import hjho.prj.prct.biz.place.model.PlaceCuntMgPagingPVO;
import hjho.prj.prct.biz.place.model.PlaceCuntMgPagingRVO;
import hjho.prj.prct.biz.place.model.PlaceCuntMgVO;
import hjho.prj.prct.common.exception.UserException;

@Service
public class PlaceCuntMgService {
	
	@Autowired 
	private PlaceCuntMgMapper placeCuntMgMapper;
	@Transactional(readOnly=true)
	public List<PlaceCuntMgPagingRVO> getPlaceCunt(PlaceCuntMgPagingPVO placeCuntMgPagingPVO) {
		return placeCuntMgMapper.getPlaceCunt(placeCuntMgPagingPVO);
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int postPlaceCunt(PlaceCuntMgVO placeCuntMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(placeCuntMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(placeCuntMgVO)) {
				throw new UserException("9006", new String[] {"국가아이디"});
			} else {
				insCnt = placeCuntMgMapper.postPlaceCunt(placeCuntMgVO);
			}
		}
		
		return insCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putPlaceCunt(PlaceCuntMgVO placeCuntMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(placeCuntMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(placeCuntMgVO)) {
				updCnt = placeCuntMgMapper.putPlaceCunt(placeCuntMgVO);
			} else {
				throw new UserException("9006", new String[] {"국가아이디"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deletePlaceCunt(PlaceCuntMgVO placeCuntMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(placeCuntMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(placeCuntMgVO)) {
				delCnt = placeCuntMgMapper.deletePlaceCunt(placeCuntMgVO);
			} else {
				throw new UserException("9006", new String[] {"국가아이디"});
			}
		}
		
		return delCnt;
	} 
	
	// 국가 관리 입력값 확인.
	private boolean isInputValOk(PlaceCuntMgVO placeCuntMgVO) throws UserException {
		
		if(placeCuntMgVO != null) {
			
			// 국가아이디
			if(StringUtils.isEmpty(placeCuntMgVO.getCountryId())) {
				throw new UserException("9002", new String[] {"국가아이디"});
			} 
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// 국가 관리 PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(PlaceCuntMgVO placeCuntMgVO) {
		
		int cnt = placeCuntMgMapper.pkCheck(placeCuntMgVO);;
		
		return (cnt == 1);
	}
}
