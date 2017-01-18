package monti.com.firebasePetagram.resApi;

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
    //public static final String ROOT_URL_FIREBASE = "https://console.firebase.google.com/project/notificaciones-3d8fd/";
    public static final String ROOT_URL_FIREBASE = "https://infinite-garden-95686.herokuapp.com/";
    public static final String KEY_POST_ID_REGISTRO = "registrar-usuario/";

    //curl -F 'access_token=ACCESS-TOKEN'
    // https://api.instagram.com/v1/media/{media-id}/likes
    //https://api.instagram.com/v1/media/1384311435290763846_4140112305/likes?access_token=3221225214.419fad8.dd48302ce4ef4756aec2943a9162562e
    public static final String KEY_LIKE_FOTO = "https://api.instagram.com/v1/media/{idFoto}/likes";

//    public static final String KEY_POST_HEROKU_MEDIA_LIKE = "like-media/";

    public static final String KEY_DI_LIKE = "di-like/{id}/";

}
