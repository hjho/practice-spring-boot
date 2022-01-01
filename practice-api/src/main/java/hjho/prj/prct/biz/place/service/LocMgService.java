package hjho.prj.prct.biz.place.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.place.mapper.LocMgMapper;
import hjho.prj.prct.biz.place.model.LocMgPagingPVO;
import hjho.prj.prct.biz.place.model.LocMgPagingRVO;

@Service
public class LocMgService {
	
	@Autowired 
	private LocMgMapper locMgMapper;
	
	public List<LocMgPagingRVO> getLoc(LocMgPagingPVO locMgPagingPVO) {
		return locMgMapper.getLoc(locMgPagingPVO);
	} 
	
}
