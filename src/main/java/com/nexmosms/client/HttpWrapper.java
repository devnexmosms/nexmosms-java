package com.nexmosms.client;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.nio.charset.Charset;

import com.nexmosms.client.auth.TokenAuthMethod;

public class HttpWrapper {
    private static final String CLIENT_NAME = "nexmosms-java";
    private static final String CLIENT_VERSION = "1.0.0";
    private static final String JAVA_VERSION = System.getProperty("java.version");

    private HttpClient httpClient = null;
    private HttpConfig httpConfig;
    private TokenAuthMethod authMethod;

    public HttpWrapper(HttpConfig httpConfig, TokenAuthMethod authMethod) {
        this.authMethod = authMethod;
        this.httpConfig = httpConfig;
    }

    public HttpClient getHttpClient() {
        if (this.httpClient == null) {
            this.httpClient = createHttpClient();
        }
        return this.httpClient;
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public TokenAuthMethod getAuthMethod() {
        return authMethod;
    }

    public void setAuthCollection(TokenAuthMethod authMethod) {
        this.authMethod = authMethod;
    }

    protected HttpClient createHttpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(200);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultConnectionConfig(ConnectionConfig
                .custom()
                .setCharset(Charset.forName("UTF-8"))
                .build());
        connectionManager.setDefaultSocketConfig(SocketConfig.custom().setTcpNoDelay(true).build());
        RequestConfig requestConfig = RequestConfig.custom().build();
        return HttpClientBuilder
                .create()
                .setConnectionManager(connectionManager)
                .setUserAgent(String.format("%s/%s java/%s", CLIENT_NAME, CLIENT_VERSION, JAVA_VERSION))
                .setDefaultRequestConfig(requestConfig)
                .useSystemProperties()
                .build();
    }

    public HttpConfig getHttpConfig() {
        return httpConfig;
    }
}
