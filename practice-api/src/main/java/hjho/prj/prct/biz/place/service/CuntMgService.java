package hjho.prj.prct.biz.place.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hjho.prj.prct.biz.place.mapper.CuntMgMapper;
import hjho.prj.prct.biz.place.model.CuntMgPagingPVO;
import hjho.prj.prct.biz.place.model.CuntMgPagingRVO;

@Service
public class CuntMgService {
	
	@Autowired 
	private CuntMgMapper cuntMgMapper;
	
	public List<CuntMgPagingRVO> getCunt(CuntMgPagingPVO cuntMgPagingPVO) {
		return cuntMgMapper.getCunt(cuntMgPagingPVO);
	} 
	
}
