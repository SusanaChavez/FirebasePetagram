package monti.com.firebasePetagram.resApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import monti.com.firebasePetagram.pojo.Usuario;
import monti.com.firebasePetagram.resApi.JsonKey;
import monti.com.firebasePetagram.resApi.model.FotoResponse;

import java.lang.reflect.Type;

/**
 * Created by Susana on 19/11/2016.
 */
public class FotoDeserializador implements JsonDeserializer<FotoResponse> {

    @Override
    public FotoResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        FotoResponse fotoResponse = gson.fromJson(json, FotoResponse.class);
        JsonArray fotoResponseData = json.getAsJsonObject().getAsJsonArray(JsonKey.MEDIA_RESPONSE_ARRAY);

        fotoResponse.setUsuario(deserializarFotoDeJson(fotoResponseData));
        return fotoResponse;
    }

    private Usuario deserializarFotoDeJson(JsonArray fotoResponseData){
    //    for (int i = 0; i < fotoResponseData.size(); i++){
        JsonObject fotoResponseDataObject = fotoResponseData.get(0).getAsJsonObject();

        String nombre = fotoResponseDataObject.get(JsonKey.USER_NAME).getAsString();
        String nombreCompleto = fotoResponseDataObject.get(JsonKey.USER_FULLNAME).getAsString();

        String urlFotoPerfil = fotoResponseDataObject.get(JsonKey.USER_FOTO_PERFIL).getAsString();

        String id = fotoResponseDataObject.get(JsonKey.USER_ID).getAsString();

   //     String urlFotoPerfil = "https://igcdn-photos-g-a.akamaihd.net/hphotos-ak-xfa1/t51.2885-19/s150x150/14596814_1151592028223046_1003529395157073920_a.jpg";
        Usuario usuarioActual = new Usuario(nombre, nombreCompleto, urlFotoPerfil, id);

   //     }
        return usuarioActual;
    }
}
