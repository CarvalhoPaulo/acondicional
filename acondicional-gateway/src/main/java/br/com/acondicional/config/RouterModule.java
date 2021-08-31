package br.com.acondicional.config;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class RouterModule {
	private static final List<GatewayRoute> ROUTES = Arrays.asList(
			GatewayRoute.builder()
				.withName("auth")
				.withPath("/auth-api/**")
				.withUri("lb://auth")
				.build(),
			GatewayRoute.builder()
				.withName("user")
				.withPath("/user-api/**")
				.withUri("lb://user")
				// .addMatcher("/user-api/**", HttpMethod.GET).permitAll()
				.anyMatchers().authenticated()
				.build(),
			GatewayRoute.builder()
				.withName("product")
				.withPath("/product-api/**")
				.withUri("lb://product")
				.anyMatchers().authenticated()
				.build()
		);
	
	public Predicate<ServerWebExchange> isSecured =
            request -> ROUTES
                    .stream()
                    .anyMatch(route -> route.matches(request));
            
    public static List<GatewayRoute> getRoutes() {
    	return ROUTES;
    }
}
