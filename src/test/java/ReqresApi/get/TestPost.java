package ReqresApi.get;

import InputData.Metods;
import InputData.User;
import io.restassured.mapper.ObjectMapperType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static InputData.Specification.requestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestPost {

    @Test(description = "Тестирование запроса Post с проверкой статус кода и key/value по полям name,job")
    public void createUser(){
        given()
                .spec(requestSpecification())
                .body(new User("morpheus","leader"))
                .post(Metods.TestConfig.PathCreateUser.value)
                .then()
                .statusCode(201)
                .assertThat()
                .body("name",equalTo("morpheus"))
                .body("jop",equalTo("leader"))
                .log().all();
    }
    @Test(description = "Авторизация на сайте и получение token")
    public void loginSuccessful(){
        given()
                .spec(requestSpecification())
                .body(new User.AvtorisationUser("eve.holt@reqres.in","cityslicka"))
                .post(Metods.TestConfig.PathLogin.value)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().all();
    }
}
