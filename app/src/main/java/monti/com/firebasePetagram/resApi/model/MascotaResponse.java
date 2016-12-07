package monti.com.firebasePetagram.resApi.model;

import monti.com.firebasePetagram.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Susana on 17/11/2016.
 */

public class MascotaResponse {
    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
