package org.apache.catalina.servlet;

import org.apache.catalina.http.HttpRequest;
import org.apache.catalina.http.HttpResponse;
import org.apache.catalina.http.header.HttpHeader;

public abstract class RestController extends Controller {

    private static final String BASIC_RESPONSE_BODY = "Hello world!";

    public boolean service(HttpRequest request, HttpResponse response) {
        if (request.isTargetBlank()) {
            response.addHeader(HttpHeader.CONTENT_TYPE, "text/html");
            response.setBody(BASIC_RESPONSE_BODY);
            return true;
        }

        return switch (request.getHttpMethod()) {
            case GET -> doGet(request, response);
            case POST -> doPost(request, response);
        };
    }

    protected boolean doGet(HttpRequest request, HttpResponse response) {
        return responseResource(response, request.getTargetPath());
    }

    protected abstract boolean doPost(HttpRequest request, HttpResponse response);
}