package com.xu.artemis.client.loader.impl;

import com.xu.artemis.client.loader.ConfigLoader;
import org.springframework.core.env.CompositePropertySource;

public class RemoteConfigLoader implements ConfigLoader {
    private static final String PROPERTY_SOURCE_NAME = "ApolloRemoteConfigProperties";

    @Override
    public CompositePropertySource loadPropertySource() {
        return null;
    }
}
