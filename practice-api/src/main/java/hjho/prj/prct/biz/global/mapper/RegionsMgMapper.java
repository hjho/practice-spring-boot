package hjho.prj.prct.biz.global.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.global.model.RegionsMgPagingPVO;
import hjho.prj.prct.biz.global.model.RegionsMgPagingRVO;
import hjho.prj.prct.biz.global.model.RegionsMgVO;

@Mapper
public interface RegionsMgMapper {

	List<RegionsMgPagingRVO> getRegions(RegionsMgPagingPVO regionsMgPagingPVO);
	
	List<RegionsMgPagingRVO> getRegionsBox();

	int postRegions(RegionsMgVO regionsMgVO);

	int putRegions(RegionsMgVO regionsMgVO);

	int deleteRegions(RegionsMgVO regionsMgVO);

	int pkCheck(RegionsMgVO regionsMgVO);

}
