package hjho.prj.prct.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MethodApiFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request
			                      , HttpServletResponse response
			                      , FilterChain filterChain)
	throws ServletException, IOException 
	{
		String method = request.getMethod();
		log.debug("[F] Method Api : {}", method);
		if( "GET".equals(method)
		 || "POST".equals(method) 
		 || "PUT".equals(method) 
		 || "DELETE".equals(method)) {
			// Xss Filter
			filterChain.doFilter(new SimpleXssFilterWrapper(request), response);
		} else {
			log.warn("[ METHOD FILTER WARNNING ]: ({}) {}{}", method, request.getRequestURL(),request.getRequestURI());
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}

}
