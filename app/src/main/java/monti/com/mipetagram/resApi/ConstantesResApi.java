package monti.com.mipetagram.resApi;

/**
 * Created by Susana on 17/11/2016.
 */

public final class ConstantesResApi {
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com"+ VERSION;
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String ACCESS_TOKEN = "3221225214.419fad8.dd48302ce4ef4756aec2943a9162562e";
    public static final String TAG_ACCESS_TOKEN = KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + TAG_ACCESS_TOKEN;

    public static final String KEY_GET_RECENT_MEDIA_AMIGOS = "users/{usuario}/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_AMIGOS = KEY_GET_RECENT_MEDIA_AMIGOS + TAG_ACCESS_TOKEN;

    //https://api.instagram.com/v1/users/search?q=miaucat123&access_token=3221225214.419fad8.dd48302ce4ef4756aec2943a9162562e
    public static final String KEY_SEARCH_USER = "users/search";
  //  public static final String KEY_ACCESS      = "&access_token=";
   // public static final String URL_GET_FOTO_USUARIO = KEY_SEARCH_USER + KEY_ACCESS + ACCESS_TOKEN;

    //https://api.instagram.com/v1/users/self/followed-by?access_token=3221225214.419fad8.dd48302ce4ef4756aec2943a9162562e
    //https://api.instagram.com/v1/users/self/follows?
//Para obtener los usuarios que me siguen.....

}
