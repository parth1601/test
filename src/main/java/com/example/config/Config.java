package com.example.config;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "config")
public interface Config {

    Credentials credentials();

    interface Credentials {
        String host();
        String username();
        String password();
    }

}