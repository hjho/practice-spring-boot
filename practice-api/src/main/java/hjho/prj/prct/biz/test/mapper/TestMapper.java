package hjho.prj.prct.biz.test.mapper;

import org.apache.ibatis.annotations.Mapper;

import hjho.prj.prct.biz.test.model.CryptoTestVO;

@Mapper
public interface TestMapper {

	CryptoTestVO crytoTest(CryptoTestVO cryptoTestVO);

}
