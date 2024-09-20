//package com.vnpt.apigateway;
//
//import com.vnpt.apigateway.security.AuthenticationFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EnableHystrix
//public class ApiGatewayConfiguration {
//
//
//
//	@Bean
//	public RouteLocator routes(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(p -> p
//						.path("/get")
//						.filters(f -> f.filter(filter))
//						.uri("http://httpbin.org:80"))
//				.build();
//	}
//
//}
