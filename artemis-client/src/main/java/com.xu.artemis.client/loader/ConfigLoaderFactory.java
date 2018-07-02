package com.xu.artemis.client.loader;

import com.xu.artemis.client.loader.impl.MockConfigLoader;

public class ConfigLoaderFactory {
    private static ConfigLoaderFactory configLoaderFactory = new ConfigLoaderFactory();

    private ConfigLoaderFactory() {
    }

    public static ConfigLoaderFactory getInstance() {
        return configLoaderFactory;
    }

    public ConfigLoader getMockConfigLoader() {
        return new MockConfigLoader();
    }
}