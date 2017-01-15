package monti.com.firebasePetagram.vistas;

import monti.com.firebasePetagram.adapter.UsuarioPerfil;
import monti.com.firebasePetagram.pojo.Mascota;
import monti.com.firebasePetagram.pojo.Usuario;

import java.util.ArrayList;

/**
 * Created by Susana on 19/11/2016.
 */

public interface IPerfilFragment {

    public void generarLinearLayoutVertical();

    public void generaGridLayout();

    public UsuarioPerfil crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(UsuarioPerfil adaptador);

    public void completarPerfil(Usuario usuario);
}
