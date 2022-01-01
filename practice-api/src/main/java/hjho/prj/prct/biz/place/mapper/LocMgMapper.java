package hjho.prj.prct.biz.place.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.place.model.LocMgPagingPVO;
import hjho.prj.prct.biz.place.model.LocMgPagingRVO;

@Mapper
public interface LocMgMapper {

	List<LocMgPagingRVO> getLoc(LocMgPagingPVO locMgPagingPVO);

}
