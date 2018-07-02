package com.xu.artemis.client;

import com.xu.artemis.client.loader.ConfigLoader;
import com.xu.artemis.client.loader.ConfigLoaderFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.MutablePropertySources;

public class ArtemisConfig implements BeanDefinitionRegistryPostProcessor, PriorityOrdered, ApplicationContextAware {
    public static final String APOLLO_PROPERTY_SOURCE_NAME = "ApolloConfigProperties";

    private ConfigLoader configLoader;

    private ConfigurableApplicationContext applicationContext;

    public ArtemisConfig() {
        this.configLoader = ConfigLoaderFactory.getInstance().getMockConfigLoader();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (!(applicationContext instanceof ConfigurableApplicationContext)) {
            throw new RuntimeException(
                    String.format("ApplicationContext must implement ConfigurableApplicationContext, but found: %s", applicationContext.getClass().getName()));
        }
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    /**
     * This is the first method invoked, so we could prepare the property sources here
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        preparePropertySource();
    }

    /**
     * This is executed after postProcessBeanDefinitionRegistry
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

    void preparePropertySource() {
        MutablePropertySources currentPropertySources = applicationContext.getEnvironment().getPropertySources();
        if (currentPropertySources.contains(APOLLO_PROPERTY_SOURCE_NAME)) {
            currentPropertySources.remove(APOLLO_PROPERTY_SOURCE_NAME);
        }

        CompositePropertySource composite = new CompositePropertySource(APOLLO_PROPERTY_SOURCE_NAME);
        composite.addPropertySource(configLoader.loadPropertySource());
        currentPropertySources.addFirst(composite);
    }
}
