package monti.com.firebasePetagram.resApi;

import monti.com.firebasePetagram.resApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Susana on 26/11/2016.
 */

public interface Endpoint {
    @FormUrlEncoded
    @POST(ConstantesResApi.KEY_POST_ID_REGISTRO)
    Call<UsuarioResponse> registrarID(@Field("id_usuario_instagram") String id_usuario_instagram, @Field("id_dispositivo") String id_dispositivo);
}
