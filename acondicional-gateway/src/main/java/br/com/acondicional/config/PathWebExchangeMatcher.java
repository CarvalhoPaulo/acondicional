package br.com.acondicional.config;

import java.util.Objects;

import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;

public class PathWebExchangeMatcher implements WebExchangeMatcher {

	private String pattern;
	private HttpMethod method;
	
	public PathWebExchangeMatcher(String pattern, HttpMethod method) {
		this.pattern = pattern;
		this.method = method;
	}
	
	public PathWebExchangeMatcher(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public boolean matches(ServerWebExchange request){
		HttpMethod method = request.getRequest().getMethod();
		
		String path = request.getRequest().getPath().value();
		return methodMatches(method) && pathMatches(path);
	}

	private boolean pathMatches(String path) {
		return new AntPathMatcher().match(pattern, path);
	}

	private boolean methodMatches(HttpMethod method) {
		return Objects.isNull(this.method) ? true : this.method == method;
	}

}
