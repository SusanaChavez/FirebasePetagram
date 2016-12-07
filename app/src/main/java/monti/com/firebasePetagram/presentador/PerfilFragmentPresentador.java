package monti.com.firebasePetagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import monti.com.firebasePetagram.bd.ConstructorMascotas;
import monti.com.firebasePetagram.pojo.Usuario;
import monti.com.firebasePetagram.resApi.ConstantesResApi;
import monti.com.firebasePetagram.resApi.EndpointApi;
import monti.com.firebasePetagram.resApi.adapter.RestApiAdapter;
import monti.com.firebasePetagram.resApi.model.FotoResponse;
import monti.com.firebasePetagram.vistas.IPerfilFragment;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Susana on 19/11/2016.
 */

public class PerfilFragmentPresentador  implements IPerfilFragmentPresentador {
    private IPerfilFragment iPerfilFragment;
    private Context context;
    private Usuario usuario;
    private ConstructorMascotas constructor;
    private String cuenta = "susana.chvz";
    private ArrayList<Usuario> misUsuarios = new ArrayList<>();

    public PerfilFragmentPresentador(IPerfilFragment view, Context context) {
        this.iPerfilFragment = view;
        this.context = context;
        if (cargarUsuario() != "")
        {
            cuenta = cargarUsuario();
        }
        obtenerFotoUsuario();
    }

    @Override
    public void obtenerMascotasBD() {

    }

    @Override
    public void obtenerFotoUsuario() {
        RestApiAdapter restApiAdapter = new RestApiAdapter(); //Realiza una conexion con Instagrm
        Gson gsonFotoUsuario  = restApiAdapter.construyeGsonDeserializadorFotoUsuario();
        EndpointApi endpointApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFotoUsuario);

        Call<FotoResponse> fotoResponseCall = endpointApi.getFotoUsuario(cuenta, ConstantesResApi.ACCESS_TOKEN);

        //Para controlar el resultado de la respuesta
        fotoResponseCall.enqueue(new Callback<FotoResponse>() {
            @Override
            public void onResponse(Call<FotoResponse> call, Response<FotoResponse> response) {
                FotoResponse fotoResponse = response.body();
                if (fotoResponse == null) {
                     Toast.makeText(context, ".....ES NULL", Toast.LENGTH_SHORT).show();
                }else {
                    usuario = fotoResponse.getUsuario();
                    mostrarFotosRV();
                }
            }

            @Override
            public void onFailure(Call<FotoResponse> call, Throwable t) {
                    Toast.makeText(context, "Algo paso :(", Toast.LENGTH_SHORT).show();
                    Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void mostrarFotosRV() {
        for(int i=0 ; i < 12; i++){
            misUsuarios.add(usuario);
        }
        iPerfilFragment.inicializarAdaptadorRV(iPerfilFragment.crearAdaptador(misUsuarios));
        iPerfilFragment.generaGridLayout();
        iPerfilFragment.completarPerfil(usuario);
    }

    @Override
    public String cargarUsuario() {

        String usuario = "";

        try{
            //FileInputStream fis =  openFileInput("usuario.txt");

            FileInputStream fis = context.openFileInput("usuario.txt");
            InputStreamReader isr = new InputStreamReader(fis);

            char[] inputBuffer = new char[100];

            int charRead;
            while((charRead = isr.read(inputBuffer)) > 0){
                // Convertimos los char a String
                String readString = String.copyValueOf(inputBuffer, 0, charRead);
                usuario += readString;

                inputBuffer = new char[100];
            }

            // Establecemos en el EditText el texto que hemos leido
            ///textBox.setText(s);

            isr.close();
        }catch (IOException ex){
            usuario= "";
            ex.printStackTrace();
        }

        return usuario;
    }

 /*   public Usuario getUsuario(){
        return usuario;
    }
   */


}
