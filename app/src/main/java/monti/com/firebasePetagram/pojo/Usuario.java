package monti.com.firebasePetagram.pojo;

/**
 * Created by Susana on 19/11/2016.
 */

public class Usuario {
    private String usuario;
    private String nombreCompleto;
    private String urlFotoPerfil;
    private String id;

    public Usuario () {

    }
    public Usuario(String usuario, String nombreCompleto, String urlFotoPerfil, String id) {
        this.usuario = usuario;
        this.nombreCompleto = nombreCompleto;
        this.urlFotoPerfil = urlFotoPerfil;
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUrlFotoPerfil() {
        return urlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
