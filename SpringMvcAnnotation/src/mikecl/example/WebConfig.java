package mikecl.example;



import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan("mikecl.example")
@MapperScan("mikecl.example.mapper")
public class WebConfig extends WebMvcConfigurerAdapter 
{
	@Bean
	public ITemplateResolver templateResolver()
	{
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("/WEB-INF/web/");
		resolver.setSuffix(".html");
		resolver.setCharacterEncoding("UTF-8");
		resolver.setTemplateMode(TemplateMode.HTML);		
		return resolver;		
	}
	
	@Bean
	public TemplateEngine templateEngine()
	{
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver());
		return engine;
	}
	
	@Bean
	public ViewResolver viewResolver()
	{
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setContentType("text/html;charset=UTF-8");
		return resolver;
	}
	
	@Bean  
	public DataSource dataSource()
	{  
		BasicDataSource dataSource = new BasicDataSource();	
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");  
		dataSource.setUrl("jdbc:mysql://localhost:3306/mvc?useUnicode=true&amp;characterEncoding=utf8");  
		dataSource.setUsername("root");  
		dataSource.setPassword("123456"); 
        return dataSource;  
    }  
	
	@Bean  	
	public SqlSessionFactoryBean sqlSessionFactory()
	{  
	    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();  
	    sqlSessionFactoryBean.setDataSource(this.dataSource());
	    sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("/mikecl/example/entity/xml/MyBatisConfig.xml"));  
	    return sqlSessionFactoryBean;  
	} 
	
	@Bean  
	public SqlSessionTemplate sqlSessionTemplate() throws Exception
	{  
	    SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(this.sqlSessionFactory().getObject());  
	    return sqlSessionTemplate;  
	}
	
}
