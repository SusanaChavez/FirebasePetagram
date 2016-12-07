package monti.com.firebasePetagram.resApi.model;

/**
 * Created by Susana on 05/12/2016.
 */
public class UsuarioResponse {
    private String id;
    private String id_usuario_instagram;
    private String id_dispositivo;

    public UsuarioResponse() {
    }

    public UsuarioResponse(String id, String id_usuario_instagram, String id_dispositivo) {
        this.id = id;
        this.id_dispositivo = id_dispositivo;
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }
}
