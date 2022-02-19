package hjho.prj.prct.biz.global.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.global.model.CountriesMgPagingPVO;
import hjho.prj.prct.biz.global.model.CountriesMgPagingRVO;
import hjho.prj.prct.biz.global.model.CountriesMgVO;

@Mapper
public interface CountriesMgMapper {

	List<CountriesMgPagingRVO> getCountries(CountriesMgPagingPVO countriesMgPagingPVO);

	int postCountries(CountriesMgVO countriesMgVO);

	int putCountries(CountriesMgVO countriesMgVO);

	int deleteCountries(CountriesMgVO countriesMgVO);

	int pkCheck(CountriesMgVO countriesMgVO);

}
