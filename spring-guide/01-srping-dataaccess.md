## Jpa와 하이버네이트 연결방법

#### JPA(Java Persistence API)
JavaEE와 JavaSE를 위한 영속성(persistence) 관리와 O/R 매핑(ORM) 을 위한 표준기술이다.

#### Hibernate
하이버네이트는 가장 크게 성공한 오픈소스 ORM 프레임워크이다. 복잡한 엔티티빈 대신 평범한 POJO로 SQL을 직접 사용하는 전통적인 방식 못지않게 강력하고 빠르면서도 편리한 ORM방식의 개발이 가능함을 보여줌.

#### JPA와 Hibernate 와의 차이점

||Hibernate|JPA|
|---|---|---|
|DB에 대한 접근|SessionFactory|EntityManagerFactory|
|DB의 query executor|Session|EntityManager|
|SELECT|find|find|
|INSERT|save or saveOrUpdate|merge|
|UPDATE|update or saveOrUpdate|merge|
|DELETE|delete|remove|
|query적용방법|Criteria, HQL, NativeSQL|Criteria, JPQL, NativeSQL|


#### JPA 활용에 대한 ApplicationContext XML을 이용한방식

1)jpaApplicationcontext.xml 방식

```xml
	<context:property-placeholder location="spring.properties" />
	<bean id="dataSource" class="com.jolbox.bonecp.BoneCPDataSource"
		destroy-method="close">
		<property name="driverClass" value="${connect.driver}" />
		<property name="jdbcUrl" value="${connect.url}" />
		<property name="username" value="${connect.username}" />
		<property name="password" value="${connect.password}" />
		<property name="idleConnectionTestPeriodInMinutes" value="60" />
		<property name="idleMaxAgeInMinutes" value="240" />
		<property name="maxConnectionsPerPartition" value="5" />
		<property name="minConnectionsPerPartition" value="5" />
		<property name="partitionCount" value="3" />
		<property name="acquireIncrement" value="5" />
		<property name="statementsCacheSize" value="100" />
		<property name="releaseHelperThreads" value="3" />
	</bean>

	<context:component-scan base-package="com.bookstore.bshibernate.services" />
	<context:component-scan base-package="com.bookstore.bshibernate.utils" />

	<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
		<property name="dataSource" ref="dataSource" />
		<property name="entityManagerFactory" ref="entityManagerFactory" />
	</bean>

	<util:map id="jpaPropertyMap">
		<entry key="hibernate.dialect" value="org.hibernate.dialect.MySQL5InnoDBDialect" />
	</util:map>

	<bean id="entityManagerFactory"
		class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
		<property name="packagesToScan" value="com.bookstore.bshibernate.entities" />
		<property name="dataSource" ref="dataSource" />
		<property name="jpaVendorAdapter">
			<bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter" />
		</property>
		<property name="jpaPropertyMap" ref="jpaPropertyMap" />
	</bean>

	<jpa:repositories base-package="com.bookstore.bshibernate.repository"
		entity-manager-factory-ref="entityManagerFactory"
		transaction-manager-ref="transactionManager" />

	<bean id="persistenceExceptionTranslationPostProcessor"
		class="org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor" />

	<context:annotation-config />
```

2) JpaApplicationConfiguration 방식

```java
@Configuration
@ComponentScan(value = { 
		"com.bookstore.bshibernate.services",
		"com.bookstore.bshibernate.utils" })
@EnableJpaRepositories(basePackages = { 
		"com.bookstore.bshibernate.repository" }, 
		entityManagerFactoryRef = "entityManagerFactory")
@EnableTransactionManagement
@PropertySource("spring.properties")
public class JpaApplicationConfiguration {

	@Autowired
	private Environment env;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setUsername(env.getProperty("connect.username"));
		dataSource.setPassword(env.getProperty("connect.password"));
		dataSource.setJdbcUrl(env.getProperty("connect.url"));
		dataSource.setDriverClass(env.getProperty("connect.driver"));
		dataSource.setIdleConnectionTestPeriodInMinutes(60);
		dataSource.setIdleMaxAgeInMinutes(240);
		dataSource.setMaxConnectionsPerPartition(30);
		dataSource.setMinConnectionsPerPartition(10);
		dataSource.setPartitionCount(3);
		dataSource.setAcquireIncrement(5);
		dataSource.setReleaseHelperThreads(3);
		return dataSource;
	}

	/*
	 * xml 파일을 가져 오기 의해 new ClassPathResource 를 사용
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		Map<String, String> jpaPropertyMap = new HashMap<String, String>();
		jpaPropertyMap.put("hibernate.dialect",
				"org.hibernate.dialect.MySQL5InnoDBDialect");

		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory
				.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory
				.setPackagesToScan("com.bookstore.bshibernate.entities");
		entityManagerFactory.setJpaPropertyMap(jpaPropertyMap);
		entityManagerFactory.setPersistenceUnitName("defaultEntity");
		return entityManagerFactory;
	}

	// sessionFactoryFactory().getObject() FactoryFactory 이기 때문에 getObject()
	// 받아야한다.
	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setDataSource(dataSource());
		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());
		return transactionManager;
	}

	/**
	 * JPA Repository에 @Repository 를 붙이고, 아래의 빈을 등록하면 JPA Repository (DAO)에서 발생하는
	 * 예외가 스프링 DataAccessException으로 전환돼어 서비스 계층으로 던져진다.
	 *  
	 * @Repository 애노테이션이 붙은 빈을 찾아서 예외 변환 기능을 가진 AOP 어드바이스를
	 * 적용해주는 후처리 기능을 제공 
	 * @return exception
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	// xml 를 클래스로 하용하기 위해서는 꼭 존재 해야한다.
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
```

3) spring.properties 파일

```txt
connect.driver=com.mysql.jdbc.Driver
connect.url=jdbc:mysql://localhost:3306/bookstore
connect.username=xxx
connect.password=xxx
```
