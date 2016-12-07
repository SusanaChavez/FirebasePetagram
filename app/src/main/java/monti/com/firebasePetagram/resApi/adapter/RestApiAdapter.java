package monti.com.firebasePetagram.resApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import monti.com.firebasePetagram.resApi.ConstantesResApi;
import monti.com.firebasePetagram.resApi.Endpoint;
import monti.com.firebasePetagram.resApi.EndpointApi;
import monti.com.firebasePetagram.resApi.deserializador.FotoDeserializador;
import monti.com.firebasePetagram.resApi.deserializador.MascotaDeserializador;
import monti.com.firebasePetagram.resApi.model.FotoResponse;
import monti.com.firebasePetagram.resApi.model.MascotaResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Susana on 17/11/2016.
 */

public class RestApiAdapter {
    public EndpointApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesResApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndpointApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorFotoUsuario(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FotoResponse.class, new FotoDeserializador());
        return gsonBuilder.create();
    }

    public Endpoint establecerConexionResApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesResApi.ROOT_URL_FIREBASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;
        return retrofit.create(Endpoint.class);
    }
}
