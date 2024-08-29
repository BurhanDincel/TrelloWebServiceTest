package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TrelloApi {

    private static final String BASE_URL = "https://api.trello.com/1";
    private static final String KEY = "313cf66e2740574fe226814e7eaec39e";
    private static final String TOKEN = "ATTA3fead77d5d0891e5e7b0f6d9b7df9a73be8fa6a322736a7c40c4306d179a79e5ED1C631A";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public Response createBoard(String boardName) {
        return given()
                .baseUri(BASE_URL)
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .contentType(ContentType.JSON)
                .body("{\"name\": \"" + boardName + "\"}")
                .post("/boards")
                .then()
                .extract().response();
    }

    public Response createList(String listName, String idBoard) {
        return given()
                .baseUri(BASE_URL)
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .contentType(ContentType.JSON)
                .body("{\"name\": \"" + listName + "\", \"idBoard\": \"" + idBoard + "\"}")
                .post("/lists")
                .then()
                .extract().response();

    }

    public Response createCard(String listId, String cardName) {
        String jsonBody = "{\"name\": \"" + cardName + "\", \"idList\": \"" + listId + "\"}";

        return RestAssured.given()
                .baseUri(BASE_URL)
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .post("/cards")
                .then()
                .extract().response();
    }

    public Response updateCard(String cardId, String newName) {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .contentType(ContentType.JSON)
                .body("{\"name\": \"" + newName + "\"}")
                .put("/cards/" + cardId)
                .then()
                .extract().response();
    }

    public Response deleteCard(String cardId) {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .delete("/cards/" + cardId)
                .then()
                .extract().response();
    }

    public Response deleteBoard(String boardId) {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .queryParam("key", KEY)
                .queryParam("token", TOKEN)
                .delete("/boards/" + boardId)
                .then()
                .extract().response();
    }
}
