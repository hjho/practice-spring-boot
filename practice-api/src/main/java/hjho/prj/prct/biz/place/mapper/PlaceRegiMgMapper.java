package hjho.prj.prct.biz.place.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.place.model.PlaceRegiMgPagingPVO;
import hjho.prj.prct.biz.place.model.PlaceRegiMgPagingRVO;
import hjho.prj.prct.biz.place.model.PlaceRegiMgVO;

@Mapper
public interface PlaceRegiMgMapper {

	List<PlaceRegiMgPagingRVO> getPlaceRegi(PlaceRegiMgPagingPVO placeRegiMgPagingPVO);

	int postPlaceRegi(PlaceRegiMgVO placeRegiMgVO);

	int putPlaceRegi(PlaceRegiMgVO placeRegiMgVO);

	int deletePlaceRegi(PlaceRegiMgVO placeRegiMgVO);

	int pkCheck(PlaceRegiMgVO placeRegiMgVO);

}
