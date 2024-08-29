package tests;

import api.TrelloApi;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloApiTests {

    TrelloApi trelloApi = new TrelloApi();

    @Test
    public void trelloApiTest() {

        // Board oluştur
        Response createBoardResponse = trelloApi.createBoard("NewTestBoard");
        String boardId = createBoardResponse.jsonPath().getString("id");
        System.out.println("\nBoard ID: " + boardId);

        String boardResponseBody = createBoardResponse.getBody().asString();
        System.out.println("\nBoard Response Body: " + boardResponseBody);

        Response createListResponse = trelloApi.createList("NewTestList", boardId);
        String listId = createListResponse.jsonPath().getString("id");
        System.out.println("\nlistId = " + listId);

        String listResponseBody = createListResponse.getBody().asString();
        System.out.println("\nList Response Body: " + listResponseBody);


        // İki kart oluşturma
        Response createCard1Response = trelloApi.createCard(listId, "NewCard1");
        System.out.println("\ncreateCard1Response = " + createCard1Response.asString());
        String cardId1 = createCard1Response.jsonPath().getString("id");
        System.out.println("\ncardId1 = " + cardId1);

        Response createCard2Response = trelloApi.createCard(listId, "NewCard2");
        System.out.println("\ncreateCard1Response = " + createCard1Response.asString());
        String cardId2 = createCard2Response.jsonPath().getString("id");
        System.out.println("\ncardId2 = " + cardId2);

        // Kartlardan birini güncelleme
        String cardToUpdate = new Random().nextBoolean() ? cardId1 : cardId2;
        Response updateCardResponse = trelloApi.updateCard(cardToUpdate, "Updated Card");
        System.out.println("\nCard Successfuly updated");
        assertEquals(200, updateCardResponse.statusCode());

        // Kartları silme
        Response deleteCard1Response = trelloApi.deleteCard(cardId1);
        System.out.println("\nCard Successfuly deleted");
        Response deleteCard2Response = trelloApi.deleteCard(cardId2);
        System.out.println("\nCard Successfuly deleted");

        assertEquals(200, deleteCard1Response.statusCode());
        assertEquals(200, deleteCard2Response.statusCode());

        // Board'u silme
        Response deleteBoardResponse = trelloApi.deleteBoard(boardId);
        System.out.println("\nBoard Successfuly deleted");
        assertEquals(200, deleteBoardResponse.statusCode());
    }
}
