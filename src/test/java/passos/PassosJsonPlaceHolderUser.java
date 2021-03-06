package passos;

import apis.ApiHeaders;
import apis.ApiRequest;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.json.JSONObject;
import usuario.JsonPlaceHolderConstructor;
import usuario.JsonPlaceHolderGetSet;
import usuario.JsonPlaceHolderLombok;
import utils.UtilsJson;
import utils.UtilsProperties;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PassosJsonPlaceHolderUser extends ApiRequest {
    UtilsProperties prop = new UtilsProperties();
    ApiHeaders apiHeaders = new ApiHeaders();
    JsonPlaceHolderLombok usuarioJPHLombok;
    UtilsJson utilsJson = new UtilsJson();

    JsonPlaceHolderConstructor jsonPlaceHolderConstructor;
    JsonPlaceHolderGetSet jsonPlaceHolderGetSet = new JsonPlaceHolderGetSet();

    Faker faker = new Faker();
    //---------chave de confirmação da Api---
    @Dado("que tenho acesso a JsonPlaceHolder")
    public void queTenhoAcessoAJsonPlaceHolder() {
        System.out.println("Api não requer chave");
    }

    //---------Usuario Criado----------------
    @E("tenho um usuario criado")
    public void tenhoUmUsuarioCriado() {
        enviarUmaRequisiçaoComDadosValidos();

    }


    //--------------Posts-------------------

    @Quando("enviar uma requisiçao com dados validos")
    public void enviarUmaRequisiçaoComDadosValidos() {
        super.uri = prop.getProp("uri_jasonPlace_users");
        super.headers = apiHeaders.jsonPlasceHolderHeader();
        usuarioJPHLombok = JsonPlaceHolderLombok.builder()
                .name(faker.name().fullName()).email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().cellPhone())
                .bs("mambo jambo").catchphrase("build to re-build").city(faker.address().city())
                .lat("00-222222").lng("-223-33333")
                .suite("234").website(faker.internet().domainName()).zipcode(faker.address().zipCode())
                .username(faker.funnyName().name()).street(faker.address().streetAddress())
                .companyName("rogerio")
                .build();

        super.body = new JSONObject(new Gson().toJson(usuarioJPHLombok));
        super.POST();

    }





    @E("envio dados validos de um usuario usando um constructor")
    public void envioDadosValidosDeUmUsuarioUsandoUmConstructor() {
        super.uri = prop.getProp("uri_jasonPlace_users");
        super.headers = apiHeaders.jsonPlasceHolderHeader();
        jsonPlaceHolderConstructor = new JsonPlaceHolderConstructor(11,faker.name().firstName(),faker.funnyName().name(),
                faker.internet().emailAddress(),faker.address().streetAddress(),"apto 543",
                faker.address().city(),faker.address().zipCode(),faker.funnyName().name()+".LTDA",
                faker.number().digit(),faker.number().digit(),faker.phoneNumber().cellPhone(),faker.internet().domainName(),
                "build em'up, take em'down",faker.funnyName().name());
        super.body = jsonPlaceHolderConstructor.getJson();
        super.POST();

    }

    @E("envio dados validos de um usuario usando get e set")
    public void envioDadosValidosDeUmUsuarioUsandoGetESet() {
        super.uri = prop.getProp("uri_jasonPlace_users");
        super.headers = apiHeaders.jsonPlasceHolderHeader();
        jsonPlaceHolderGetSet.getName(faker.name().fullName());
        jsonPlaceHolderGetSet.setUsername(faker.funnyName().name());
        jsonPlaceHolderGetSet.setEmail(faker.internet().emailAddress());
        jsonPlaceHolderGetSet.setCity(faker.address().city());
        jsonPlaceHolderGetSet.setId(11);
        jsonPlaceHolderGetSet.setCatchphrase("teste teste");
        jsonPlaceHolderGetSet.setBs("teste teste");
        jsonPlaceHolderGetSet.setCompanyName(faker.name().firstName()+".ltda");
        jsonPlaceHolderGetSet.setLat(faker.number().digits(4));
        jsonPlaceHolderGetSet.setLng(faker.number().digits(4));
        jsonPlaceHolderGetSet.setPhone(faker.phoneNumber().cellPhone());
        jsonPlaceHolderGetSet.setStreet(faker.address().streetAddress());
        jsonPlaceHolderGetSet.setSuite("apt 234");
        jsonPlaceHolderGetSet.setZipcode(faker.address().zipCode());
        jsonPlaceHolderGetSet.setWebsite(faker.internet().domainName());
        super.body = jsonPlaceHolderGetSet.getjson();
        super.POST();

    }

    @E("envio dados validos de um usuario usando um Json")
    public void envioDadosValidosDeUmUsuarioUsandoUmJson() throws IOException {
        super.uri = prop.getProp("uri_jasonPlace_users");
        super.headers = apiHeaders.jsonPlasceHolderHeader();
        super.body = utilsJson.parseJsonFile("JsonPlaceHolderCriaçãoDeUsuario");
        super.POST();

    }



    //--------------Gets--------------------
    //--Get da APi
    //--Get de Usuario Criado


    @Quando("realizar buscas de {string}")
    public void realizarBuscasDe(String idUsuario){
        super.uri = prop.getProp("uri_jasonPlace_users")+idUsuario;
        super.headers = apiHeaders.jsonPlasceHolderHeader();
        super.body = new JSONObject();
        super.GET();

    }



    //--------------Puts--------------------
    @Quando("realizar buscas de {string} e altera seu respectivos {string}")
    public void realizarBuscasDeEAlteraSeuRespectivos(String idUsuario, String nomeAlterado) {
        super.uri = prop.getProp("uri_jasonPlace_users")+idUsuario;
        super.headers = apiHeaders.jsonPlasceHolderHeader();
        super.body.put("name", nomeAlterado);
        super.PUT();
    }

    //--------------Patchs------------------

    @Quando("Alterar o nome da companhia para {string} do {string}")
    public void alterarONomeDaCompanhiaParaDo(String empresaNova, String idUsuario) {
        super.uri = prop.getProp("uri_jasonPlace_users")+idUsuario;
        super.headers = apiHeaders.jsonPlasceHolderHeader();
        usuarioJPHLombok.setCompanyName(empresaNova);
        super.body = new JSONObject("{\"companyName\":\""+ empresaNova+"\"}");
        super.PATCH();
    }




    //--------------Deletes-----------------
    @Quando("deletar um {string}")
    public void deletarUm(String idDelete) {
        super.uri = prop.getProp("uri_jasonPlace_users")+idDelete;
        super.headers = apiHeaders.jsonPlasceHolderHeader();
        super.DELETE();
    }
    //--------validacoes--------------------

     @Então("garantir a devolutiva de status {int}")
        public void garantirADevolutivaDeStatus(Integer statusCodeEsperado) {
            assertEquals(statusCodeEsperado,response.statusCode());
        }


    @Então("devo validar do corpo recebido e o corpo enviado")
    public void devoValidarDoCorpoRecebidoEOCorpoEnviado() {
        JSONObject corpoRetorno = new JSONObject(response.getBody().asString());
        assertEquals(body.toString(), corpoRetorno.toString());

    }


    @Então("devo validar se o {string} foi trocado")
    public void devoValidarSeOFoiTrocado(String nomeEsperado) {
        assertEquals(nomeEsperado,response.jsonPath().getJsonObject("companyName"));

    }


    @Então("devo validar do corpo recebido, os {string}")
    public void devoValidarDoCorpoRecebidoOs(String nomesRecebidos) {
        assertEquals(nomesRecebidos,response.jsonPath().getJsonObject("name"));
    }


    @Então("devo validar do corpo recebido, os {string} alterados")
    public void devoValidarDoCorpoRecebidoOsAlterados(String nomesAlterados) {
        assertEquals(nomesAlterados, response.jsonPath().getJsonObject("name"));
    }


    @Então("devo validar o corpo do delete")
    public void devoValidarOCorpoDoDelete() {
        JSONObject corpoRetorno = new JSONObject(response.getBody().asString());
        assertEquals("{}",corpoRetorno.toString());
    }


    @Então("devo validar o corpo enviado e o corpo recebido")
    public void devoValidarOCorpoEnviadoEOCorpoRecebido() {
        JSONObject corpoRetorno = new JSONObject(response.getBody().asString());
        assertEquals(body.toString(),corpoRetorno.toString());
    }



}
