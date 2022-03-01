package hjho.prj.prct.biz.table;

import hjho.prj.prct.common.clazz.CommonModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SysMgr extends CommonModel {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name="mgrId", value="관리자 아이디")
	private String mgrId;
	
	@ApiModelProperty(name="empId", value="사원 아이디")
	private Long   empId;
	
	@ApiModelProperty(name="mgrPw", value="관리자 비밀번호")
	private String mgrPw;
	
	@ApiModelProperty(name="mgrNm", value="관리자 이름")
	private String mgrNm;
	
	@ApiModelProperty(name="sysMgrStatCd", value="시스템 관리자 상태 코드")
	private String sysMgrStatCd;
	
	@ApiModelProperty(name="pwErrCnt", value="비밀번호 오류 횟수")
	private Long   pwErrCnt;
	
	@ApiModelProperty(name="pwChagDtm", value="비밀번호 변경 일시")
	private String pwChagDtm;
	
	@ApiModelProperty(name="tempPwIssDtm", value="임시 비밀번호 발급일시")
	private String tempPwIssDtm;
	
	@ApiModelProperty(name="rcntAccDtm", value="최근 접속 일시")
	private String rcntAccDtm;
	
	@ApiModelProperty(name="rfshTk", value="새로고침 토큰")
	private String rfshTk;
	
	@ApiModelProperty(name="tkIssDtm", value="토큰 발급 일시")
	private String tkIssDtm;
	
}
