package com.xu.artemis.client.loader.impl;

import com.xu.artemis.client.loader.ConfigLoader;
import org.springframework.core.env.CompositePropertySource;

public class LocalConfigLoader implements ConfigLoader {
    private static final String PROPERTY_SOURCE_NAME = "ApolloLocalConfigProperties";

    @Override
    public CompositePropertySource loadPropertySource() {
        return null;
    }
}
