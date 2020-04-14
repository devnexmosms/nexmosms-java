
package com.nexmosms.client.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

public class TokenAuthMethod {
    private final int SORT_KEY = 30;

    private String apiKey;
    private String apiSecret;

    public TokenAuthMethod(String apiKey, String apiSecret) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public RequestBuilder apply(RequestBuilder request) {
        return request.addParameter("api_key", this.apiKey).addParameter("api_secret", this.apiSecret);
    }

    public RequestBuilder applyAsBasicAuth(RequestBuilder request) {
        String headerValue = Base64.encodeBase64String((this.apiKey + ":" + this.apiSecret).getBytes());
        Header authHeader = new BasicHeader("Authorization", "Basic " + headerValue);
        return request.addHeader(authHeader);
    }

    public RequestBuilder applyAsJsonProperties(RequestBuilder request)
            throws Exception {
        HttpEntity entity = request.getEntity();

        ObjectNode json = (ObjectNode) new ObjectMapper().readTree(EntityUtils.toString(entity));
        json.put("api_key", this.apiKey);
        json.put("api_secret", this.apiSecret);

        return request.setEntity(new StringEntity(json.toString(), ContentType.APPLICATION_JSON));
    }

    public int getSortKey() {
        return SORT_KEY;
    }
}
