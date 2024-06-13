package jsonmanager;

import com.google.gson.*;
import entidades.Compex;
import entidades.Vaga;

public class CompiladorJsonServidor {

    //candidato ------
    public String sucessoCadastroCandidato(String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarCandidato");
        json.addProperty("status", 201);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaCadastroCandidato(String mensagem) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarCandidato");
        json.addProperty("status", 404);
        json.addProperty("mensagem", mensagem);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaCadastroCandidatoEmail() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarCandidato");
        json.addProperty("status", 422);
        json.addProperty("mensagem", "E-mail já cadastrado");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoLoginCandidato(String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "loginCandidato");
        json.addProperty("status", 200);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaLoginCandidatoCredenciais() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "loginCandidato");
        json.addProperty("status", 401);
        json.addProperty("mensagem", "Login ou senha incorretos");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoVisualizarCandidato(String nome, String senha) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarCandidato");
        json.addProperty("status", 201);
        json.addProperty("nome", nome);
        json.addProperty("senha", senha);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaVisualizarCandidatoInexistente() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarCandidato");
        json.addProperty("status", 404);
        json.addProperty("mensagem", "E-mail não encontrado");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoAtualizarCandidato() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "atualizarCandidato");
        json.addProperty("status", 201);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaAtualizarCandidatoInexistente() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "atualizarCandidato");
        json.addProperty("status", 404);
        json.addProperty("mensagem", "E-mail não encontrado");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoApagarCandidato() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "apagarCandidato");
        json.addProperty("status", 201);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaApagarCandidatoInexistente() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "apagarCandidato");
        json.addProperty("status", 404);
        json.addProperty("mensagem", "E-mail não encontrado");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    //empresa ------
    public String sucessoCadastroEmpresa(String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarEmpresa");
        json.addProperty("status", 201);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaCadastroEmpresa(String mensagem) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarEmpresa");
        json.addProperty("status", 404);
        json.addProperty("mensagem", mensagem);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaCadastroEmpresaEmail() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarEmpresa");
        json.addProperty("status", 422);
        json.addProperty("mensagem", "E-mail já cadastrado");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaCadastroEmpresaCNPJEmUso() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarEmpresa");
        json.addProperty("status", 422);
        json.addProperty("mensagem", "CNPJ já cadastrado");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoLoginEmpresa(String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "loginEmpresa");
        json.addProperty("status", 200);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaLoginEmpresaCredenciais() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "loginEmpresa");
        json.addProperty("status", 401);
        json.addProperty("mensagem", "Login ou senha incorretos");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoVisualizarEmpresa(String razaoSocial, String cnpj, String senha, String descricao, String ramo) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarEmpresa");
        json.addProperty("status", 201);
        json.addProperty("razaoSocial", razaoSocial);
        json.addProperty("cnpj", cnpj);
        json.addProperty("senha", senha);
        json.addProperty("descricao", descricao);
        json.addProperty("ramo", ramo);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaVisualizarEmpresaInexistente() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarEmpresa");
        json.addProperty("status", 404);
        json.addProperty("mensagem", "E-mail não encontrado");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoAtualizarEmpresa() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "atualizarEmpresa");
        json.addProperty("status", 201);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaAtualizarEmpresaInexistente() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "atualizarCandidato");
        json.addProperty("status", 404);
        json.addProperty("mensagem", "E-mail não encontrado");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoApagarEmpresa() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "apagarEmpresa");
        json.addProperty("status", 201);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaApagarEmpresaInexistente() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "apagarEmpresa");
        json.addProperty("status", 404);
        json.addProperty("mensagem", "E-mail não encontrado");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    //logout
    public String logout() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "logout");
        json.addProperty("status", 204);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    //competencias e experiencia
    public String sucessoCadastroCompetenciaExperiencia(String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarCompetenciaExperiencia");
        json.addProperty("status", 201);
        json.addProperty("token", token);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaCadastroCompetenciaExperiencia(String mensagem) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarCompetenciaExperiencia");
        json.addProperty("status", 404);
        json.addProperty("mensagem", mensagem);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoVisualizarCompetenciaExperiencia(String competenciaExperiencia) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarCompetenciaExperiencia");
        json.addProperty("status", 201);
        JsonArray competenciaExperienciaArray = new JsonArray();
        for (String ce : competenciaExperiencia.split(",")) {
            String[] parts = ce.split(":");
            JsonObject ceJson = new JsonObject();
            ceJson.addProperty("competencia", parts[0]);
            ceJson.addProperty("experiencia", parts[1]);
            competenciaExperienciaArray.add(ceJson);
        }
        json.add("competenciaExperiencia", competenciaExperienciaArray);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String erroVisualizarCompetenciaExperiencia(String mensagem) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarCompetenciaExperiencia");
        json.addProperty("status", 422);
        json.addProperty("mensagem", mensagem);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    //vagas
    public String sucessoCadastroVaga(String token) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarVaga");
        json.addProperty("status", 201);
        json.addProperty("mensagem", "Vaga cadastrada com sucesso");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String erroCadastroVaga(String mensagem) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarVaga");
        json.addProperty("status", 422);
        json.addProperty("mensagem", mensagem);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoVisualizarVaga(double faixaSalarial, String descricao, String estado, String[] competencias) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarVaga");
        json.addProperty("status", 201);
        json.addProperty("faixaSalarial", faixaSalarial);
        json.addProperty("descricao", descricao);
        json.addProperty("estado", estado);
        JsonArray competenciasArray = new JsonArray();
        for (String competencia : competencias) {
            competenciasArray.add(competencia);
        }
        json.add("competencias", competenciasArray);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String erroVisualizarVaga(String mensagem) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarVaga");
        json.addProperty("status", 404);
        json.addProperty("mensagem", mensagem);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoAtualizarVaga() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "atualizarVaga");
        json.addProperty("status", 201);
        json.addProperty("mensagem", "Vaga atualizada com sucesso.");
        return json.toString();
    }

    public String falhaAtualizarVaga() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "atualizarVaga");
        json.addProperty("status", 422);
        json.addProperty("mensagem", "Nao foi possivel atualizar a vaga.");
        return json.toString();
    }

    public String sucessoApagarVaga() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "apagarVaga");
        json.addProperty("status", 201);
        json.addProperty("mensagem", "Vaga apagada com sucesso.");
        return json.toString();
    }

    public String falhaApagarVaga() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "apagarVaga");
        json.addProperty("status", 422);
        json.addProperty("mensagem", "Nao foi possivel apagar a vaga.");
        return json.toString();
    }

    public String listarVagas(Vaga[] vagas) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "listarVagas");
        JsonArray vagasArray = new JsonArray();
        for (Vaga vaga : vagas) {
            JsonObject vagaJson = new JsonObject();
            vagaJson.addProperty("nome", vaga.getNome());
            vagaJson.addProperty("idVaga", vaga.getCod());
            vagasArray.add(vagaJson);
        }
        json.add("vagas", vagasArray);
        System.out.println(json.toString());
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status 201");
        return json.toString();
    }

    public String filtrarVagas(Vaga[] vagas) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "filtrarVagas");
        JsonArray vagasArray = new JsonArray();
        for (Vaga vaga : vagas) {
            JsonObject vagaJson = new JsonObject();
            vagaJson.addProperty("idVaga", vaga.getCod());
            vagaJson.addProperty("nomeVaga", vaga.getNome());
            vagaJson.addProperty("faixaSalarial", vaga.getFaixaSalarial());
            vagaJson.addProperty("descricao", vaga.getDescricao());
            vagaJson.addProperty("estado", vaga.getCod());
            vagaJson.addProperty("competencias", vaga.getCod());
            vagaJson.addProperty("email", vaga.getEmail());
            vagasArray.add(vagaJson);
        }
        json.addProperty("status", "201");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status 201");
        return json.toString();
    }

    //compex
    public String sucessoCadastroCompex() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarCompetenciaExperiencia");
        json.addProperty("status", 201);
        json.addProperty("mensagem", "Competencia/Experiencia cadastrada com sucesso");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaCadastroCompex(String mensagem) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "cadastrarCompetenciaExperiencia");
        json.addProperty("status", 422);
        json.addProperty("mensagem", mensagem);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoVisualizarCompex(Compex[] compexes) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarCompetenciaExperiencia");
        JsonArray compexArray = new JsonArray();
        for (Compex compex : compexes) {
            JsonObject compexJson = new JsonObject();
            compexJson.addProperty("competencia", compex.getTitulo());
            compexJson.addProperty("experiencia", compex.getExperiencia());
            compexArray.add(compexJson);
        }
        json.add("competenciaExperiencia", compexArray);
        json.addProperty("status", 201);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status 201");
        return json.toString();
    }

    public String falhaVisualizarCompex(String mensagem) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "visualizarCompex");
        json.addProperty("status", 422);
        json.addProperty("mensagem", mensagem);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoAtualizarCompex() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "atualizarCompetenciaExperiencia");
        json.addProperty("status", 201);
        json.addProperty("mensagem", "Competencia/Experiencia atualizada com sucesso.");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaAtualizarCompex(String mensagem) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "atualizarCompetenciaExperiencia");
        json.addProperty("status", 422);
        json.addProperty("mensagem", "Nao foi possivel atualizar a competencia/experiencia. " + mensagem);
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String sucessoApagarCompex() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "apagarCompetenciaExperiencia");
        json.addProperty("status", 200);
        json.addProperty("mensagem", "Competencia/Experiencia apagada com sucesso.");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    public String falhaApagarCompex() {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", "apagarCompetenciaExperiencia");
        json.addProperty("status", 422);
        json.addProperty("mensagem", "Nao foi possivel apagar as competencias especificadas.");
        System.out.println("CompiladorJsonServidor retornou operacao " + json.get("operacao") + " com status " + json.get("status"));
        return json.toString();
    }

    //generico
    public String erroGenerico(String operacao, String mensagem) {
        JsonObject json = new JsonObject();
        json.addProperty("operacao", operacao);
        json.addProperty("status", 401);
        json.addProperty("mensagem", mensagem);
        System.out.println("CompiladorJsonServidor retornou erro generico para operacao " + json.get("operacao"));
        return json.toString();
    }
}
