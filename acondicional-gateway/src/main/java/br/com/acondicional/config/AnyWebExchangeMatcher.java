package br.com.acondicional.config;

import org.springframework.web.server.ServerWebExchange;

public class AnyWebExchangeMatcher implements WebExchangeMatcher {

	@Override
	public boolean matches(ServerWebExchange request) {
		return true;
	}

}
