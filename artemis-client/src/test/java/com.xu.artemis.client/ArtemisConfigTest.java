package com.xu.artemis.client;

import com.xu.artemis.client.loader.ConfigLoader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.test.util.ReflectionTestUtils;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArtemisConfigTest {

    private ArtemisConfig artemisConfig;
    @Mock
    private ConfigLoader configLoader;
    @Mock
    private ConfigurableApplicationContext applicationContext;
    @Mock
    private ConfigurableEnvironment env;
    @Mock
    private MutablePropertySources mutablePropertySources;

    @Before
    public void setUp() {
        artemisConfig = new ArtemisConfig();

        when(applicationContext.getEnvironment()).thenReturn(env);
        when(env.getPropertySources()).thenReturn(mutablePropertySources);

        artemisConfig.setApplicationContext(applicationContext);
        ReflectionTestUtils.setField(artemisConfig, "configLoader", configLoader);
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidApplicationContext() {
        ApplicationContext someInvalidApplication = mock(ApplicationContext.class);
        artemisConfig.setApplicationContext(someInvalidApplication);
    }

    @Test
    public void testPreparePropertySourceSuccessful() {
        CompositePropertySource somePropertySource = mock(CompositePropertySource.class);
        final ArgumentCaptor<CompositePropertySource> captor = ArgumentCaptor.forClass(CompositePropertySource.class);

        when(configLoader.loadPropertySource()).thenReturn(somePropertySource);

        artemisConfig.preparePropertySource();

        verify(configLoader, times(1)).loadPropertySource();
        verify(mutablePropertySources, times(1)).addFirst(captor.capture());

        final CompositePropertySource insertedPropertySource = captor.getValue();

        assertEquals(artemisConfig.APOLLO_PROPERTY_SOURCE_NAME, insertedPropertySource.getName());
        assertTrue(insertedPropertySource.getPropertySources().contains(somePropertySource));
    }
}
