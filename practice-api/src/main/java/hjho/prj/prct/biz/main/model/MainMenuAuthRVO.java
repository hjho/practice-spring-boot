package hjho.prj.prct.biz.main.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
@ApiModel(value="MainMenuAuthRVO", description="메인 메뉴 권한 OUTPUT")
public class MainMenuAuthRVO extends MenuAuthVO {

	private List<MenuAuthVO> menuLr;
}
