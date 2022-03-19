package hjho.prj.prct.common.clazz;

public class URI {

	/* DEFAULT */
	private static final String DEF    = "/api";
	
	/* PACKAGE */
	private static final String MAIN    = "/main";
	private static final String GLOBAL  = "/global";
	private static final String COMPANY = "/company";
	private static final String SYSTEM  = "/system";
	
	/* SERVICE */
	private static final String REGIONS      = "/regions";
	private static final String COUNTRIES    = "/countries";
	private static final String LOCATIONS    = "/locations";
	private static final String DEPARTMENTS  = "/departments";
	private static final String JOBS         = "/jobs";
	private static final String JOBS_HISTORY = "/jobs/history";
	private static final String EMPLOYEES    = "/employees";

	private static final String LOGIN_PROC   = "/login/proc";
	private static final String AUTH_MENU    = "/auth/menu";
	private static final String AUTH_MANAGER = "/auth/mgr";
	private static final String TOKEN_ISSUE  = "/token/issue";
	
	private static final String MENU         = "/menu";
	private static final String MANAGER      = "/mgr";
	private static final String GROUP        = "/grp";
	private static final String AUTHORISE    = "/auth";
	private static final String ROLE         = "/role";
	
/* Default XE Oracle Table */
	// 세계정보관리 > 대륙관리
	public static final String GLOBAL_REGIONS_API       = DEF.concat(GLOBAL).concat(REGIONS);
	// 세계정보관리 > 국가관리 
	public static final String GLOBAL_COUNTRIES_API     = DEF.concat(GLOBAL).concat(COUNTRIES);
	
	// 회사정보관리 > 위치관리
	public static final String COMPANY_LOCATIONS_API    = DEF.concat(COMPANY).concat(LOCATIONS);
	// 회사정보관리 > 부서관리
	public static final String COMPANY_DEPARTMENTS_API  = DEF.concat(COMPANY).concat(DEPARTMENTS);
	// 회사정보관리 > 직책관리
	public static final String COMPANY_JOBS_API         = DEF.concat(COMPANY).concat(JOBS);
	// 회사정보관리 > 직책이력관리
	public static final String COMPANY_JOBS_HISTORY_API = DEF.concat(COMPANY).concat(JOBS_HISTORY);
	// 회사정보관리 > 사원관리
	public static final String COMPANY_EMPLOYEES_API    = DEF.concat(COMPANY).concat(EMPLOYEES);

/* Add Table */
	// 시스템관리 > 메뉴관리
	public static final String SYSTEM_MENU_API          = DEF.concat(SYSTEM).concat(MENU);
	// 시스템관리 > 관리자관리
	public static final String SYSTEM_MGR_API           = DEF.concat(SYSTEM).concat(MANAGER);
	// 시스템관리 > 관리자그룹관리
	public static final String SYSTEM_MGR_GRP_API       = DEF.concat(SYSTEM).concat(MANAGER).concat(GROUP);
	// 시스템관리 > 관리자역할관리
	public static final String SYSTEM_MGR_ROLE_API      = DEF.concat(SYSTEM).concat(MANAGER).concat(ROLE);
	// 시스템관리 > 관리자권한관리
	public static final String SYSTEM_MGR_AUTH_API      = DEF.concat(SYSTEM).concat(MANAGER).concat(AUTHORISE);
	
/* Main Service */
	// 로그인 검증.
	public static final String MAIN_LOGIN_PROC_API      = DEF.concat(MAIN).concat(LOGIN_PROC);
	// 메뉴 권한 조회.
	public static final String MAIN_MENU_AUTH_API       = DEF.concat(MAIN).concat(AUTH_MENU);
	// 관리자 권한 조회.
	public static final String MAIN_MGR_AUTH_API        = DEF.concat(MAIN).concat(AUTH_MANAGER);
	// 토큰 발급.
	public static final String MAIN_TOKEN_ISSUE_API     = DEF.concat(MAIN).concat(TOKEN_ISSUE);
	
}
