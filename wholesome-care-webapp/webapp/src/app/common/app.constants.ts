export class AppConstants {
   

    //    private static API_BASE_URL = "https://wholesome-care.stackroute.io/user-auth-service/";
    //    private static OAUTH2_URL = AppConstants.API_BASE_URL + "oauth2/authorization/";
    //    private static REDIRECT_URL = "?redirect_uri=https://wholesome-care.stackroute.io/login";
    //    public static API_URL = AppConstants.API_BASE_URL + "api/";
    //    public static AUTH_API = AppConstants.API_URL + "auth/";
    //    public static GOOGLE_AUTH_URL = AppConstants.OAUTH2_URL + "google" + AppConstants.REDIRECT_URL;


    private static API_BASE_URL = "https://wholesome-care.stackroute.io/user-auth-service/";
    private static OAUTH2_URL = AppConstants.API_BASE_URL + "oauth2/authorization/";
    private static REDIRECT_URL = "?redirect_uri=https://wholesome-care.stackroute.io/login";
    public static API_URL = AppConstants.API_BASE_URL + "api/";
    public static AUTH_API = AppConstants.API_URL + "v1/";
    public static GOOGLE_AUTH_URL = AppConstants.OAUTH2_URL + "google" + AppConstants.REDIRECT_URL;

    public static BMI_BASE_URL = "https://wholesome-care.stackroute.io/wellness-tracker-service/api/v1/bmi";
}
