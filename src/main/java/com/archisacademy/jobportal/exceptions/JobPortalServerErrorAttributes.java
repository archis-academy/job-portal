package com.archisacademy.jobportal.exceptions;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JobPortalServerErrorAttributes extends DefaultErrorAttributes {

    public JobPortalServerErrorAttributes(){
        super();
    }

    @Override
    public Map<String,Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options){
        final Map<String, Object> defaultAttributes = super.getErrorAttributes(webRequest,options);

        Map<String,Object> errorAttributes = new HashMap<>();
        errorAttributes.put("status",defaultAttributes.get("status"));
        errorAttributes.put("timestamp",defaultAttributes.get("timestamp"));
        Object message = defaultAttributes.get("message");
        message = "No message available".equals(message)? defaultAttributes.get("message"):message;
        errorAttributes.put("message",message);
        return errorAttributes;
    }
}
