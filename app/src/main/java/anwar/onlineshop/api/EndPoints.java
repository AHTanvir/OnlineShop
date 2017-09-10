package anwar.onlineshop.api;

public class EndPoints {

    /**
     * Base server url.
     */
    private static final String API_URL                  = "http://websiteurl.com/";    // staging
    public static final String PRODUCTS_LIST                 = API_URL;
    public static final String RECOM                 = API_URL+ "recom";
    public static final String MEN                 = API_URL+"men/products";
    public static final String BEST_SELLERS                 = API_URL+ "best_sellers";
    public static final String WOMEN                 = API_URL+ "women/products";
    public static final String ORDERS                   = API_URL+ "order";
    public static final String MEN_PRODUCTS                   = API_URL+ "men/";
    public static final String WOMEN_PRODUCTS                   = API_URL+ "women/";
    // Notifications parameters
    public static final String NOTIFICATION_IMAGE_URL   = "image_url";

    private EndPoints() {}
}
