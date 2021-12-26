package hjho.prj.prct.common.interfazz;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CryptoType {
	
	public enum Division {DEC, ENC}

	public Division dv();
	
	public enum Type {AES, SHA, URI, B64}
	
	public Type tp();
	
}
