package editorial.web;

import java.io.Serializable;
import java.util.List;

public abstract class BuscadorWeb<T> implements Serializable {

    protected String keyword;   

    public abstract List<T> getColeccion(); 

    public abstract void accion(T item);    

    public abstract String getEtiqueta(T item); 

    public abstract String getIdentificador(T item);

    

    public abstract void buscar();


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
