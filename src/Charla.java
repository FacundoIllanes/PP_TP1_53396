public class Charla extends Actividad {
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
        return "Charla (Conferencista: " + conferencista + ")";
    }
}