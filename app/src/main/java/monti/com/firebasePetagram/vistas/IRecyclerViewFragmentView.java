package monti.com.firebasePetagram.vistas;

import monti.com.firebasePetagram.adapter.MascotaAdaptador;
import monti.com.firebasePetagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Susana on 15/10/2016.
 */

public interface IRecyclerViewFragmentView {

        public void generarLinearLayoutVertical();

        public void generaGridLayout();

        public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

        public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
