package entidades;

public class CompexSimples {
    private String competencia;
    private int experiencia;

    public CompexSimples(String competencia, int experiencia) {
        this.competencia = competencia;
        this.experiencia = experiencia;
    }

    public String getCompetencia() {
        return competencia;
    }

    public int getExperiencia() {
        return experiencia;
    }
}
