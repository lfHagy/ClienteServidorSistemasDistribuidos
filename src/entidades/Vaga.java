package entidades;

public class Vaga {

    private int cod;
    private String nome;
    private double faixaSalarial;
    private String[] competencias;
    private String descricao;
    private String estado;
    private String email;

    public Vaga(int cod, String nome, double faixaSalarial,
            String[] competencias, String descricao,
            String estado, String email) {
        this.cod = cod;
        this.nome = nome;
        this.faixaSalarial = faixaSalarial;
        this.competencias = competencias;
        this.descricao = descricao;
        this.estado = estado;
        this.email = email;
    }

    public int getCod() {
        return cod;
    }
    
    public String getNome() {
        return nome;
    }

    public double getFaixaSalarial() {
        return faixaSalarial;
    }

    public String[] getCompetencias() {
        return competencias;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEstado() {
        return estado;
    }
    
    public String getEmail() {
        return email;
    }

}
