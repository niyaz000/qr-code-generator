package com.github.niyaz000.qrcodegen.filter;

import com.github.niyaz000.qrcodegen.constant.ApplicationConstants;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Order(1)
@Component
public class MDCFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    try {
      HttpServletRequest req = (HttpServletRequest) request;
      addXRequestId(req);
      MDC.put(ApplicationConstants.METHOD, req.getMethod());
      MDC.put(ApplicationConstants.PATH, req.getRequestURI());
      MDC.put(ApplicationConstants.QUERY, req.getQueryString());
      chain.doFilter(request, response);
    } finally {
      MDC.clear();
    }
  }

  private void addXRequestId(HttpServletRequest request) {
    String xRequestId = request.getHeader(ApplicationConstants.X_REQUEST_ID);
    if (xRequestId == null) {
      MDC.put(ApplicationConstants.X_REQUEST_ID, UUID.randomUUID().toString());
    } else {
      MDC.put(ApplicationConstants.X_REQUEST_ID, xRequestId);
    }
  }

}
