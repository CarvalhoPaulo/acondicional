package br.com.acondicional.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.web.server.ServerWebExchange;

public class GatewayRoute {
	private String name;
	private String path;
	private HttpMethod[] method;
	private String uri;
	private boolean secure;
	private List<WebExchangeMatcher> privateMatchers = new ArrayList<>();
	private List<WebExchangeMatcher> publicMatchers = new ArrayList<>();

	private GatewayRoute(Builder builder) {
		this.name = builder.name;
		this.path = builder.path;
		this.uri = builder.uri;
		this.secure = builder.secure;
		this.method = builder.method;
		this.privateMatchers = builder.privateMatchers;
		this.publicMatchers = builder.publicMatchers;
	}
	
	public String getName() {
		return name;
	}
	public String getPath() {
		return path;
	}
	public String getUri() {
		return uri;
	}
	public boolean isSecure() {
		return secure;
	}
	public HttpMethod[] getMethod() {
		return method;
	}

	public boolean matches(ServerWebExchange request) {
		for (WebExchangeMatcher matcher : publicMatchers) {
			if (matcher.matches(request)) {
				return false;
			}
		}
		return privateMatchers.stream()
				.map((matcher) -> matcher.matches(request))
				.anyMatch(match -> match);
	}
	
	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String name;
		private String path;
		private String uri;
		private boolean secure;
		private HttpMethod[] method;
		private List<WebExchangeMatcher> privateMatchers = new ArrayList<>();
		private List<WebExchangeMatcher> publicMatchers = new ArrayList<>();

		private Builder() {
		}

		public Builder withName(String name) {
			this.name = name;
			return this;
		}

		public Builder withPath(String path) {
			this.path = path;
			return this;
		}

		public Builder withUri(String uri) {
			this.uri = uri;
			return this;
		}
		
		public MatcherBuilder anyMatchers() {
			return new MatcherBuilder(new AnyWebExchangeMatcher(), this);
		}
		
		public MatcherBuilder addMatcher(String pattern) {
			return new MatcherBuilder(new PathWebExchangeMatcher(pattern), this);
		}
		
		public MatcherBuilder addMatcher(String pattern, HttpMethod method) {
			return new MatcherBuilder(new PathWebExchangeMatcher(pattern, method), this);
		}
		
		public Builder withMethod(HttpMethod... method) {
			this.method = method;
			return this;
		}

		public GatewayRoute build() {
			return new GatewayRoute(this);
		}
	}
	
	public static final class MatcherBuilder {
		private final WebExchangeMatcher matcher;
		private final Builder builder;
		
		public MatcherBuilder(WebExchangeMatcher serverWebExchangeMatcher, Builder builder) {
			this.matcher = serverWebExchangeMatcher;
			this.builder = builder;
		}
		
		public Builder permitAll() {
			builder.publicMatchers.add(matcher);
			return builder;
		}
		
		public Builder authenticated() {
			builder.privateMatchers.add(matcher);
			return builder;
		}
	}
}
