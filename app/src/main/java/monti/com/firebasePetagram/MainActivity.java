package monti.com.firebasePetagram;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import monti.com.firebasePetagram.adapter.PageAdapter;
import monti.com.firebasePetagram.pojo.Usuario;
import monti.com.firebasePetagram.resApi.ConstantesResApi;
import monti.com.firebasePetagram.resApi.Endpoint;
import monti.com.firebasePetagram.resApi.EndpointApi;
import monti.com.firebasePetagram.resApi.adapter.RestApiAdapter;
import monti.com.firebasePetagram.resApi.model.FotoResponse;
import monti.com.firebasePetagram.resApi.model.LikeMedia;
import monti.com.firebasePetagram.resApi.model.UsuarioResponse;
import monti.com.firebasePetagram.vistas.PerfilFragment;
import monti.com.firebasePetagram.vistas.RecyclerViewFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String cuenta = "susana.chvz";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Mascotas");
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewpager();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setUpViewpager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.house);
        tabLayout.getTabAt(1).setIcon(R.drawable.face);
    }

    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());

        fragments.add(new PerfilFragment());

        return fragments;
    }

    public void irFavoritas() {
        Intent intent = new Intent(this, Favoritas.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.menu.menu, menu);
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        // or call onBackPressed()
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        //   Toast.makeText(ListaMascotas.this, Integer.toString(item.getItemId()), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.vStart:
                irFavoritas();
                break;
            case R.id.mpContacto:
                intent = new Intent(this, Contacto.class);
                startActivity(intent);
                break;
            case R.id.mpAcerca:
                intent = new Intent(this, Acerca.class);
                startActivity(intent);
                break;
            case R.id.mpConfigurar:
                intent = new Intent(this, ConfigurarCuenta.class);
                //             startActivityForResult(intent, 0);
                startActivity(intent);
                break;
            case R.id.mpNotificacion:
                enviarToken();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void enviarToken() {
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }

    public void enviarTokenRegistro(String token) {
        Log.d("TOKEN", token);
        if (cargarUsuario() != "") {
            cuenta = cargarUsuario();
        }
        obtenerIdInstagram(cuenta, token);
    }

    public String cargarUsuario() {

        String usuario = "";

        try {
            //FileInputStream fis =  openFileInput("usuario.txt");

            FileInputStream fis = getBaseContext().openFileInput("usuario.txt");
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

    public void obtenerIdInstagram(String cuenta, final String token) {
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
                    Toast.makeText(getBaseContext(), ".....ES NULL", Toast.LENGTH_SHORT).show();
                } else {
                    registrarDatos(fotoResponse.getUsuario().getId(), token);
                }
            }

            @Override
            public void onFailure(Call<FotoResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Algo paso con Instagram :(", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    public void registrarDatos(final String idInstagram, final String token){
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endpoint endpoint = restApiAdapter.establecerConexionResApi();
        Call<UsuarioResponse> usuarioResponseCall = endpoint.registrarID(idInstagram, token);
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
                Toast.makeText(getBaseContext(), "Algo paso con Firebase :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void toqueAnimal (View v){

    }








    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
