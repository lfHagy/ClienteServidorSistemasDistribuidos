package jsonmanager;

import com.google.gson.*;
import entidades.*;
import java.sql.SQLException;
import java.util.Arrays;

public class InterpretadorJson {

    JsonParser parser = new JsonParser();
    CompiladorJsonServidor compilador;
    ManagerBD dbManager;

    public InterpretadorJson() throws Exception {
        System.out.println("Interpretador inicializado");
        this.dbManager = new ManagerBD();
        this.compilador = new CompiladorJsonServidor();
    }

    public static void main(String[] args) {
        String temp = "{\"operacao\":\"cadastrarCandidato\",\"nome\":\"placeholderNome\",\"email\":\"placeholderEmail\",\"senha\":\"placeholderSenha\"}";
    }

    public String processarRequest(String input) throws Exception {
        if (input == null) {
            System.out.println("Usuario enviou null!");
            return compilador.erroGenerico("desconhecida", "O objeto recebido pelo servidor esta nulo!");
        }
        JsonElement json = parser.parse(input);
        JsonObject jsonObject = json.getAsJsonObject();
        String operacao = jsonObject.get("operacao").getAsString();
        System.out.println("IntepretadorJson determinou operação: " + operacao);
        switch (operacao) {
            //genericos
            case "logout" -> {
                String token = jsonObject.get("token").getAsString();
                System.out.println("Usuário de token " + token + " efetuando logout...");
                return compilador.logout();
            }
            //candidato
            case "cadastrarCandidato" -> {
                //TODO limites tamanho da senha e email
                try {
                    String nome = jsonObject.get("nome").getAsString();
                    String email = jsonObject.get("email").getAsString();
                    String senha = jsonObject.get("senha").getAsString();
                    System.out.println("Tentando inserir candidato de nome " + nome + "...");
                    dbManager.cadastrarCandidato(nome, email, senha);
                    System.out.println("Sucesso ao inserir candidato " + nome + "!");
                    System.out.println("Gerando token...");
                    dbManager.gerarToken(email);
                    String tokenGerada = dbManager.getToken(email);
                    System.out.println("Token gerada: " + tokenGerada);
                    return compilador.sucessoCadastroCandidato(tokenGerada);
                } catch (SQLException e) {
                    if (e.getErrorCode() == 1062) { //duplicidade de registro
                        return compilador.falhaCadastroCandidatoEmail();
                    } else {
                        e.printStackTrace();
                        return compilador.falhaCadastroCandidato("Nao foi possivel cadastrar o candidato. (" + e.getMessage() + ")");
                    }
                }
            }
            case "loginCandidato" -> {
                try {
                    String email = jsonObject.get("email").getAsString();
                    String senha = jsonObject.get("senha").getAsString();
                    System.out.println("Tentativa de login na conta do candidato de email " + email);
                    if (dbManager.loginCandidato(email, senha) != null) {
                        System.out.println("Credenciais corretas.");
                        String token = dbManager.getToken(email);
                        return compilador.sucessoLoginCandidato(token);
                    } else {
                        System.out.println("Credenciais incorretas");
                        return compilador.falhaLoginCandidatoCredenciais();
                    }
                } catch (SQLException e) {
                    System.out.println("Ocorreu uma exceção SQL: " + e.getMessage());
                    return compilador.falhaLoginCandidatoCredenciais();
                } catch (JsonSyntaxException e) {
                    System.err.println("Erro de sintaxe no JSON: " + e.getMessage());
                    return compilador.falhaLoginCandidatoCredenciais();
                } catch (NullPointerException e) {
                    System.err.println("Ocorreu um erro devido a um objeto nulo: " + e.getMessage());
                    return compilador.falhaLoginCandidatoCredenciais();
                }
            }
            case "visualizarCandidato" -> {
                try {
                    String email = jsonObject.get("email").getAsString();
                    String token = jsonObject.get("token").getAsString();
                    if (token == null ? dbManager.getToken(email) != null : !token.equals(dbManager.getToken(email))) {
                        System.out.println("Token no BD: " + dbManager.getToken(email));
                        System.out.println("Token invalida! A token " + token + " nao bate com o email " + email);
                        return compilador.erroGenerico("visualizarCandidato", "Sua token nao bate com a do email utilizado para esse candidato.");
                    }
                    Candidato resultado = dbManager.visualizarCandidato(email);
                    if (resultado != null) {
                        return compilador.sucessoVisualizarCandidato(resultado.getNome(), resultado.getSenha());
                    } else {
                        System.out.println("Nao existe candidato de email " + email + " no BD!");
                        return compilador.falhaVisualizarCandidatoInexistente();
                    }
                } catch (SQLException e) {
                    System.out.println("Ocorreu uma exceção SQL: " + e.getMessage());
                    return compilador.falhaVisualizarCandidatoInexistente();
                } catch (JsonSyntaxException e) {
                    System.err.println("Erro de sintaxe no JSON: " + e.getMessage());
                    return compilador.falhaVisualizarCandidatoInexistente();
                } catch (NullPointerException e) {
                    System.err.println("Ocorreu um erro devido a um objeto nulo: " + e.getMessage());
                    return compilador.falhaVisualizarCandidatoInexistente();
                }
            }
            case "atualizarCandidato" -> {
                try {
                    String email = jsonObject.get("email").getAsString();
                    String token = jsonObject.get("token").getAsString();
                    System.out.println("Token retornada: " + token);
                    if (tokenBate(email, token) == true) {
                        String nome = jsonObject.get("nome").getAsString();
                        String senha = jsonObject.get("senha").getAsString();
                        System.out.println("Tentando atualizar candidato de email " + email + "...");
                        if (dbManager.visualizarCandidato(email) == null) {
                            System.out.println("Candidato nao encontrado.");
                            return compilador.falhaAtualizarCandidatoInexistente();
                        } else {
                            dbManager.atualizarCandidato(nome, email, senha);
                            System.out.println("Candidato atualizado!");
                            return compilador.sucessoAtualizarCandidato();
                        }
                    } else {
                        return compilador.erroGenerico("atualizarCandidato", "Sua token nao bate com a do email utilizado para esse candidato.");
                    }
                } catch (ClassCastException | IllegalStateException e) {
                    System.out.println("Erro ao obter dados do JSON: " + e.getMessage());
                    return compilador.falhaAtualizarCandidatoInexistente();
                } catch (NullPointerException e) {
                    System.out.println("Erro: jsonObject ou dbManager eh nulo.");
                    return compilador.falhaAtualizarCandidatoInexistente();
                } catch (SQLException e) {
                    System.out.println("Erro ao atualizar candidato no banco de dados: " + e.getMessage());
                    return compilador.falhaAtualizarCandidatoInexistente();
                }
            }
            case "apagarCandidato" -> {
                try {
                    String email = jsonObject.get("email").getAsString();
                    String token = jsonObject.get("token").getAsString();
                    if (tokenBate(email, token) == true) {
                        if (dbManager.visualizarCandidato(email) == null) {
                            System.out.println("Candidato inexistente.");
                            return compilador.falhaApagarCandidatoInexistente();
                        } else {
                            System.out.println("Apagando candidato de email " + email + "...");
                            dbManager.apagarCandidato(email);
                            System.out.println("Candidato apagado!");
                            return compilador.sucessoApagarCandidato();
                        }
                    } else {
                        return compilador.erroGenerico("atualizarCandidato", "Sua token nao bate com a do email utilizado para esse candidato.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao apagar o candidato no banco de dados: " + e.getMessage());
                    return compilador.erroGenerico(operacao, e.getMessage());
                }
            }
            //compex
            case "cadastrarCompetenciaExperiencia" -> {
                boolean nenhumResultado = true;
                String email = jsonObject.get("email").getAsString();
                String token = jsonObject.get("token").getAsString();
                JsonArray competencias = jsonObject.get("competenciaExperiencia").getAsJsonArray();
                if (tokenBate(email, token) == true) {
                    System.out.println("--- CompEx do candidato " + email + " ---");
                    for (JsonElement competenciaElement : competencias) {
                        JsonObject competenciaObject = competenciaElement.getAsJsonObject();
                        String titulo = competenciaObject.get("competencia").getAsString();
                        int experiencia = competenciaObject.get("experiencia").getAsInt();
                        dbManager.cadastrarCompex(email, titulo, experiencia);
                        System.out.println("CompEx cadastrado: " + titulo + experiencia);
                        if (nenhumResultado) { //checa se compex esta vazio
                            nenhumResultado = false;
                        }
                    }
                    if (nenhumResultado) {
                        return compilador.falhaCadastroCompex("Nao ha nenhum registro de competencia/experiencia para o email " + email + ".");
                    } else {
                        return compilador.sucessoCadastroCompex();
                    }
                } else {
                    return compilador.erroGenerico("cadastrarCompetenciaExperiencia", "Sua token nao bate com a do email utilizado para esse candidato.");
                }
            }
            case "visualizarCompetenciaExperiencia" -> {
                String email = jsonObject.get("email").getAsString();
                String token = jsonObject.get("token").getAsString();
                if (tokenBate(email, token) == true) {
                    Compex[] compex = dbManager.listarCompex(email);
                    if (compex != null) {
                        return compilador.sucessoVisualizarCompex(compex);
                    } else {
                        return compilador.falhaVisualizarCompex("O servidor nao encontrou nenhum registro de competencia/experiencia.");
                    }
                } else {
                    return compilador.erroGenerico("atualizarCandidato", "Sua token nao bate com a do email utilizado para esse candidato.");
                }
            }
            case "apagarCompetenciaExperiencia" -> {
                String email = jsonObject.get("email").getAsString();
                String token = jsonObject.get("token").getAsString();
                if (tokenBate(email, token) == true) {
                    JsonArray competenciaExperienciaArray = jsonObject.get("competenciaExperiencia").getAsJsonArray();
                    for (JsonElement element : competenciaExperienciaArray) {
                        JsonObject competenciaExperienciaObject = element.getAsJsonObject();
                        String titulo = competenciaExperienciaObject.get("competencia").getAsString();
                        int experiencia = competenciaExperienciaObject.get("experiencia").getAsInt();
                        dbManager.apagarCompex(email, new CompexSimples(titulo, experiencia));
                        return compilador.sucessoApagarCompex();
                    }
                } else {
                    return compilador.erroGenerico("atualizarCandidato", "Sua token nao bate com a do email utilizado para esse candidato.");
                }
                break;
            }
            case "atualizarCompetenciaExperiencia" -> {
                String email = jsonObject.get("email").getAsString();
                String token = jsonObject.get("token").getAsString();
                if (tokenBate(email, token) == true) {
                    JsonArray competenciaExperienciaArray = jsonObject.get("competenciaExperiencia").getAsJsonArray();
                    for (JsonElement element : competenciaExperienciaArray) {
                        JsonObject competenciaExperienciaObject = element.getAsJsonObject();
                        String titulo = competenciaExperienciaObject.get("competencia").getAsString();
                        int experiencia = competenciaExperienciaObject.get("experiencia").getAsInt();
                        dbManager.atualizarCompex(email, new CompexSimples(titulo, experiencia));
                        return compilador.sucessoAtualizarCompex();
                    }
                } else {
                    return compilador.erroGenerico("atualizarCandidato", "Sua token nao bate com a do email utilizado para esse candidato.");
                }
            }

            //empresa
            case "cadastrarEmpresa" -> {
                try {
                    String razaoSocial = jsonObject.get("razaoSocial").getAsString();
                    String email = jsonObject.get("email").getAsString();
                    String cnpj = jsonObject.get("cnpj").getAsString();
                    String senha = jsonObject.get("senha").getAsString();
                    String descricao = jsonObject.get("descricao").getAsString();
                    String ramo = jsonObject.get("ramo").getAsString();
                    System.out.println("Tentando inserir empresa de nome " + razaoSocial + "...");
                    dbManager.cadastrarEmpresa(razaoSocial, email, cnpj, senha, descricao, ramo);
                    System.out.println("Sucesso ao inserir empresa " + razaoSocial + "!");
                    System.out.println("Gerando token...");
                    dbManager.gerarToken(email);
                    String tokenGerada = dbManager.getToken(email);
                    System.out.println("Token gerada: " + tokenGerada);
                    return compilador.sucessoCadastroEmpresa(tokenGerada);
                } catch (SQLException e) {
                    if (e.getErrorCode() == 1062) { //duplicidade de registro
                        return compilador.falhaCadastroEmpresaEmail();
                    } else {
                        return compilador.falhaCadastroEmpresa("Nao foi possivel cadastrar a empresa. (" + e.getMessage() + ")");
                    }
                }
            }
            case "loginEmpresa" -> {
                try {
                    String email = jsonObject.get("email").getAsString();
                    String senha = jsonObject.get("senha").getAsString();
                    System.out.println("Tentativa de login na conta da empresa de email " + email);
                    if (dbManager.loginEmpresa(email, senha) != null) {
                        System.out.println("Credenciais corretas.");
                        String token = dbManager.getToken(email);
                        return compilador.sucessoLoginEmpresa(token);
                    } else {
                        System.out.println("Credenciais incorretas");
                        return compilador.falhaLoginEmpresaCredenciais();
                    }
                } catch (SQLException e) {
                    System.out.println("Ocorreu uma exceção SQL: " + e.getMessage());
                    return compilador.falhaLoginEmpresaCredenciais();
                } catch (JsonSyntaxException e) {
                    System.err.println("Erro de sintaxe no JSON: " + e.getMessage());
                    return compilador.falhaLoginEmpresaCredenciais();
                } catch (NullPointerException e) {
                    System.err.println("Ocorreu um erro devido a um objeto nulo: " + e.getMessage());
                    return compilador.falhaLoginEmpresaCredenciais();
                }
            }
            case "visualizarEmpresa" -> {
                try {
                    String email = jsonObject.get("email").getAsString();
                    Empresa resultado = dbManager.visualizarEmpresa(email);
                    System.out.println("");
                    if (resultado != null) {
                        return compilador.sucessoVisualizarEmpresa(resultado.getRazaoSocial(), resultado.getCnpj(), resultado.getSenha(), resultado.getDescricao(), resultado.getRamo());
                    } else {
                        System.out.println("Nao existe empresa de email " + email + " no BD!");
                        return compilador.falhaVisualizarEmpresaInexistente();
                    }
                } catch (SQLException e) {
                    System.out.println("Ocorreu uma exceção SQL: " + e.getMessage());
                    return compilador.falhaVisualizarEmpresaInexistente();
                } catch (JsonSyntaxException e) {
                    System.err.println("Erro de sintaxe no JSON: " + e.getMessage());
                    return compilador.falhaVisualizarEmpresaInexistente();
                } catch (NullPointerException e) {
                    System.err.println("Ocorreu um erro devido a um objeto nulo: " + e.getMessage());
                    return compilador.falhaVisualizarEmpresaInexistente();
                }
            }
            case "atualizarEmpresa" -> {
                try {
                    String email = jsonObject.get("email").getAsString();
                    String token = jsonObject.get("token").getAsString();
                    if (tokenBate(email, token)) {
                        String razaoSocial = jsonObject.get("razaoSocial").getAsString();
                        String cnpj = jsonObject.get("cnpj").getAsString();
                        String senha = jsonObject.get("senha").getAsString();
                        String descricao = jsonObject.get("descricao").getAsString();
                        String ramo = jsonObject.get("ramo").getAsString();
                        System.out.println("Tentando atualizar empresa de email " + email + "...");
                        if (dbManager.visualizarEmpresa(email) == null) {
                            System.out.println("Empresa nao encontrada.");
                            return compilador.falhaAtualizarEmpresaInexistente();
                        } else {
                            dbManager.atualizarEmpresa(razaoSocial, email, cnpj, senha, descricao, ramo);
                            System.out.println("Empresa atualizada!");
                            return compilador.sucessoAtualizarEmpresa();
                        }
                    } else {
                        return compilador.erroGenerico("atualizarEmpresa", "Sua token nao bate com a do email utilizado para essa empresa.");
                    }
                } catch (ClassCastException | IllegalStateException e) { //TODO - erros especificos
                    System.out.println("Erro ao obter dados do JSON: " + e.getMessage());
                    return compilador.falhaAtualizarEmpresaInexistente();
                } catch (NullPointerException e) {
                    System.out.println("Erro: jsonObject ou dbManager eh nulo.");
                    return compilador.falhaAtualizarEmpresaInexistente();
                } catch (SQLException e) {
                    System.out.println("Erro ao atualizar candidato no banco de dados: " + e.getMessage());
                    return compilador.falhaAtualizarEmpresaInexistente();
                }
            }
            case "apagarEmpresa" -> {
                try {
                    String email = jsonObject.get("email").getAsString();
                    String token = jsonObject.get("token").getAsString();
                    if (tokenBate(email, token)) {
                        if (dbManager.visualizarEmpresa(email) == null) {
                            System.out.println("Empresa inexistente.");
                            return compilador.falhaApagarEmpresaInexistente();
                        } else {
                            System.out.println("Apagando empresa de email " + email + "...");
                            dbManager.apagarEmpresa(email);
                            System.out.println("Empresa apagada!");
                            return compilador.sucessoApagarEmpresa();
                        }
                    } else {
                        return compilador.erroGenerico("atualizarEmpresa", "Sua token nao bate com a do email utilizado para essa empresa.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao apagar a empresa no banco de dados: " + e.getMessage());
                    return compilador.falhaApagarEmpresaInexistente();
                }
            }

            //vagas
            case "cadastrarVaga" -> {
                String nome = jsonObject.get("nome").getAsString();
                String email = jsonObject.get("email").getAsString();
                String token = jsonObject.get("token").getAsString();
                if (tokenBate(email, token)) {
                    double faixaSalarial = jsonObject.get("faixaSalarial").getAsDouble();
                    String descricao = jsonObject.get("descricao").getAsString();
                    String estado = jsonObject.get("estado").getAsString();
                    JsonArray competenciasArray = jsonObject.get("competencias").getAsJsonArray();
                    String[] competencias = new String[competenciasArray.size()];
                    for (int i = 0; i < competenciasArray.size(); i++) {
                        competencias[i] = competenciasArray.get(i).getAsString();
                    }
                    dbManager.cadastrarVaga(nome, faixaSalarial, descricao, estado, competencias, email);
                    return compilador.sucessoCadastroVaga(token);
                } else {
                    return compilador.erroGenerico(operacao, "Sua token nao bate com a token da empresa especificada.");
                }
            }
            case "visualizarVaga" -> {
                int idVaga = jsonObject.get("idVaga").getAsInt();
                String email = jsonObject.get("email").getAsString();
                String token = jsonObject.get("token").getAsString();
                if (tokenBate(email, token)) {
                    Vaga vaga = dbManager.getVagaById(idVaga);
                    return compilador.sucessoVisualizarVaga(vaga.getFaixaSalarial(), vaga.getDescricao(), vaga.getEstado(), vaga.getCompetencias());
                } else {
                    return compilador.erroGenerico(operacao, "Sua token nao bate com a token da empresa especificada.");
                }
            }
            case "listarVagas" -> {
                try {
                    String email = jsonObject.get("email").getAsString();
                    Vaga[] vagas = dbManager.listarVagas(null, email);
                    System.out.println("");
                    if (vagas != null) {
                        return compilador.listarVagas(vagas);
                    } else {
                        System.out.println("Nao existem vagas para empresa de email " + email + " no BD!");
                        return compilador.erroGenerico(operacao, "Nao existem vagas registradas nesse email.");
                    }
                } catch (SQLException e) {
                    System.out.println("Ocorreu uma exceção SQL: " + e.getMessage());
                    return compilador.falhaVisualizarEmpresaInexistente();
                } catch (JsonSyntaxException e) {
                    System.err.println("Erro de sintaxe no JSON: " + e.getMessage());
                    return compilador.erroGenerico("visualizarVaga", "Ocorreu um erro de sintaxe no json.");
                } catch (NullPointerException e) {
                    System.err.println("Ocorreu um erro devido a um objeto nulo: " + e.getMessage());
                    return compilador.erroGenerico("visualizarVaga", "Ocorreu um erro de nullpointer.");
                }
            }
            case "atualizarVaga" -> {
                try {
                    System.out.println("Entrou no interpretador");
                    String email = jsonObject.get("email").getAsString();
                    String token = jsonObject.get("token").getAsString();
                    if (tokenBate(email, token)) {
                        System.out.println("Token ok");
                        String nome = jsonObject.get("nome").getAsString();
                        int cod = jsonObject.get("idVaga").getAsInt();
                        double faixaSalarial = jsonObject.get("faixaSalarial").getAsDouble();
                        String descricao = jsonObject.get("descricao").getAsString();
                        String estado = jsonObject.get("estado").getAsString();
                        JsonArray competenciasArray = jsonObject.get("competencias").getAsJsonArray();
                        String[] competencias = new String[competenciasArray.size()];
                        for (int i = 0; i < competenciasArray.size(); i++) {
                            competencias[i] = competenciasArray.get(i).getAsString();
                        }
                        System.out.println("Tentando inserir");
                        dbManager.atualizarVaga(cod, nome, faixaSalarial, competencias, descricao, estado);
                        return compilador.sucessoAtualizarVaga();
                    } else {
                        return compilador.erroGenerico("atualizarEmpresa", "Sua token nao bate com a do email utilizado para essa empresa.");
                    }
                } catch (SQLException e) {
                    System.out.println("Excecao SQL!" + e.getMessage());
                    return compilador.falhaAtualizarVaga();
                }
            }
            case "apagarVaga" -> {
                try {
                    int cod = jsonObject.get("idVaga").getAsInt();
                    String email = jsonObject.get("email").getAsString();
                    String token = jsonObject.get("token").getAsString();
                    if (tokenBate(email, token)) {
                        dbManager.apagarVaga(cod);
                        return compilador.sucessoApagarVaga(); //todo
                    } else {
                        return compilador.erroGenerico("apagarVaga", "Sua token nao bate com a do email utilizado para a empresa que registrou essa vaga.");
                    }
                } catch (SQLException e) {
                    return compilador.falhaApagarVaga(); //todo
                }
            }
            case "filtrarVagas" -> {
                try {
                    JsonObject filtros = jsonObject.get("filtros").getAsJsonObject();
                    JsonArray competenciasArray = filtros.get("competencias").getAsJsonArray();
                    String tipo = filtros.get("tipo").getAsString();
                    String[] competencias = new String[competenciasArray.size()];
                    for (int i = 0; i < competenciasArray.size(); i++) {
                        competencias[i] = competenciasArray.get(i).getAsString();
                    }
                    Vaga[] vagas = dbManager.listarVagas(dbManager.gerarFiltro(competencias, tipo), null);
                    System.out.println("Vagas encontradas: " + Arrays.toString(vagas));
                    if (vagas != null) {
                        return compilador.filtrarVagas(vagas);
                    } else {
                        return compilador.erroGenerico(operacao, "Nao existem vagas registradas com esse filtro.");
                    }
                } catch (SQLException e) {
                    System.out.println("Ocorreu uma exceção SQL: " + e.getMessage());
                    return compilador.erroGenerico("filtrarVaga", "Nao foram encontradas vagas que se encaixem neses filtro.");
                } catch (JsonSyntaxException e) {
                    System.err.println("Erro de sintaxe no JSON: " + e.getMessage());
                    return compilador.erroGenerico("filtrarVaga", "Ocorreu um erro de sintaxe no json.");
                } catch (NullPointerException e) {
                    System.err.println("Ocorreu um erro devido a um objeto nulo: " + e.getMessage());
                    return compilador.erroGenerico("filtrarVaga", "Ocorreu um erro de nullpointer.");
                }
            }
            default -> {
                System.err.println("Nao foi possivel tratar o request - InterpretadorJson nao conseguiu determinar a operacao ou a mesma eh invalida!");
                return compilador.erroGenerico(operacao, "O titulo da operacao nao pode ser tratado ou nao segue o protocolo.");
            }
        } //supostamente unreachable
        System.err.print(
                "Nao foi possivel tratar o request - classe InterpretadorJson atingiu EOF!");
        return compilador.erroGenerico(operacao,
                "O titulo da operacao nao pode ser tratado ou nao segue o protocolo.");
    }

    public boolean tokenBate(String email, String token) throws SQLException {
        if (token == null ? dbManager.getToken(email) != null : !token.equals(dbManager.getToken(email))) {
            System.out.println("Token invalida! A token " + token + " nao bate com o email " + email);
            return false;
        } else {
            return true;
        }
    }
}
