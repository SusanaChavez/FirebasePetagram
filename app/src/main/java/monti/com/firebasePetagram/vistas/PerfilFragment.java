package monti.com.firebasePetagram.vistas;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import monti.com.firebasePetagram.R;
import monti.com.firebasePetagram.adapter.MascotaAdaptador;
import monti.com.firebasePetagram.adapter.UsuarioPerfil;
import monti.com.firebasePetagram.pojo.Mascota;
import monti.com.firebasePetagram.pojo.Usuario;
import monti.com.firebasePetagram.presentador.PerfilFragmentPresentador;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment implements IPerfilFragment {
    ImageView foto;
    TextView nombre;
    private RecyclerView listaFotos;
    PerfilFragmentPresentador perfilFragmentPresentador;
    Bundle bundle;
    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bundle = this.getArguments();
        // o......
//        TextView tvmesa = (TextView) container.getRootView().findViewById(R.id.tvmesa);
//        int vRoot = container.getRootView().getId();

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        foto = (ImageView) v.findViewById(R.id.fgfoto);
        nombre = (TextView) v.findViewById(R.id.fgNombre);


        listaFotos = (RecyclerView) v.findViewById(R.id.rvPerfil);

        //     Toast.makeText(Favoritas.this, "He creado todas las mascotas ", Toast.LENGTH_SHORT).show();
        //  Snackbar.make(nose, "Estooooy pasando........", Snackbar.LENGTH_SHORT);
        perfilFragmentPresentador = new PerfilFragmentPresentador(this, getContext());
        return v;
    }

    private void inicializaAdaptador() {

    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
    }

    @Override
    public void generaGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);

        listaFotos.setLayoutManager(glm);

    }

    @Override
    public UsuarioPerfil crearAdaptador(ArrayList<Mascota> mascotas) {
        UsuarioPerfil adaptador = new UsuarioPerfil(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(UsuarioPerfil adaptador) {
        listaFotos.setAdapter(adaptador);
    }

    @Override
    public void completarPerfil(Usuario usuario) {
        nombre.setText(usuario.getNombreCompleto());
        Picasso.with(getContext())
                .load(usuario.getUrlFotoPerfil())
                .placeholder(R.drawable.pata)
                .into(foto);
    }
}
