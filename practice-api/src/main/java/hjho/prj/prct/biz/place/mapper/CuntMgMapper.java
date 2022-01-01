package hjho.prj.prct.biz.place.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.place.model.CuntMgPagingPVO;
import hjho.prj.prct.biz.place.model.CuntMgPagingRVO;

@Mapper
public interface CuntMgMapper {

	List<CuntMgPagingRVO> getCunt(CuntMgPagingPVO cuntMgPagingPVO);

}
