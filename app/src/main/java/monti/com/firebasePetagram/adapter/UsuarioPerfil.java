package monti.com.firebasePetagram.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import monti.com.firebasePetagram.DetalleFoto;
import monti.com.firebasePetagram.R;
import monti.com.firebasePetagram.pojo.Mascota;
import monti.com.firebasePetagram.pojo.Usuario;
import monti.com.firebasePetagram.resApi.ConstantesResApi;
import monti.com.firebasePetagram.resApi.Endpoint;
import monti.com.firebasePetagram.resApi.adapter.RestApiAdapter;
import monti.com.firebasePetagram.resApi.model.LikeMedia;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Susana on 06/10/2016.
 */
public class UsuarioPerfil extends RecyclerView.Adapter<UsuarioPerfil.UsuarioViewHolder>   {

    ArrayList<Mascota> mascotas;
    Context activity;
    public UsuarioPerfil(ArrayList<Mascota> mascotas, Context activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public UsuarioPerfil.UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil, parent, false);
        int height = parent.getMeasuredHeight() / 8;
        v.setMinimumHeight(height);

        return new UsuarioViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(UsuarioPerfil.UsuarioViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.pata)
                .into(holder.imgFoto);
        holder.tvTantosCV.setText(String.valueOf(mascota.getLikes()));
        holder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetalleFoto.class);
                intent.putExtra("url", mascota.getUrlFoto());
                intent.putExtra("like", mascota.getLikes());
                v.getContext().startActivity(intent);
            }
        });

        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Te gustaaaa  " + mascota.getNombreCompleto(), Toast.LENGTH_SHORT).show();

                // Dar like ;)
                RestApiAdapter restApiAdapter = new RestApiAdapter(); //Realiza una conexion con Instagrm
                //Gson gsonFotoUsuario = restApiAdapter.construyeGsonDeserializadorFotoUsuario();
                Endpoint endpoint = restApiAdapter.establecerConexionRestApiInstagram();
                Log.d("LIKE_FOTO", mascota.getIdFoto());

                Call<LikeMedia> likeMediaCall = endpoint.likeFoto(mascota.getIdFoto(), ConstantesResApi.ACCESS_TOKEN);
                likeMediaCall.enqueue(new Callback<LikeMedia>() {
                    @Override
                    public void onResponse(Call<LikeMedia> call, Response<LikeMedia> response) {
                        Log.d("LIKE_FOTO", response.message());
                    }


                    @Override
                    public void onFailure(Call<LikeMedia> call, Throwable t) {
                        Toast.makeText(activity, "Algo paso :(", Toast.LENGTH_SHORT).show();
                        Log.e("FALLO LA CONEXION", t.toString());

                    }
                });
                // fin dar like

/*
                RestApiAdapter restApiAdapter = new RestApiAdapter();
                Endpoint endpoint = restApiAdapter.establecerConexionResApi();

                Call<UsuarioResponse> usuarioResponseCall = endpoint.likeFoto(mascota.getId());

                usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                    @Override
                    public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                        UsuarioResponse usuarioResponse1 = response.body();
                        Log.d("ID_FIREBASE", usuarioResponse1.getId());

                    }

                    @Override
                    public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                    }
                });
                */

            }


        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFoto;
        private TextView tvTantosCV;
        private ImageView btnLike;

        public UsuarioViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvTantosCV = (TextView) itemView.findViewById(R.id.tvTantoCV);
            btnLike = (ImageView) itemView.findViewById(R.id.btnLike);
        }
    }
}
