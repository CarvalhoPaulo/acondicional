package br.com.acondicional.config;

import org.springframework.web.server.ServerWebExchange;

public interface WebExchangeMatcher {

	public boolean matches(ServerWebExchange request);

}
