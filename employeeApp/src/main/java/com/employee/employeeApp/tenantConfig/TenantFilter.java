package com.employee.employeeApp.tenantConfig;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TenantFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest=(HttpServletRequest) servletRequest;
        String tenant= httpRequest.getHeader("X-Tenant-ID");
        String tenantType= httpRequest.getHeader("X-Tenant-Type");
        if (tenant != null && tenantType!=null) {
            TenantIdentifierResolver.setCurrentTenant(tenant,tenantType);
        }
        else{
            TenantIdentifierResolver.setCurrentTenant("public","shared");
        }

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            TenantIdentifierResolver.clear(); // Clear the tenant after the request
        }
    }

}
