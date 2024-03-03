package ReqresApi.get;

import InputData.Metods;
import InputData.User;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import static InputData.Specification.requestSpecification;
import static io.restassured.RestAssured.given;

public class TestDelete {

    @Test(description ="Тестирование запроса Delete с удалением пользователя")
    public void deleteUser(){
        int id;
        id = Integer.parseInt(given()
                .spec(requestSpecification())
                .body(new User("triniti","girl"))
                .post(Metods.TestConfig.PathCreateUser.value)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .response()
                .body()
                .path("id"));

        given()
                .spec(requestSpecification())
                .delete(Metods.TestConfig.PathPutDeleteUser.value + id)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .log().all();
    }
}
