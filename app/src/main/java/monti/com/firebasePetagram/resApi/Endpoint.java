package monti.com.firebasePetagram.resApi;

import monti.com.firebasePetagram.resApi.model.LikeMedia;
import monti.com.firebasePetagram.resApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Susana on 26/11/2016.
 */

public interface Endpoint {

    @FormUrlEncoded
    @POST(ConstantesResApi.KEY_POST_ID_REGISTRO)
    Call<UsuarioResponse> registrarID(@Field("id_usuario_instagram") String id_usuario_instagram,
                                      @Field("id_dispositivo") String id_dispositivo);

    //endpoint like a foto timeline

    @POST(ConstantesResApi.KEY_LIKE_FOTO)
    Call<LikeMedia> likeFoto(@Path("idFoto") String idFoto, @Query("access_token") String access_token);
/*
    @FormUrlEncoded
    @POST(ConstantesResApi.KEY_POST_HEROKU_MEDIA_LIKE)
    Call<String> darLike(@Field("id_dispositivo") String id_dispositivo,
                              @Field("id_foto_instagram") String id_foto_instagram,
                              @Field("id_usuario_instagram") String id_usuario_instagram);
                              */
}
