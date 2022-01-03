package hjho.prj.prct.biz.place.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.place.model.PlaceCuntMgPagingPVO;
import hjho.prj.prct.biz.place.model.PlaceCuntMgPagingRVO;
import hjho.prj.prct.biz.place.model.PlaceCuntMgVO;

@Mapper
public interface PlaceCuntMgMapper {

	List<PlaceCuntMgPagingRVO> getPlaceCunt(PlaceCuntMgPagingPVO placeCuntMgPagingPVO);

	int postPlaceCunt(PlaceCuntMgVO placeCuntMgVO);

	int putPlaceCunt(PlaceCuntMgVO placeCuntMgVO);

	int deletePlaceCunt(PlaceCuntMgVO placeCuntMgVO);

	int pkCheck(PlaceCuntMgVO placeCuntMgVO);

}
