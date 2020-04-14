
package com.nexmosms.client.sms;

import com.nexmosms.client.HttpWrapper;
import com.nexmosms.client.sms.messages.Message;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicResponseHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;

import com.nexmosms.client.auth.TokenAuthMethod;

class SendMessageEndpoint {
    private static final String PATH = "/sms/json";
    private static final Log LOG = LogFactory.getLog(SendMessageEndpoint.class);
    protected final HttpWrapper httpWrapper;

    SendMessageEndpoint(HttpWrapper httpWrapper) {
        this.httpWrapper = httpWrapper;
    }

    public RequestBuilder makeRequest(Message message) throws UnsupportedEncodingException {
        System.out.println(httpWrapper.getHttpConfig().getRestBaseUri());
        RequestBuilder request = RequestBuilder.post(httpWrapper.getHttpConfig().getRestBaseUri() + PATH);
        message.addParams(request);
        return request;
    }

    public SmsSubmissionResponse parseResponse(HttpResponse response) throws IOException {
        return SmsSubmissionResponse.fromJson(new BasicResponseHandler().handleResponse(response));
    }

    public SmsSubmissionResponse execute(Message request) throws Exception {
        try {
            RequestBuilder requestBuilder = applyAuth(makeRequest(request));
            HttpUriRequest httpRequest = requestBuilder.build();

            if (httpRequest instanceof HttpEntityEnclosingRequest) {
                HttpEntityEnclosingRequest entityRequest = (HttpEntityEnclosingRequest) httpRequest;
                HttpEntity entity = entityRequest.getEntity();
                if (entity instanceof UrlEncodedFormEntity) {
                    entityRequest.setEntity(new UrlEncodedFormEntity(requestBuilder.getParameters(),
                            Charset.forName("UTF-8")
                    ));
                }
            }
            LOG.debug("Request: " + httpRequest);
            if (LOG.isDebugEnabled() && httpRequest instanceof HttpEntityEnclosingRequestBase) {
                HttpEntityEnclosingRequestBase enclosingRequest = (HttpEntityEnclosingRequestBase) httpRequest;
                LOG.debug(EntityUtils.toString(enclosingRequest.getEntity()));
            }
            HttpResponse response = this.httpWrapper.getHttpClient().execute(httpRequest);
            try{
                return parseResponse(response);
            } catch (IOException io){
                throw new Exception("Unable to parse response.", io);
            }
        } catch (UnsupportedEncodingException uee) {
            throw uee;
        } catch (IOException io) {
            throw io;
        }
    }

    protected RequestBuilder applyAuth(RequestBuilder request) {
        return httpWrapper.getAuthMethod().apply(request);
    }

    protected TokenAuthMethod getAuthMethod(Class[] acceptableAuthMethods) {
        return this.httpWrapper.getAuthMethod();
    }

    public void setHttpClient(HttpClient client) {
        this.httpWrapper.setHttpClient(client);
    }
}
