package monti.com.firebasePetagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import monti.com.firebasePetagram.R;
import monti.com.firebasePetagram.pojo.Usuario;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Susana on 06/10/2016.
 */
public class UsuarioPerfil extends RecyclerView.Adapter<UsuarioPerfil.UsuarioViewHolder>   {

    ArrayList<Usuario> usuarios;
    Context activity;
    public UsuarioPerfil(ArrayList<Usuario> usuarios, Context activity){
        this.usuarios = usuarios;
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
        final Usuario usuario = usuarios.get(position);
        Picasso.with(activity)
                .load(usuario.getUrlFotoPerfil())
                .placeholder(R.drawable.pata)
                .into(holder.imgFoto);
        holder.tvTantosCV.setText(String.valueOf(0));
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFoto;
        private TextView tvTantosCV;

        public UsuarioViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
            tvTantosCV = (TextView) itemView.findViewById(R.id.tvTantoCV);
        }
    }
}
