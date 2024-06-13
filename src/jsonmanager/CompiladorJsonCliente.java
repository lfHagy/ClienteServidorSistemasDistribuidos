package jsonmanager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entidades.CompexSimples;
import java.util.Arrays;

public class CompiladorJsonCliente {

    JsonParser parser = new JsonParser();

    public static void main(String[] args) {
        String email = "placeholderEmail"; //teste
        String razaoSocial = "placeholderRS";
        String senha = "placeholderSenha";
        String cnpj = "placeholderCnpj";
        String nome = "placeholderNome";
        String token = "tokenPlaceholder";
        String[] array = {"compe", "tencia"};
        CompiladorJsonCliente compilerJson = new CompiladorJsonCliente();
        System.out.println(compilerJson.filtrarVagas(array, "OR", token));
        compilerJson.cadastrarCandidato(nome, email, senha);
        compilerJson.cadastrarEmpresa(razaoSocial, email, cnpj, senha, senha, nome);
        compilerJson.loginCandidato(email, senha);
        compilerJson.loginEmpresa(email, senha);
        compilerJson.logout(token);
    }

    public String cadastrarCandidato(String nome, String email, String senha) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarCandidato");
        json.addProperty("nome", nome);
        json.addProperty("email", email);
        json.addProperty("senha", senha);
        System.out.println("CompiladorJsonCliente criou " + json.get("operacao") + ": " + json.toString());
        return json.toString();
    }

    public String loginCandidato(String email, String senha) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "loginCandidato");
        json.addProperty("email", email);
        json.addProperty("senha", senha);
        System.out.println("CompiladorJsonCliente criou " + json.get("operacao") + ": " + json.toString());
        return json.toString();
    }

    public String visualizarCandidato(String email, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarCandidato");
        json.addProperty("email", email);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonCliente criou " + json.get("operacao") + ": " + json.toString());
        return json.toString();
    }

    public String atualizarCandidato(String email, String nome, String senha, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "atualizarCandidato");
        json.addProperty("email", email);
        json.addProperty("nome", nome);
        json.addProperty("senha", senha);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonCliente criou " + json.get("operacao") + ": " + json.toString());
        return json.toString();
    }

    public String apagarCandidato(String email, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "apagarCandidato");
        json.addProperty("email", email);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonCliente criou " + json.get("operacao") + ": " + json.toString());
        return json.toString();
    }

    //empresa
    public String cadastrarEmpresa(String razaoSocial, String email, String cnpj, String senha, String descricao, String ramo) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarEmpresa");
        json.addProperty("razaoSocial", razaoSocial);
        json.addProperty("email", email);
        json.addProperty("cnpj", cnpj);
        json.addProperty("senha", senha);
        json.addProperty("descricao", descricao);
        json.addProperty("ramo", ramo);
        System.out.println("CompiladorJsonCliente criou " + json.get("operacao") + ": " + json.toString());
        return json.toString();
    }

    public String loginEmpresa(String email, String senha) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "loginEmpresa");
        json.addProperty("email", email);
        json.addProperty("senha", senha);
        System.out.println("CompiladorJsonCliente criou " + json.get("operacao") + ": " + json.toString());
        return json.toString();
    }

    public String visualizarEmpresa(String email, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarEmpresa");
        json.addProperty("email", email);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonCliente criou " + json.get("operacao") + ": " + json.toString());
        return json.toString();
    }

    public String atualizarEmpresa(String email, String razaoSocial, String cnpj, String senha, String descricao, String ramo, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "atualizarEmpresa");
        json.addProperty("email", email);
        json.addProperty("razaoSocial", razaoSocial);
        json.addProperty("cnpj", cnpj);
        json.addProperty("senha", senha);
        json.addProperty("descricao", descricao);
        json.addProperty("ramo", ramo);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonCliente criou " + json.get("operacao") + ": " + json.toString());
        return json.toString();
    }

    public String apagarEmpresa(String email, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "apagarEmpresa");
        json.addProperty("email", email);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonCliente criou " + json.get("operacao") + ": " + json.toString());
        return json.toString();
    }

    //compex
    public String cadastrarCompetenciaExperiencia(String email, CompexSimples[] competenciaExperiencia, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarCompetenciaExperiencia");
        json.addProperty("email", email);
        JsonArray competenciaExperienciaArray = new JsonArray();
        for (int i = 0; i < competenciaExperiencia.length; i++) {
            JsonObject compExJson = new JsonObject();
            compExJson.addProperty("competencia", competenciaExperiencia[i].getCompetencia());
            compExJson.addProperty("experiencia", competenciaExperiencia[i].getExperiencia());
            competenciaExperienciaArray.add(compExJson);
        }
        json.add("competenciaExperiencia", competenciaExperienciaArray);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonCliente enviou operacao " + json.get("operacao"));
        return json.toString();
    }

    public String visualizarCompetenciaExperiencia(String email, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarCompetenciaExperiencia");
        json.addProperty("email", email);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonServidor enviou operacao " + json.get("operacao"));
        return json.toString();
    }

    public String atualizarCompetenciaExperiencia(String email, CompexSimples[] competenciaExperiencia, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "atualizarCompetenciaExperiencia");
        json.addProperty("email", email);
        JsonArray competenciaExperienciaArray = new JsonArray();
        for (int i = 0; i < competenciaExperiencia.length; i++) {
            JsonObject compExJson = new JsonObject();
            compExJson.addProperty("competencia", competenciaExperiencia[i].getCompetencia());
            compExJson.addProperty("experiencia", competenciaExperiencia[i].getExperiencia());
            competenciaExperienciaArray.add(compExJson);
        }
        json.add("competenciaExperiencia", competenciaExperienciaArray);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonCliente enviou operacao " + json.get("operacao"));
        return json.toString();
    }

    public String apagarCompetenciaExperiencia(String email, CompexSimples[] competenciaExperiencia, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "apagarCompetenciaExperiencia");
        json.addProperty("email", email);
        JsonArray competenciaExperienciaArray = new JsonArray();
        for (int i = 0; i < competenciaExperiencia.length; i++) {
            JsonObject compExJson = new JsonObject();
            compExJson.addProperty("competencia", competenciaExperiencia[i].getCompetencia());
            compExJson.addProperty("experiencia", competenciaExperiencia[i].getExperiencia());
            competenciaExperienciaArray.add(compExJson);
        }
        json.add("competenciaExperiencia", competenciaExperienciaArray);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonCliente enviou operacao " + json.get("operacao"));
        return json.toString();
    }

    //vagas
    public String cadastrarVaga(String nome, String email, double faixaSalarial, String descricao, String estado, String[] competencias, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarVaga");
        json.addProperty("nome", nome);
        json.addProperty("email", email);
        json.addProperty("faixaSalarial", faixaSalarial);
        json.addProperty("descricao", descricao);
        json.addProperty("estado", estado);
        JsonArray competenciasArray = new JsonArray();
        for (String competencia : competencias) {
            competenciasArray.add(competencia);
        }
        json.add("competencias", competenciasArray);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonServidor enviou operacao " + json.get("operacao"));
        return json.toString();
    }

    public String visualizarVaga(int idVaga, String email, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarVaga");
        json.addProperty("idVaga", idVaga);
        json.addProperty("email", email);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonServidor enviou operacao " + json.get("operacao"));
        return json.toString();
    }

    public String atualizarVaga(int id, String nome, String email, double faixaSalarial, String descricao, String estado, String[] competencias, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "atualizarVaga");
        json.addProperty("idVaga", id);
        json.addProperty("nome", nome);
        json.addProperty("email", email);
        json.addProperty("faixaSalarial", faixaSalarial);
        json.addProperty("descricao", descricao);
        json.addProperty("estado", estado);
        JsonArray competenciasArray = new JsonArray();
        for (String competencia : competencias) {
            competenciasArray.add(competencia);
        }
        json.add("competencias", competenciasArray);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonServidor enviou operacao " + json.get("operacao"));
        return json.toString();
    }

    public String apagarVaga(int id, String email, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "apagarVaga");
        json.addProperty("idVaga", id);
        json.addProperty("email", email);
        json.addProperty("token", token);
        return json.toString();
    }

    public String listarVagas(String email, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "listarVagas");
        json.addProperty("email", email);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonServidor enviou operacao " + json.get("operacao"));
        return json.toString();
    }

    public String filtrarVagas(String[] competencias, String tipo, String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "filtrarVagas");
        JsonObject filtros = new JsonObject();
        JsonArray competenciasArray = new JsonArray();
        for (String competencia : competencias) {
            competenciasArray.add(competencia);
        }
        filtros.add("competencias", competenciasArray);
        filtros.addProperty("tipo", tipo);
        json.add("filtros", filtros);
        json.addProperty("token", token);
        return json.toString();
    }

    //genericos
    public String logout(String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "logout");
        json.addProperty("token", token);
        System.out.println("CompiladorJsonCliente criou " + json.get("operacao") + ": " + json.toString());
        return json.toString();
    }

    public String definirTokenSessao(String retorno) {
        JsonObject json = (parser.parse(retorno)).getAsJsonObject();
        System.out.println("Parser p/a token determinou retorno " + json);
        if (json.get("status").getAsInt() != 200) {
            System.out.println("Parser determinou erro no login ou o login nao foi . Token nao pode ser definida.");
            return null;
        }
        String token = json.get("token").getAsString();
        System.out.println("Token retornada: " + token);
        return token;
    }

    public boolean checkOperacao(String retorno, String operacao) {
        JsonElement json = parser.parse(retorno);
        JsonObject jsonObject = json.getAsJsonObject();
        String operacaoRetornada = jsonObject.get("operacao").getAsString();
        return operacaoRetornada.equals(operacao);
    }
}
