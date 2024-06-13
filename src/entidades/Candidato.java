package entidades;

public class Candidato {

    private int cod;
    private String nome;
    private String email;
    private String senha;

    public Candidato(int cod, String nome, String email, String senha) {
        this.cod = cod;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
