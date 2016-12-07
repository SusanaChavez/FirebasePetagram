package monti.com.firebasePetagram.bd;

import android.content.Context;

/**
 * Created by Susana on 15/10/2016.
 */

public class ConstructorMascotas {

    private static final int LIKE = 1;
    private Context context;
    public ConstructorMascotas(Context context) {
        this.context = context;
    }
/*==
    public ArrayList<Mascota> obtenerDatos(){


        BaseDatos db = new BaseDatos(context);
 //      insertarMascota(db);
        return db.obtenerLasMascotas();
    }

    public void insertarMascota(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "Garfield");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.gardfielito);
      //  contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_LIKE, 0);

        db.intertarMascota(contentValues);

        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "Miss");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.gatito);
    //    contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_LIKE, 0);

        db.intertarMascota(contentValues);

        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "Pluto");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.plutito);
   //     contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_LIKE, 0);

        db.intertarMascota(contentValues);

        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "Santa");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.santito);
    //    contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_LIKE, 0);

        db.intertarMascota(contentValues);

        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "Scooby");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.scoobyto);
      //  contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_LIKE, 0);

        db.intertarMascota(contentValues);

        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_NOMBRE, "Tom");
        contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_FOTO, R.drawable.tomito);
     //   contentValues.put(ConstanteBaseDatos.TABLE_MASCOTA_LIKE, 0);

        db.intertarMascota(contentValues);

    }

    public void darLike(Mascota mascota){
        BaseDatos bd = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstanteBaseDatos.TABLE_LIKE_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstanteBaseDatos.TABLE_LIKE_LIKE, LIKE);
        bd.insertarLike(contentValues);
    }

    public int obtenerLikes(Mascota mascota){
        BaseDatos bd = new BaseDatos(context);
        return bd.obtenerLike(mascota);
    }
==*/

}
