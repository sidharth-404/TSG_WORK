package com.employee.employeeApp.tenantConfig;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {
    private static String DEFAULT_TENANT="public";
    private static final ThreadLocal<String> currentTenant=new ThreadLocal<>();
    private static final ThreadLocal<String> TENANT_TYPE = new ThreadLocal<>();

    public static  void setCurrentTenant(String tenant,String tenantType){

        currentTenant.set(tenant);
        TENANT_TYPE.set(tenantType);
    }


    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static String getTenantType() {
        return TENANT_TYPE.get();
    }


    public static void clear(){
        currentTenant.remove();
        TENANT_TYPE.remove();
    }

    @Override
    public String resolveCurrentTenantIdentifier() {
        return currentTenant.get() != null ? currentTenant.get() : DEFAULT_TENANT;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
