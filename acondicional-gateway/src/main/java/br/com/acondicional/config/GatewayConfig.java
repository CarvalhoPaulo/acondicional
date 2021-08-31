package br.com.acondicional.config;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.BooleanSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.acondicional.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		RouteLocatorBuilder.Builder routes = builder.routes();
		for (GatewayRoute route : RouterModule.getRoutes()) {
			routes = routes.route(route.getName(), r -> {
				BooleanSpec spec = r.path(route.getPath());
				if (Objects.nonNull(route.getMethod())) {
					spec = spec.and().method(route.getMethod());
				}
				return spec.filters(f -> f.filter(filter)).uri(route.getUri());
			});
		}
		return routes.build();
	}

}
