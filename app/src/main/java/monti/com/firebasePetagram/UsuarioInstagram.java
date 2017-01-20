package monti.com.firebasePetagram;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import monti.com.firebasePetagram.resApi.ConstantesResApi;
import monti.com.firebasePetagram.resApi.Endpoint;
import monti.com.firebasePetagram.resApi.EndpointApi;
import monti.com.firebasePetagram.resApi.adapter.RestApiAdapter;
import monti.com.firebasePetagram.resApi.model.FotoResponse;
import monti.com.firebasePetagram.resApi.model.LikeMedia;
import monti.com.firebasePetagram.resApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Susana on 18/01/2017.
 */

public class UsuarioInstagram extends BroadcastReceiver {
    Intent inte;
    Context context;
    @Override
    public void onReceive(Context cont, Intent intent) {
        context = cont;
        String ACCION_KEY_PERFIL = "VER_PERFIL";
        String ACCION_KEY_SEGUIR = "SEGUIR";
        String ACCION_KEY_USUARIO = "VER_USUARIO";
        String accion = intent.getAction();
        if (accion.equals(ACCION_KEY_PERFIL)) {

            inte = new Intent(context, MainActivity.class);
            inte.putExtra("fragment", "perfil");
            inte.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(inte);
        }else if (accion.equals(ACCION_KEY_SEGUIR)) {
            seguir();
        }else {
            intent = new Intent(context, FotosRecientes.class);
            //intent.putExtra("string","Hooola");
            context.startActivity(intent);
        }
    }

    private void seguir() {
        String usuarioActivo = cargarUsuario();
        if (usuarioActivo.equals("")) {
            usuarioActivo="susana.chvz";
        }
        obtenerIdInstagram(usuarioActivo);

    }

    public String cargarUsuario() {

        String usuario = "";
        try {
            //FileInputStream fis =  openFileInput("usuario.txt");

            FileInputStream fis = context.openFileInput("usuario.txt");
            InputStreamReader isr = new InputStreamReader(fis);

            char[] inputBuffer = new char[100];

            int charRead;
            while ((charRead = isr.read(inputBuffer)) > 0) {
                // Convertimos los char a String
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                usuario += readString;

                inputBuffer = new char[100];
            }

            // Establecemos en el EditText el texto que hemos leido
            ///textBox.setText(s);

            isr.close();
        } catch (IOException ex) {
            usuario = "";
            ex.printStackTrace();
        }
        return usuario;
    }

    public void obtenerIdInstagram(String cuenta) {
        RestApiAdapter restApiAdapter = new RestApiAdapter(); //Realiza una conexion con Instagrm
        Gson gsonFotoUsuario = restApiAdapter.construyeGsonDeserializadorFotoUsuario();
        EndpointApi endpointApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFotoUsuario);

        Call<FotoResponse> fotoResponseCall = endpointApi.getFotoUsuario(cuenta, ConstantesResApi.ACCESS_TOKEN);

        //Para controlar el resultado de la respuesta
        fotoResponseCall.enqueue(new Callback<FotoResponse>() {
            @Override
            public void onResponse(Call<FotoResponse> call, Response<FotoResponse> response) {
                FotoResponse fotoResponse = response.body();
                if (fotoResponse == null) {
                    Toast.makeText(context, ".....ES NULL", Toast.LENGTH_SHORT).show();
                } else {
                    seguirUsuario(fotoResponse.getUsuario().getId(), ConstantesResApi.ACCESS_TOKEN);
                }
            }

            @Override
            public void onFailure(Call<FotoResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso con Instagram :(", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    public void seguirUsuario(final String idInstagram, final String token){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endpoint endpoint = restApiAdapter.establecerConexionResApi();

        Call<UsuarioResponse> usuarioResponseCall = endpoint.seguir(idInstagram);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>(){
            @Override
            public void onResponse
                    (Call < UsuarioResponse > call, Response < UsuarioResponse > response){
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_USUARIO_INSTAGRAM", usuarioResponse.getId_usuario_instagram());
                Log.d("ID_DISPOSITIVO", usuarioResponse.getId_dispositivo());
            }

            @Override
            public void onFailure (Call < UsuarioResponse > call, Throwable t){
                Toast.makeText(context, "Algo paso con Firebase :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
