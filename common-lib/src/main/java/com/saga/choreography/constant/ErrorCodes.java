package com.saga.choreography.constant;

/**
 * ============================================================================
 * Shared error code constants
 * ============================================================================
 * <p>
 * Format: {SERVICE}_{CATEGORY}_{NUMBER}
 * Example: INV_INSF_001 (Inventory - Insufficient - 001)
 */
public final class ErrorCodes {

    private ErrorCodes() {
    }

    // ========================================================================
    // INVENTORY SERVICE ERROR CODES (INV_XXX)
    // ========================================================================
    public static final String INV_INSUFFICIENT_STOCK = "INV_INSF_001";
    public static final String INV_PRODUCT_NOT_FOUND = "INV_NFND_002";
    public static final String INV_RESERVATION_NOT_FOUND = "INV_NFND_003";
    public static final String INV_RESERVATION_EXPIRED = "INV_EXPR_004";
    public static final String INV_DB_ERROR = "INV_DB_005";

    // ========================================================================
    // PAYMENT SERVICE ERROR CODES (PAY_XXX)
    // ========================================================================
    public static final String PAY_INSUFFICIENT_FUNDS = "PAY_INSF_001";
    public static final String PAY_CARD_DECLINED = "PAY_DCLN_002";
    public static final String PAY_GATEWAY_TIMEOUT = "PAY_TIMO_003";
    public static final String PAY_REFUND_FAILED = "PAY_REF_004";
    public static final String PAYMENT_NOT_FOUND = "PAY_NFND_005";
    public static final String PAY_INVALID_STATUS = "PAY_INV_006";

    // ========================================================================
    // ORDER SERVICE ERROR CODES (ORD_XXX)
    // ========================================================================
    public static final String ORD_NOT_FOUND = "ORD_NFND_001";
    public static final String ORD_INVALID_STATUS = "ORD_INV_002";
    public static final String ORD_CANNOT_CANCEL = "ORD_CNCL_003";

    // ========================================================================
    // NOTIFICATION SERVICE ERROR CODES (NTF_XXX)
    // ========================================================================
    public static final String NTF_EMAIL_FAILED = "NTF_EML_001";
    public static final String NTF_SMS_FAILED = "NTF_SMS_002";
    public static final String NTF_TEMPLATE_NOT_FOUND = "NTF_TMPL_003";

    // ========================================================================
    // SYSTEM ERROR CODES (SYS_XXX)
    // ========================================================================
    public static final String SYS_DATABASE_ERROR = "SYS_DB_001";
    public static final String SYS_MESSAGE_BROKER_ERROR = "SYS_MQ_002";
    public static final String SYS_CACHE_ERROR = "SYS_CACHE_003";
    public static final String SYS_UNKNOWN_ERROR = "SYS_UNKNOWN_999";
}