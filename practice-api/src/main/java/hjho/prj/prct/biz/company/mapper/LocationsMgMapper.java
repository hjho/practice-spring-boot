package hjho.prj.prct.biz.company.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.company.model.LocationsMgPagingPVO;
import hjho.prj.prct.biz.company.model.LocationsMgPagingRVO;
import hjho.prj.prct.biz.company.model.LocationsMgVO;

@Mapper
public interface LocationsMgMapper {

	List<LocationsMgPagingRVO> getLocations(LocationsMgPagingPVO locationsMgPagingPVO);

	int postLocations(LocationsMgVO locationsMgVO);

	int putLocations(LocationsMgVO locationsMgVO);

	int deleteLocations(LocationsMgVO locationsMgVO);

	int pkCheck(LocationsMgVO locationsMgVO);

}
