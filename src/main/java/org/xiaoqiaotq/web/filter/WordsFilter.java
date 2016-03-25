package org.xiaoqiaotq.web.filter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(1)
public class WordsFilter implements Filter {
	private SensitiveWordUtils filter;

	//初始化将敏感词库全部加载到集合中
	public void init(FilterConfig filterConfig) throws ServletException {
		 filter = new SensitiveWordUtils();
	}
	
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		//用户提交的数据是否包含审核词和替换词，自定义包装类
		SensitiveRequest sensitiveRequest = new SensitiveRequest(request);
		//放行
		chain.doFilter(sensitiveRequest, response);
	}
	
	class SensitiveRequest extends HttpServletRequestWrapper{
		
		private HttpServletRequest request;
		public SensitiveRequest(HttpServletRequest request){
			super(request);
			this.request = request;
		}
		@Override
		public String getParameter(String name) {
			
			String value = this.request.getParameter(name);
			if(value!=null){
				value = filter.replaceSensitiveWord(value, 1, "*");
			}
			return value;
		}

		@Override
		public String[] getParameterValues(String name) {
			String[] parameterValues = super.getParameterValues(name);
			if (parameterValues != null) {
				parameterValues= Arrays.stream(parameterValues).map(v -> filter.replaceSensitiveWord(v, 1, "*")).toArray(String[]::new);
			}
			return parameterValues;

		}

		@Override
		public Map<String, String[]> getParameterMap() {
			Map<String, String[]> parameterMap = super.getParameterMap();
			HashMap<String, String[]> results = new HashMap<>(parameterMap);
			results.forEach((k,v)->{
				String[] values = Arrays.stream(v).map(v1 -> filter.replaceSensitiveWord(v1, 1, "*")).toArray(String[]::new);
				results.put(k, values);
			});
			return results;
		}
	}
	public void destroy() {
	}

}