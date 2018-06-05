package ru.bellintegrator.weatherdatabase;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.atomikos.jms.AtomikosConnectionFactoryBean;
import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.transaction.jta.JtaTransactionManager;
import javax.jms.ConnectionFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

@Configuration
@EnableJms
public class ReceiverConfig {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;


    @Bean
    public UserTransaction userTransaction() throws Throwable {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(1000);
        return userTransactionImp;
    }

    @Bean(initMethod = "init", destroyMethod = "close")
    public TransactionManager transactionManager() throws Throwable {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);

        return userTransactionManager;
    }

    @Bean
    public JtaTransactionManager jtaTransactionManager() throws Throwable {
        JtaTransactionManager transactionManager = new JtaTransactionManager();
        transactionManager.setTransactionManager(transactionManager());
        transactionManager.setUserTransaction(userTransaction());
        return transactionManager;
    }

    @Bean
    public DataSource dataSource() {
        PGXADataSource pgXaDataSource = new PGXADataSource();
        pgXaDataSource.setUrl(datasourceUrl);
        pgXaDataSource.setUser(username);
        pgXaDataSource.setPassword(password);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(pgXaDataSource);
        xaDataSource.setUniqueResourceName("PostgresXADS");
        xaDataSource.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");

        return xaDataSource;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQXAConnectionFactory activeMQXAConnectionFactory = new ActiveMQXAConnectionFactory();
        activeMQXAConnectionFactory.setBrokerURL(brokerUrl);

        AtomikosConnectionFactoryBean atomikosConnectionFactoryBean = new AtomikosConnectionFactoryBean();
        atomikosConnectionFactoryBean.setUniqueResourceName("ActiveMQXADS");
        atomikosConnectionFactoryBean.setLocalTransactionMode(false);
        atomikosConnectionFactoryBean.setXaConnectionFactory(activeMQXAConnectionFactory);
        return atomikosConnectionFactoryBean;
    }


    @Bean // Сериализация сообщений в json
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean // для принятия сообщений
    public JmsListenerContainerFactory<?> jmsFactory(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) throws Throwable {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        factory.setConcurrency("1-1");
        factory.setSessionTransacted(true);
        factory.setPubSubDomain(true);
        factory.setTransactionManager(jtaTransactionManager());

        factory.setMessageConverter(jacksonJmsMessageConverter());
        configurer.configure(factory, connectionFactory);

        return factory;
    }
}