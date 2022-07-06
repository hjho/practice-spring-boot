package hjho.prj.prct.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MethodWebFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request
			                      , HttpServletResponse response
			                      , FilterChain filterChain)
	throws ServletException, IOException 
	{
		String uri = request.getRequestURI();
		if(uri.startsWith("/js") || uri.startsWith("/css") || uri.startsWith("/images")) {
			filterChain.doFilter(request, response);
		} else {
			log.debug("[F] Method Web : ({}) {}", request.getMethod(), request.getRequestURL());
			if( "GET".equals(request.getMethod())
					|| "POST".equals(request.getMethod())) {
				filterChain.doFilter(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		}
	}

}
