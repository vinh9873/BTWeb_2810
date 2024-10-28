package vn.ute.configs;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	 protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {

	 // Assigning default decorator if no path specific decorator found
	 builder.addDecoratorPath("/*", "/web.jsp")

	 // Map decorators to specific path patterns.
	 .addDecoratorPath("/admin/*", "/admin.jsp")

	 // Exclude few paths from decoration.
	 .addExcludedPath("/login*").addExcludedPath("/login/*")
	 .addExcludedPath("/alogin*").addExcludedPath("/alogin/*")
	 .addExcludedPath("/api/**").addExcludedPath("/api/**");
	 
	 }
	
	@Bean
	 public FilterRegistrationBean<MySiteMeshFilter> siteMeshFilter() {
		
	 FilterRegistrationBean<MySiteMeshFilter> filterRegistrationBean 
	         = new FilterRegistrationBean<MySiteMeshFilter>();
	 filterRegistrationBean.setFilter(new MySiteMeshFilter()); // adding sitemesh filter ??
     filterRegistrationBean.addUrlPatterns("/*");
	 return filterRegistrationBean;

	 }

}
