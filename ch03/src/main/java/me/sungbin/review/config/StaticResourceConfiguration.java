package me.sungbin.review.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class StaticResourceConfiguration  implements WebMvcConfigurer {

  private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	        "classpath:/META-INF/resources/", 
	        "classpath:/resources/",
	        "classpath:/resources/static/",
	        "classpath:/static/", 
	        "classpath:/public/" };


    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	if (!registry.hasMappingForPattern("/**")) {

	        registry.addResourceHandler("/**")
	                .addResourceLocations("CLASSPATH_RESOURCE_LOCATIONS")
	                .setCachePeriod(3600)
	                .resourceChain(true)
	                .addResolver(new PathResourceResolver());

    	}
      
    }
  
}