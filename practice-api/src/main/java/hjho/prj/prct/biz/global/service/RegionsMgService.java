package hjho.prj.prct.biz.global.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.global.mapper.RegionsMgMapper;
import hjho.prj.prct.biz.global.model.RegionsMgPagingPVO;
import hjho.prj.prct.biz.global.model.RegionsMgPagingRVO;
import hjho.prj.prct.biz.global.model.RegionsMgVO;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.LongUtil;

@Service
public class RegionsMgService {
	
	@Autowired 
	private RegionsMgMapper regionsMgMapper;
	
	@Transactional(readOnly=true)
	public List<RegionsMgPagingRVO> getRegions(RegionsMgPagingPVO regionsMgPagingPVO) {
		return regionsMgMapper.getRegions(regionsMgPagingPVO);
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int postRegions(RegionsMgVO regionsMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(regionsMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(regionsMgVO)) {
				throw new UserException("9005", new String[] {"대륙번호"});
			} else {
				insCnt = regionsMgMapper.postRegions(regionsMgVO);
			}
		}
		
		return insCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putRegions(RegionsMgVO regionsMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(regionsMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(regionsMgVO)) {
				updCnt = regionsMgMapper.putRegions(regionsMgVO);
			} else {
				throw new UserException("9006", new String[] {"대륙번호"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteRegions(RegionsMgVO regionsMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(regionsMgVO)) {
			
			// PK 확인.
			if(this.isDataOne(regionsMgVO)) {
				delCnt = regionsMgMapper.deleteRegions(regionsMgVO);
			} else {
				throw new UserException("9006", new String[] {"대륙번호"});
			}
		}
		
		return delCnt;
	} 
	
	// 국가 관리 입력값 확인.
	private boolean isInputValOk(RegionsMgVO regionsMgVO) throws UserException {
		
		if(regionsMgVO != null) {
			
			// 대륙번호
			if(LongUtil.isEmpty(regionsMgVO.getRegionId())) {
				throw new UserException("9002", new String[] {"대륙번호"});
			} 
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// 국가 관리 PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(RegionsMgVO RegionsMgVO) {
		
		int cnt = regionsMgMapper.pkCheck(RegionsMgVO);;
		
		return (cnt == 1);
	}
}
