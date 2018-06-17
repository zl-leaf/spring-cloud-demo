package com.yipzale.springclouddemo.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        String accessToken = request.getParameter("access_token");
        if (null == accessToken || accessToken.isEmpty()) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            return null;
        }

        return null;
    }
}
