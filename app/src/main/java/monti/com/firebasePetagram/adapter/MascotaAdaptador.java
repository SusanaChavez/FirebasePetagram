package monti.com.firebasePetagram.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import monti.com.firebasePetagram.DetalleFoto;
import monti.com.firebasePetagram.R;
import monti.com.firebasePetagram.pojo.Mascota;
import monti.com.firebasePetagram.resApi.Endpoint;
import monti.com.firebasePetagram.resApi.EndpointApi;
import monti.com.firebasePetagram.resApi.adapter.RestApiAdapter;
import monti.com.firebasePetagram.resApi.model.LikeMedia;
import monti.com.firebasePetagram.resApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>  {

    ArrayList<Mascota> mascotas = new ArrayList<Mascota>();
    Activity activity;
    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }
    @Override
    public MascotaAdaptador.MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v) ;
    }

    @Override
    public void onBindViewHolder(final MascotaAdaptador.MascotaViewHolder holder, int position) {
        final Mascota mascota = mascotas.get(position);
        // holder.imgFoto.setImageResource(mascota.getUrlFoto());
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.pata)
                .into(holder.imgFoto);
        holder.tvLikesCV.setText(String.valueOf(mascota.getLikes()));
        holder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DetalleFoto.class);
                intent.putExtra("url", mascota.getUrlFoto());
                intent.putExtra("like", mascota.getLikes());

                // Esto es porque las transiociones existen desde la version LOLLIPOP
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Explode explode = new Explode();
                    explode.setDuration(1000);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(activity, v, activity.getString(R.string.transicion_foto)).toBundle());

                }else {
                    activity.startActivity(intent);
                }
            }
        });

        /* PARA BASE DE DATOS
        holder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(v.getContext(), "Te gustaaaa  "+mascota.getNombre(), Toast.LENGTH_SHORT).show();

               ConstructorMascotas constructorMascotas = new ConstructorMascotas(v.getContext());
               constructorMascotas.darLike(mascota);

               holder.tvLikesCV.setText(String.valueOf(constructorMascotas.obtenerLikes(mascota)));
               holder.tvLikesCV.invalidate();
            }
        });

        */

    }


    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFoto;
        private TextView tvLikesCV;
        private ImageButton btnLike;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvLikesCV = (TextView) itemView.findViewById(R.id.tvTantoCV);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);
        }
    }
}