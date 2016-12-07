package monti.com.firebasePetagram.resApi;

import monti.com.firebasePetagram.resApi.model.FotoResponse;
import monti.com.firebasePetagram.resApi.model.MascotaResponse;

import monti.com.firebasePetagram.resApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Susana on 17/11/2016.
 */

public interface EndpointApi {

    @GET(ConstantesResApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    @GET(ConstantesResApi.URL_GET_RECENT_MEDIA_AMIGOS)
    Call<MascotaResponse> getRecentMediaAmigos(@Path("usuario") String usuario);

//https://api.instagram.com/v1/users/search?q=miaucat123&access_token=3221225214.419fad8.dd48302ce4ef4756aec2943a9162562e
    @GET(ConstantesResApi.KEY_SEARCH_USER)
    Call<FotoResponse> getFotoUsuario(@Query("q") String cuenta, @Query("access_token") String access_token);

}
