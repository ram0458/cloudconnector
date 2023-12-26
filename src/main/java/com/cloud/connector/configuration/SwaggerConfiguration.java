package com.cloud.connector.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	private  ServletContext servletContext;

	@Value("${application-hostname}")
	private String hostName;
	@Value("${context-path}")
	private String path;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).host(hostName)
				.pathProvider(new RelativePathProvider(servletContext) {
			        @Override
			        public String getApplicationBasePath() {
			            return path;
			        }
			    });
	}
}
