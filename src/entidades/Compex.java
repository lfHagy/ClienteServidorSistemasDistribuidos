package entidades;

public class Compex {

    private int cod;
    private int codCandidato;
    private int codCompetencia;
    private int experiencia;
    private String titulo;

    public Compex(int cod, int codCandidato, int codCompetencia, int experiencia, String titulo) {
        this.cod = cod;
        this.codCandidato = codCandidato;
        this.codCompetencia = codCompetencia;
        this.experiencia = experiencia;
        this.titulo = titulo;
    }

    public int getCod() {
        return cod;
    }

    public int getCodCandidato() {
        return codCandidato;
    }

    public int getCodCompetencia() {
        return codCompetencia;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public String getTitulo() {
        return titulo;
    }
}
