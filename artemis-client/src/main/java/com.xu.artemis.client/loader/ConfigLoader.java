package com.xu.artemis.client.loader;

import org.springframework.core.env.CompositePropertySource;

public interface ConfigLoader {
    /**
     * Load property source for client use
     * @return property source
     */
    CompositePropertySource loadPropertySource();
}
