package monti.com.firebasePetagram.adapter;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import monti.com.firebasePetagram.DetalleFoto;
import monti.com.firebasePetagram.R;
import monti.com.firebasePetagram.pojo.Mascota;

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
                Intent intent = new Intent(v.getContext(), DetalleFoto.class);
                intent.putExtra("url", mascota.getUrlFoto());
                intent.putExtra("like", mascota.getLikes());
                v.getContext().startActivity(intent);

            }
        });
    /*    holder.btnLike.setOnClickListener(new View.OnClickListener() {
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

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvLikesCV = (TextView) itemView.findViewById(R.id.tvTantoCV);
        }
    }
}