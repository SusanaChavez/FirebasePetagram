package monti.com.firebasePetagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import monti.com.firebasePetagram.bd.ConstructorMascotas;
import monti.com.firebasePetagram.pojo.Mascota;
import monti.com.firebasePetagram.pojo.Usuario;
import monti.com.firebasePetagram.resApi.ConstantesResApi;
import monti.com.firebasePetagram.resApi.EndpointApi;
import monti.com.firebasePetagram.resApi.adapter.RestApiAdapter;
import monti.com.firebasePetagram.resApi.model.FotoResponse;
import monti.com.firebasePetagram.resApi.model.MascotaResponse;
import monti.com.firebasePetagram.vistas.IRecyclerViewFragmentView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Susana on 15/10/2016.
 */

public class RecyclerViewFragmentPresentador implements IRecyclerViewFragmentPresentador {
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructor;
    private ArrayList<Mascota> mascotas;
    private String cuenta = "susana.chvz";
    private Usuario usuario;
    RestApiAdapter restApiAdapter;
    Gson gsonConsulta;
    EndpointApi endpointApi;

    public RecyclerViewFragmentPresentador(IRecyclerViewFragmentView view, Context context) {
        this.iRecyclerViewFragmentView = view;
        this.context = context;

        if (cargarUsuario() != "")
        {
            cuenta = cargarUsuario();
        }

      //  obtenerMascotasBD();
        obtenerMedioRecientes();
    }

    @Override
    public void obtenerMascotasBD() {
        constructor = new ConstructorMascotas(context);
  //     mascotas = constructor.obtenerDatos();
        mostrarMascotasRV();
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


    @Override
    public void obtenerMedioRecientes() {
        restApiAdapter = new RestApiAdapter(); //Realiz una conexion con Instagrm
        gsonConsulta = restApiAdapter.construyeGsonDeserializadorFotoUsuario();
        endpointApi = restApiAdapter.establecerConexionRestApiInstagram(gsonConsulta);

        Call<FotoResponse> fotoResponseCall = endpointApi.getFotoUsuario(cuenta, ConstantesResApi.ACCESS_TOKEN);

        //Para controlar el resultado de la respuesta
        fotoResponseCall.enqueue(new Callback<FotoResponse>() {
            @Override
            public void onResponse(Call<FotoResponse> call, Response<FotoResponse> response) {
                FotoResponse fotoResponse = response.body();
                if (fotoResponse == null) {
                    Toast.makeText(context, ".....ES NULL", Toast.LENGTH_SHORT).show();
                } else {
                    usuario = fotoResponse.getUsuario();
                    buscarMediosRecientes(usuario);
                }
            }

            @Override
            public void onFailure(Call<FotoResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso :(", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    public void buscarMediosRecientes(Usuario usuario){
        gsonConsulta = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        endpointApi = restApiAdapter.establecerConexionRestApiInstagram(gsonConsulta);

        Call<MascotaResponse> mascotaResponseCall = endpointApi.getRecentMediaAmigos(usuario.getId());

        //Para controlar el resultado de la respuesta
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso :(", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generaGridLayout();
    }
}
