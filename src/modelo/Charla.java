package modelo;

import java.io.Serializable;

public class Charla extends Actividad implements Serializable {
    private String conferencista;

    public Charla(int id, String titulo, int cupoMaximo, String conferencista) {
        super(id, titulo, cupoMaximo);
        this.conferencista = conferencista;
    }

    @Override
    public double calcularCostoMateriales() {
        return 0.0; // Las charlas son gratuitas
    }

    @Override
    public String getTipo() {
        return "modelo.Charla (Conferencista: " + conferencista + ")";
    }
}