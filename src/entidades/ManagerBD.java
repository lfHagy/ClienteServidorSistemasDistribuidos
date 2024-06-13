package entidades;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ManagerBD {

    private static final String IP_BD = "127.0.0.1";
    private static final String NOME_BD = "sistemasdistribuidos";
    private static final String URL = String.format("jdbc:mysql://%s:3306/%s", IP_BD, NOME_BD);
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USUARIO_BD = "root";
    private static final String SENHA_BD = "";
    private Connection conn;

    public ManagerBD() throws Exception { //construtor
        this.conn = connectBD(); //conecta ao instanciar
    }

    public static Connection connectBD() throws Exception {
        Class.forName(DRIVER);
        Connection conexao = DriverManager.getConnection(URL, USUARIO_BD, SENHA_BD);
        return conexao;
    }

    //candidato -----
    public void cadastrarCandidato(String nome, String email, String senha) throws SQLException {
        String query = "INSERT INTO candidato (nome, email, senha) VALUES (?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, nome);
        statement.setString(2, email);
        statement.setString(3, senha);
        statement.executeUpdate();
    }

    public Candidato loginCandidato(String email, String senha) throws SQLException {
        String query = "SELECT * FROM candidato WHERE email =? AND senha =?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, senha);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return new Candidato(result.getInt("cod"), result.getString("nome"), result.getString("email"), result.getString("senha"));
        }
        return null;
    }

    public void atualizarCandidato(String nome, String email, String senha) throws SQLException {
        String query = "UPDATE candidato SET nome =?, senha =? WHERE email =?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, nome);
        statement.setString(2, senha);
        statement.setString(3, email);
        statement.executeUpdate();
    }

    public void apagarCandidato(String email) throws SQLException {
        String query = "DELETE FROM candidato WHERE email =?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, email);
        statement.executeUpdate();
    }

    public Candidato visualizarCandidato(String email) throws SQLException {
        String query = "SELECT * FROM candidato WHERE email = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, email);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int cod = resultSet.getInt("cod");
            String nome = resultSet.getString("nome");
            String senha = resultSet.getString("senha");
            return new Candidato(cod, nome, email, senha);
        } else {
            return null;
        }
    }

    public Candidato[] getAllCandidatos() throws SQLException {
        String query = "SELECT * FROM candidato";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        List<Candidato> candidatos = new ArrayList<>();
        while (result.next()) {
            int cod = result.getInt("cod");
            String nome = result.getString("nome");
            String email = result.getString("email");
            String senha = result.getString("senha");
            candidatos.add(new Candidato(cod, nome, email, senha));
        }
        return candidatos.isEmpty() ? null : candidatos.toArray(Candidato[]::new);
    }

    //empresa -----
    public void cadastrarEmpresa(String razaoSocial, String email, String cnpj, String senha, String descricao, String ramo) throws SQLException {
        String query = "INSERT INTO empresa (razaoSocial, email, cnpj, senha, descricao, ramo) VALUES (?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, razaoSocial);
        statement.setString(2, email);
        statement.setString(3, cnpj);
        statement.setString(4, senha);
        statement.setString(5, descricao);
        statement.setString(6, ramo);
        statement.executeUpdate();
    }

    public Empresa loginEmpresa(String email, String senha) throws SQLException {
        String query = "SELECT * FROM empresa WHERE email =? AND senha =?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, senha);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return new Empresa(result.getInt("cod"), result.getString("razaoSocial"), result.getString("email"), result.getString("cnpj"), result.getString("senha"), result.getString("descricao"), result.getString("ramo"));
        }
        return null;
    }

    public void atualizarEmpresa(String razaoSocial, String email, String cnpj, String senha, String descricao, String ramo) throws SQLException {
        String query = "UPDATE empresa SET razaoSocial =?, cnpj =?, senha =?, descricao =?, ramo =? WHERE email =?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, razaoSocial);
        statement.setString(2, cnpj);
        statement.setString(3, senha);
        statement.setString(4, descricao);
        statement.setString(5, ramo);
        statement.setString(6, email);
        statement.executeUpdate();
    }

    public void apagarEmpresa(String email) throws SQLException {
        String query = "DELETE FROM empresa WHERE email =?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, email);
        statement.executeUpdate();
    }

    public Empresa visualizarEmpresa(String email) throws SQLException {
        String query = "SELECT * FROM empresa WHERE email =?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, email);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return new Empresa(result.getInt("cod"), result.getString("razaoSocial"), result.getString("email"), result.getString("cnpj"), result.getString("senha"), result.getString("descricao"), result.getString("ramo"));
        }
        return null;
    }

    public Empresa[] getAllEmpresas() throws SQLException {
        String query = "SELECT * FROM empresa";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        List<Empresa> empresas = new ArrayList<>();
        while (result.next()) {
            int cod = result.getInt("cod");
            String razaoSocial = result.getString("razaoSocial");
            String email = result.getString("email");
            String cnpj = result.getString("cnpj");
            String senha = result.getString("senha");
            String descricao = result.getString("descricao");
            String ramo = result.getString("ramo");
            empresas.add(new Empresa(cod, razaoSocial, email, cnpj, senha, descricao, ramo));
        }
        return empresas.isEmpty() ? null : empresas.toArray(new Empresa[0]);
    }

    //token -----
    public String gerarToken(String email) throws SQLException {
        String token = null;
        while (token == null || token.length() != 36) {
            token = UUID.randomUUID().toString();
        }
        PreparedStatement statement = conn.prepareStatement("INSERT INTO token (token, email) VALUES (?, ?)");
        statement.setString(1, token);
        statement.setString(2, email);
        statement.executeUpdate();
        return token;
    }

    public String getToken(String email) throws SQLException {
        String query = "SELECT token FROM token WHERE email = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, email);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return result.getString("token");
        }
        return null;
    }

    public void apagarToken(String email) throws SQLException {
        String query = "UPDATE token SET token = NULL WHERE email = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, email);
        statement.executeUpdate();
    }

    //competencias -----
    public Competencia cadastrarCompetencia(String titulo) throws SQLException {
        String query = "SELECT * FROM competencia WHERE titulo =?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, titulo);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return null;
        }
        query = "INSERT INTO competencia (titulo) VALUES (?)";
        statement = conn.prepareStatement(query);
        statement.setString(1, titulo);
        statement.executeUpdate();
        query = "SELECT * FROM competencia WHERE titulo =?";
        statement = conn.prepareStatement(query);
        statement.setString(1, titulo);
        result = statement.executeQuery();
        if (result.next()) {
            int cod = result.getInt("cod");
            return new Competencia(cod, titulo);
        }
        return null;
    }

    public Competencia[] getAllCompetencias() throws SQLException {
        String query = "SELECT * FROM competencia";
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        List<Competencia> competencias = new ArrayList<>();
        while (result.next()) {
            int cod = result.getInt("cod");
            String titulo = result.getString("titulo");
            competencias.add(new Competencia(cod, titulo));
        }
        return competencias.isEmpty() ? null : competencias.toArray(new Competencia[0]);
    }

    //compex -----
    public void cadastrarCompex(String email, String titulo, int experiencia) throws SQLException {
        String query = "SELECT * FROM compex WHERE codCandidato IN (SELECT cod FROM candidato WHERE email =?) AND codCompetencia IN (SELECT cod FROM competencia WHERE titulo =?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, titulo);
        ResultSet result = statement.executeQuery();

        if (result.next()) { //jah existe
            System.out.println("Email " + email + " jah tem competencia " + titulo + " registrada! Essa competencia nao sera inserida.");
            return;
        }

        // Create a new complex entry
        query = "SELECT cod FROM candidato WHERE email =?"; //achando id do candidato
        statement = conn.prepareStatement(query);
        statement.setString(1, email);
        result = statement.executeQuery();
        int codCandidato = 0;
        if (result.next()) {
            codCandidato = result.getInt("cod");
        } else {
            throw new SQLException("Candidato nao existe.");
        }
        query = "SELECT cod FROM competencia WHERE titulo =?"; //achando id da comp
        statement = conn.prepareStatement(query);
        statement.setString(1, titulo);
        result = statement.executeQuery();
        int codCompetencia = 0;
        if (result.next()) {
            codCompetencia = result.getInt("cod");
        } else {
            throw new SQLException("Competencia nao existe.");
        }
        query = "INSERT INTO compex (codCandidato, codCompetencia, experiencia) VALUES (?,?,?)";
        statement = conn.prepareStatement(query);
        statement.setInt(1, codCandidato);
        statement.setInt(2, codCompetencia);
        statement.setInt(3, experiencia);
        statement.executeUpdate();
    }

    public void atualizarCompex(String email, CompexSimples compex) throws SQLException {
        String query = "SELECT cod FROM competencia WHERE titulo =?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, compex.getCompetencia());
        ResultSet result = statement.executeQuery();
        int codCompetencia = 0;
        if (result.next()) {
            codCompetencia = result.getInt("cod");
        } else {
            cadastrarCompetencia(compex.getCompetencia());
            query = "SELECT cod FROM competencia WHERE titulo =?";
            statement = conn.prepareStatement(query);
            statement.setString(1, compex.getCompetencia());
            result = statement.executeQuery();
            if (result.next()) {
                codCompetencia = result.getInt("cod");
            } else {
                throw new SQLException("Erro ao criar competencia.");
            }
        }

        query = "UPDATE compex SET experiencia =? WHERE codCandidato IN (SELECT cod FROM candidato WHERE email =?) AND codCompetencia =?";
        statement = conn.prepareStatement(query);
        statement.setInt(1, compex.getExperiencia());
        statement.setString(2, email);
        statement.setInt(3, codCompetencia);
        int rowsUpdated = statement.executeUpdate();

        if (rowsUpdated == 0) {
            //nao existe? cadastrar
            cadastrarCompex(email, compex.getCompetencia(), compex.getExperiencia());
        }
    }

    public Compex[] listarCompex(String email) throws SQLException {
        String query = "SELECT cod FROM candidato WHERE email =?"; //achar cod por email
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, email);
        ResultSet result = statement.executeQuery();
        int codCandidato = 0;
        if (result.next()) {
            codCandidato = result.getInt("cod");
        } else {
            throw new SQLException("Candidato not found");
        }
        query = "SELECT * FROM compex WHERE codCandidato =?"; //achar compexes
        statement = conn.prepareStatement(query);
        statement.setInt(1, codCandidato);
        result = statement.executeQuery();
        List<Compex> compexList = new ArrayList<>();
        while (result.next()) {
            int cod = result.getInt("cod");
            int codCompetencia = result.getInt("codCompetencia");
            int experiencia = result.getInt("experiencia");

            query = "SELECT titulo FROM competencia WHERE cod =?"; //achar titulo da comp
            PreparedStatement statement2 = conn.prepareStatement(query);
            statement2.setInt(1, codCompetencia);
            ResultSet result2 = statement2.executeQuery();
            String titulo = null;
            if (result2.next()) {
                titulo = result2.getString("titulo");
            }

            compexList.add(new Compex(cod, codCandidato, codCompetencia, experiencia, titulo));
        }
        return compexList.isEmpty() ? null : compexList.toArray(new Compex[0]);
    }

    public void apagarCompex(String email, CompexSimples compex) throws SQLException {
        String query = "DELETE FROM compex WHERE codCandidato IN (SELECT cod FROM candidato WHERE email =?) AND codCompetencia IN (SELECT cod FROM competencia WHERE titulo =?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, email);
        statement.setString(2, compex.getCompetencia());
        statement.executeUpdate();
    }

    //vagas -----
    public void cadastrarVaga(String nome, double faixaSalarial, String descricao, String estado, String[] competencias, String email) throws SQLException {
        String query = "INSERT INTO vaga (nome, faixaSalarial, descricao, competencia, estado, email) VALUES (?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, nome);
        statement.setDouble(2, faixaSalarial);
        statement.setString(3, descricao);
        statement.setString(4, Arrays.toString(competencias).replace("[", "").replace("]", ""));
        statement.setString(5, estado);
        statement.setString(6, email);
        statement.executeUpdate();
    }

    public void atualizarVaga(int cod, String nome, double faixaSalarial, String[] competencias, String descricao, String estado) throws SQLException {
        String query = "UPDATE vaga SET nome =?, faixaSalarial =?, competencia =?, descricao =?, estado =? WHERE cod =?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1, nome);
        statement.setDouble(2, faixaSalarial);
        statement.setString(3, Arrays.toString(competencias).replace("[", "").replace("]", ""));
        statement.setString(4, descricao);
        statement.setString(5, estado);
        statement.setInt(6, cod);
        statement.executeUpdate();
    }

    public Vaga[] listarVagas(String filtro, String email) throws SQLException, NullPointerException {
        List<Vaga> vagas = new ArrayList<>();
        String query = null;
        if (email != null) { //se email eh nulo, provavelmente eh candidato entao puxar tudo
            query = "SELECT * FROM vaga WHERE email LIKE '" + email + "'";
        } else if (filtro != null) { //
            query = "SELECT * FROM vaga WHERE ";
            query = query + filtro;
        }
        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int cod = result.getInt("cod");
            String nome = result.getString("nome");
            double faixaSalarial = result.getDouble("faixaSalarial");
            String competencia = result.getString("competencia");
            String descricao = result.getString("descricao");
            String estado = result.getString("estado");
            String[] competencias = competencia.replace("[", "").replace("]", "").split(", ");
            Vaga vaga = new Vaga(cod, nome, faixaSalarial, competencias, descricao, estado, email);
            vagas.add(vaga);
        }
        return vagas.toArray(Vaga[]::new);
    }

    public boolean apagarVaga(int cod) throws SQLException {
        String query = "DELETE FROM vaga WHERE cod = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, cod);
        int rowsAffected = statement.executeUpdate();
        return rowsAffected > 0;
    }

    public Vaga getVagaById(int cod) throws SQLException {
        String query = "SELECT * FROM vaga WHERE cod =?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setInt(1, cod);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String nome = result.getString("nome");
            double faixaSalarial = result.getDouble("faixaSalarial");
            String competencia = result.getString("competencia");
            String descricao = result.getString("descricao");
            String estado = result.getString("estado");
            String email = result.getString("email");
            String[] competencias = competencia.replace("[", "").replace("]", "").split(", ");
            return new Vaga(cod, nome, faixaSalarial, competencias, descricao, estado, email);
        } else {
            return null;
        }
    }

    //filtro
    public String gerarFiltro(String[] competencias, String tipo) {
        StringBuilder query = new StringBuilder();
        for (int i = 0; i < competencias.length; i++) {
            query.append("competencia LIKE '%").append(competencias[i]).append("%'");
            if (i < competencias.length - 1) {
                query.append(" ").append(tipo).append(" ");
            }
        }
        return query.toString();
    }
}
