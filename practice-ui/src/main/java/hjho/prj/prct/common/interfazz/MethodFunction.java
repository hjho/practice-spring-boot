package hjho.prj.prct.common.interfazz;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodFunction {

	public Function value() default Function.M;
	
	public enum Function {  C	// 등록
						  , R	// 조회
						  , U	// 수정
						  , D	// 삭제
						  , M	// 화면이동
						  , E 	// 출력
						  , L	// 기타 Logic
	}
						
}
