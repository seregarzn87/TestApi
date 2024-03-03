package ReqresApi.get;

import InputData.Metods;
import InputData.User;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static InputData.Specification.requestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TestPut {

    @Test(description = "Тестирование запроса PUT с обновлением данных Users по полю name")
    public void updateUser(){
        String id;
        id = given()
                .spec(requestSpecification())
                .body(new User("morpheus","leader"))
                .post(Metods.TestConfig.PathCreateUser.value)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .body("name",equalTo("morpheus"))
                .body("jop",equalTo("leader"))
                .extract()
                .response()
                .body()
                .path("id");

        given()
                .spec(requestSpecification())
                .body(new User("neo","leader"))
                .post(Metods.TestConfig.PathPutDeleteUser.value + id)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .assertThat()
                .body("name",equalTo("neo"))
                .body("jop",equalTo("leader"))
                .log().all();
    }
}
