package ReqresApi.get;

import InputData.Metods;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import java.io.File;
import java.net.http.HttpClient;

import static InputData.Specification.requestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestGet {

    @Test(description = "Тестирование запроса GET с проверкой статус кода и валидацией ответа по JSON схеме")
    public void listUsers (){
        File schema = new File("C:\\Users\\User\\IdeaProjects\\ApiTest\\src\\main\\resources\\listUsersSchemaGenerateJson.json");
        given()
                .spec(requestSpecification())
                .get(Metods.TestConfig.Path.value)
                .then()
                .assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(schema))
                .log().all();
    }
    @Test(description = "Тестирование запроса GET с проверкой статус кода и key/value по полям id,email,first_name,last_name")
    public void singleUser(){
        given()
               .spec(requestSpecification())
               .get(Metods.TestConfig.PathUser2.value)
               .then()
               .assertThat()
               .statusCode(HttpStatus.SC_OK)
               .body("data.id", Matchers.is(2))
               .body("data.email", Matchers.is("janet.weaver@reqres.in"))
               .body("data.first_name", Matchers.is("Janet"))
               .body("data.last_name", Matchers.is("Weaver"))
               .log().all();
    }
}
