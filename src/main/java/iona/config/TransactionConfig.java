package iona.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 本配置类实现全局事务管理，和如下的xml实现一致
 * 	<bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
 * 		<property name="dataSource" ref="dataSource"/>
 * 	</bean>
 *
 * 	<tx:advice id="txAdvice" transaction-manager="txManager">
 * 		<tx:attributes>
 * 			<tx:method name="*"/>
 * 		</tx:attributes>
 * 	</tx:advice>
 *
 * 	<tx:advice id="txAdviceForOther" transaction-manager="txManager">
 * 		<tx:attributes>
 * 			<tx:method name="*Transaction"/>
 * 		</tx:attributes>
 * 	</tx:advice>
 *
 * 	<aop:config>
 * 		<aop:pointcut id="ct" expression="execution(* com.b_controller..*(..))" />
 * 		<aop:pointcut id="sv" expression="execution(* com.c_service..*(..))" />
 * 		<aop:pointcut id="dao" expression="execution(* com.d_dao..*(..))" />
 * 		<aop:pointcut id="mapper" expression="execution(* com.h_mapper.*(..))" />
 * 		<aop:pointcut id="other" expression="execution(* com..*(..))" />
 * 		<aop:advisor pointcut-ref="ct" advice-ref="txAdvice" />
 * 		<aop:advisor pointcut-ref="sv" advice-ref="txAdvice" />
 * 		<aop:advisor pointcut-ref="dao" advice-ref="txAdvice" />
 * 		<aop:advisor pointcut-ref="other" advice-ref="txAdviceForOther" />
 * 	</aop:config>
 */
@Configuration
public class TransactionConfig {
    private static final String ALL = "execution (* iona..*(..))";
    private static final String CONTROLLER = "execution(* iona.controller..*(..))";
    private static final String SERVICE = "execution (* iona.service..*(..))";
    private static final String DAO = "execution (* iona.dao..*(..))";
    private static final String MAPPER = "execution (* iona.mapper..*(..))";
    private static final String CACHE = "execution (* iona.cache..*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {
        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("*", txAttr_REQUIRED);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public TransactionInterceptor txAdviceAll() {

        DefaultTransactionAttribute txAttr_REQUIRED = new DefaultTransactionAttribute();
        txAttr_REQUIRED.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("*Transaction", txAttr_REQUIRED);
        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean(value = "serviceAdvisor")
    public Advisor serviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(SERVICE);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

    @Bean(value = "controllerAdvisor")
    public Advisor controllerAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(CONTROLLER);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

    @Bean(value = "daoAdvisor")
    public Advisor daoAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(DAO);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

    @Bean(value = "mapperAdvisor")
    public Advisor mapperAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(MAPPER);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

    @Bean(value = "cacheAdvisor")
    public Advisor cacheAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(CACHE);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

    @Bean(value = "allAdvisor")
    public Advisor allAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(ALL);
        return new DefaultPointcutAdvisor(pointcut, txAdviceAll());
    }

}
