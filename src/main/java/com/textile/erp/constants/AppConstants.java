package com.textile.erp.constants;

public final class AppConstants {

    private AppConstants() {}

    public static final String API_VERSION         = "/v1";
    public static final String AUTH_API            = API_VERSION + "/auth";
    public static final String TENANT_API          = API_VERSION + "/tenants";
    public static final String USER_API            = API_VERSION + "/users";
    public static final String ROLE_API            = API_VERSION + "/roles";
    public static final String SUPPLIER_API        = API_VERSION + "/suppliers";
    public static final String CUSTOMER_API        = API_VERSION + "/customers";
    public static final String CATEGORY_API        = API_VERSION + "/categories";
    public static final String UNIT_API            = API_VERSION + "/units";
    public static final String WAREHOUSE_API       = API_VERSION + "/warehouses";
    public static final String PRODUCT_API         = API_VERSION + "/products";
    public static final String INVENTORY_API       = API_VERSION + "/inventory";
    public static final String PURCHASE_API        = API_VERSION + "/purchases";

    public static final int DEFAULT_PAGE_NUMBER    = 0;
    public static final int DEFAULT_PAGE_SIZE      = 20;
    public static final int MAX_PAGE_SIZE          = 100;

    public static final String DEFAULT_SORT_BY     = "createdAt";
    public static final String DEFAULT_SORT_DIR    = "desc";

    public static final String DATE_FORMAT         = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT     = "yyyy-MM-dd HH:mm:ss";
}