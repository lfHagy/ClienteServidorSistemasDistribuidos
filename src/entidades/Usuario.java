package entidades;
//TODO

public abstract class Usuario {
    
    protected String email;
    protected String senha;
    protected String token;

    public Usuario(String email, String senha, String token) {
        boolean emailValido = validarEmail(email);
        boolean senhaValida = validarSenha(senha);
        if (emailValido && senhaValida) {
            this.email = email;
            this.senha = senha;
            this.token = token;
        }
    }

    public boolean validarEmail(String email) {
        if (email.length() < 7 || email.length() > 50) {
            return false;
        }
        if (!email.contains("@")) {
            return false;
        }
        return true;
    }

    public boolean validarSenha(String senha) {
        if (!senha.matches("^[0-9]*$")) {
            return false;
        }
        if (senha.length() < 3 || senha.length() > 8) {
            return false;
        }
        return true;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
