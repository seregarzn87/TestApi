package ReqresApi.get;

import InputData.Metods;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;
import java.io.File;
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
    @Test(description = "Тестирование запроса GET с проверкой статус кода и key/value по полям id,email,first_name,last_name,avatar")
    public void singleUser(){
        given()
               .spec(requestSpecification())
               .get(Metods.TestConfig.PathUser2.value)
               .then()
               .assertThat()
               .statusCode(200)
               .body("data.id", equalTo(2))
               .body("data.email", equalTo("janet.weaver@reqres.in"))
               .body("data.first_name", equalTo("Janet"))
               .body("data.last_name", equalTo("Weaver"))
               .body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"))
               .log().all();
    }
}
