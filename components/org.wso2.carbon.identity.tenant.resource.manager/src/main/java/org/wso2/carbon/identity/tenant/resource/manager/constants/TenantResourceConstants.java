package org.wso2.carbon.identity.tenant.resource.manager.constants;

public class TenantResourceConstants {

    private TenantResourceConstants() {
    }

    public static final String PUBLISHER = "Publisher";

    public enum ErrorMessages {

        ERROR_CODE_INVALID_TENANT_DOMAIN_PASSWORD_RESET("TRM-10001", "User's tenant domain does "
                + "not match with the domain in the context"),
        ERROR_CODE_EVENT_PUBLISHER_ALREADY_EXSISTS("TRM-10002", "Event publisher with the same is already available "
                + "in the database.");

        private final String code;
        private final String message;

        ErrorMessages(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return code + " - " + message;
        }
    }
}
