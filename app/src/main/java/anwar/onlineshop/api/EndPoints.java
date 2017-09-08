package anwar.onlineshop.api;

public class EndPoints {

    /**
     * Base server url.
     */
    private static final String API_URL                  = "http://websiteurl";    // staging
    public static final String PRODUCTS                 = API_URL.concat("/drawable/products");
    public static final String RECOM                 = API_URL.concat("/recom");
    public static final String MEN                 = API_URL.concat("/men");
    public static final String BEST_SELLERS                 = API_URL.concat("/bestsellers");
    public static final String WOMEN                 = API_URL.concat("/WOMEN");
    public static final String CATEGORY_MAN                 = API_URL.concat("/categoryman");
    public static final String CART                     = API_URL.concat("/cart");
    public static final String DISCOUNTS           = API_URL.concat("/discounts");
    public static final String ORDERS                   = API_URL.concat("/orders");
    // Notifications parameters
    public static final String NOTIFICATION_IMAGE_URL   = "image_url";

    private EndPoints() {}
}
