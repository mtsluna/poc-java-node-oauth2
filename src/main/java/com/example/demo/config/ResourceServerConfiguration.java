package com.example.demo.config;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.StreamUtils;
import sun.security.tools.keytool.Resources;

import java.io.*;
import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String ROOT_PATTERN = "/**";

    private TokenStore tokenStore;

    public ResourceServerConfiguration() {

    }

    @Override
    public void configure(final ResourceServerSecurityConfigurer resources) throws IOException {
        resources.tokenStore(tokenStore());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, ROOT_PATTERN).access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.POST, ROOT_PATTERN).access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PATCH, ROOT_PATTERN).access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.PUT, ROOT_PATTERN).access("#oauth2.hasScope('write')")
                .antMatchers(HttpMethod.DELETE, ROOT_PATTERN).access("#oauth2.hasScope('write')");
    }

    @Bean
    public DefaultTokenServices tokenServices(final TokenStore tokenStore) {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore);
        return tokenServices;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() throws IOException {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setVerifierKey(getPublicKeyAsString());
        return converter;
    }

    @Bean
    public TokenStore tokenStore() throws IOException {
        if (tokenStore == null) {
            tokenStore = new JwtTokenStore(jwtAccessTokenConverter());
        }
        return tokenStore;
    }

    private String getPublicKeyAsString() {
        try {
            Resource resource = new ClassPathResource("public.txt");
            return StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}