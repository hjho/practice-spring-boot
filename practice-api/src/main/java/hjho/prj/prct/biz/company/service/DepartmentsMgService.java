package hjho.prj.prct.biz.company.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hjho.prj.prct.biz.company.mapper.DepartmentsMgMapper;
import hjho.prj.prct.biz.company.model.DepartmentsMgPagingPVO;
import hjho.prj.prct.biz.company.model.DepartmentsMgPagingRVO;
import hjho.prj.prct.biz.company.model.DepartmentsMgVO;
import hjho.prj.prct.common.exception.UserException;
import hjho.prj.prct.common.util.LongUtil;

@Service
public class DepartmentsMgService {
	
	@Autowired 
	private DepartmentsMgMapper departmentsMgMapper;
	
	@Transactional(readOnly=true)
	public List<DepartmentsMgPagingRVO> getDepartments(DepartmentsMgPagingPVO departmentsMgPagingPVO) {
		
		return departmentsMgMapper.getDepartments(departmentsMgPagingPVO);
	}
	
	@Transactional(readOnly=true)
	public List<DepartmentsMgPagingRVO> getDepartmentsBox() {
		return departmentsMgMapper.getDepartmentsBox();
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int postDepartments(DepartmentsMgVO departmentsMgVO) throws UserException {
		int insCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(departmentsMgVO, false)) {
			
			insCnt = departmentsMgMapper.postDepartments(departmentsMgVO);
			
		}
		
		return insCnt;
	} 
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int putDepartments(DepartmentsMgVO departmentsMgVO) throws UserException {
		int updCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(departmentsMgVO, true)) {
			
			// PK 확인.
			if(this.isDataOne(departmentsMgVO)) {
				updCnt = departmentsMgMapper.putDepartments(departmentsMgVO);
			} else {
				throw new UserException("9006", new String[] {"부서번호"});
			}
		}
		
		return updCnt;
	}
	
	@Transactional(rollbackFor={DataAccessException.class})
	public int deleteDepartments(DepartmentsMgVO departmentsMgVO) throws UserException {
		int delCnt = 0;
		
		// 입력값 확인.
		if(this.isInputValOk(departmentsMgVO, true)) {
			
			// PK 확인.
			if(this.isDataOne(departmentsMgVO)) {
				delCnt = departmentsMgMapper.deleteDepartments(departmentsMgVO);
			} else {
				throw new UserException("9006", new String[] {"부서번호"});
			}
		}
		
		return delCnt;
	}

	
	// 관리자 관리 입력값 확인.
	private boolean isInputValOk(DepartmentsMgVO departmentsMgVO, boolean isNotSeq) throws UserException {
		
		if(departmentsMgVO != null) {
			
			// 부서 ID 
			if(isNotSeq && LongUtil.isEmpty(departmentsMgVO.getDepartmentId())) {
				throw new UserException("9002", new String[] {"부서번호"});
				
			// 부서 명
			} else if(StringUtils.isEmpty(departmentsMgVO.getDepartmentName())) {
				throw new UserException("9002", new String[] {"부서명"});
			}
			
		} else {
			throw new UserException("9001");
		}
		
		return true;
	}
	
	// 관리자 관리 PK 확인.
	@Transactional(readOnly=true)
	private boolean isDataOne(DepartmentsMgVO departmentsMgVO) {
		
		int cnt = departmentsMgMapper.pkCheck(departmentsMgVO);;
		
		return (cnt == 1);
	}

}
