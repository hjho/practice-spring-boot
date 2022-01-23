package hjho.prj.prct.biz.main.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper=false)
@ToString(callSuper=true)
public class MainMenuAuthRVO extends MenuAuthVO {

	private static final long serialVersionUID = 1L;

	private List<MenuAuthVO> menuLr;
}
