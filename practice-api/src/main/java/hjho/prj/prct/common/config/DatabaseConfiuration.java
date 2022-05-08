package hjho.prj.prct.common.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import hjho.prj.prct.common.handler.SqlLogInterceptor;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages="hjho.prj.prct.biz")
public class DatabaseConfiuration {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private SqlLogInterceptor sqlLogInterceptor;

	@Value("${spring.datasource.hikari.jdbc-url}")
    private String jdbcUrl;
	@Value("${spring.datasource.hikari.username}")
	private String userName;
	@Value("${spring.datasource.hikari.password}")
	private String password;
    
	@Bean
	// @ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
		config.setUsername(userName);
		config.setPassword(password);
		config.setJdbcUrl(jdbcUrl);
		config.setConnectionTimeout(20000);	// 20 초
		config.setMaxLifetime(1200000);		// 20 분
		config.setAutoCommit(false);		// auto commit
		return config;
	}
	
	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		org.apache.ibatis.session.Configuration cofiuration 
			= new org.apache.ibatis.session.Configuration();
		cofiuration.setMapUnderscoreToCamelCase(true);
		return cofiuration; 
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(this.dataSource());
		sqlSessionFactoryBean.setConfiguration(this.mybatisConfig());
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/**/*.xml"));
		// 뒤에 있는 인터셉터가 먼저 시작.
		sqlSessionFactoryBean.setPlugins(sqlLogInterceptor);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() throws URISyntaxException
	                                                             ,GeneralSecurityException
	                                                             ,ParseException
	                                                             ,IOException
	                                                             ,DataAccessException {
		return new DataSourceTransactionManager(this.dataSource());
	}
}
