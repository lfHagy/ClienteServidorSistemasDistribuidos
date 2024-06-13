package entidades;

public class Empresa {

    private int cod;
    private String razaoSocial;
    private String ramo;
    private String descricao;
    private String email;
    private String senha;
    private String cnpj;

    public Empresa(int cod, String razaoSocial, String email, String cnpj, String senha, String descricao, String ramo) {
        this.cod = cod;
        this.razaoSocial = razaoSocial;
        this.email = email;
        this.cnpj = cnpj;
        this.senha = senha;
        this.descricao = descricao;
        this.ramo = ramo;
    }

    public int getCod() {
        return cod;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public void showAll() {
        System.out.println(cod);
        System.out.println(razaoSocial);
        System.out.println(email);
        System.out.println(cnpj);
        System.out.println(senha);System.out.println(descricao);
        System.out.println(ramo);
    }
}
