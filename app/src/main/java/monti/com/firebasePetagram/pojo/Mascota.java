package monti.com.firebasePetagram.pojo;

public class Mascota {
    private String id;  //Porque tomo el ID que obtengo de hacer GET
    private String nombreCompleto;
    private String urlFoto;
    private String idFoto;
    private int likes = 0;

    public Mascota(String nombreCompleto, String urlFoto, String idFoto, int likes) {
        this.nombreCompleto = nombreCompleto;
        this.urlFoto = urlFoto;
        this.idFoto = idFoto;
        this.likes = likes;
    }

    public Mascota() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getIdFoto() { return idFoto;}

    public void setIdFoto(String idFoto) { this.idFoto = idFoto;}

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
