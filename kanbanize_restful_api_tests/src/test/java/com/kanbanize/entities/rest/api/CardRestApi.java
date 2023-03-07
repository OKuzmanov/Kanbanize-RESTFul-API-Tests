package com.kanbanize.entities.rest.api;

import com.kanbanize.utils.JsonPayload;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class CardRestApi extends BaseEntity {

    public static JsonPath getCard(int cardId) {
        return given()
                .pathParam("cardId", cardId)
                .when().get(GET_CARD_RESOURCE)
                .then().assertThat().statusCode(200)
                .extract().response().body().jsonPath();
    }

    public static JsonPath getCardWithError(int cardId) {
        return given()
                .pathParam("cardId", cardId)
                .when().get(GET_CARD_RESOURCE)
                .then().assertThat().statusCode(404)
                .extract().response().body().jsonPath();
    }

    public static JsonPath createCard(String title, int colId, int laneId, int position, int ownerId, int size, int priority, String color, String description, String deadline) {
        return given()
                .body(JsonPayload.createCard(title, colId, laneId, position, ownerId, size, priority, color, description, deadline))
                .when().post(CREATE_CARD_RESOURCE)
                .then().assertThat().statusCode(200)
                .extract().response().body().jsonPath();
    }

    public static JsonPath createCardWithError(String title, int colId, int laneId, int position, int ownerId, int size, int priority, String color, String description, String deadline) {
        return given()
                .body(JsonPayload.createCard(title, colId, laneId, position, ownerId, size, priority, color, description, deadline))
                .when().post(CREATE_CARD_RESOURCE)
                .then().assertThat().statusCode(400)
                .extract().response().body().jsonPath();
    }

    public static JsonPath updateCardColumn(int cardId, int newColumnId) {
        return given()
                .pathParam("cardId", cardId)
                .body(JsonPayload.updateCardColumn(newColumnId))
                .when().patch(UPDATE_CARD_RESOURCE)
                .then().assertThat().statusCode(200)
                .extract().response().body().jsonPath();
    }

    public static JsonPath updateCardInvalidColumn(int cardId, int newColumnId) {
        return given()
                .pathParam("cardId", cardId)
                .body(JsonPayload.updateCardColumn(newColumnId))
                .when().patch(UPDATE_CARD_RESOURCE)
                .then().assertThat().statusCode(400)
                .extract().response().body().jsonPath();
    }

    public static JsonPath updateCardSizeInvalidCardId(int cardId, int newSize) {
        return given()
                .pathParam("cardId", cardId)
                .body(JsonPayload.updateCardSize(newSize))
                .when().patch(UPDATE_CARD_RESOURCE)
                .then().assertThat().statusCode(404)
                .extract().response().body().jsonPath();
    }

    public static JsonPath addSubtask(int cardId, String description, int ownerId) {
        return given()
                .body(JsonPayload.addSubtask(description, ownerId))
                .pathParam("cardId", cardId)
                .when().post(ADD_SUBTASK_RESOURCE)
                .then().assertThat().statusCode(200)
                .extract().response().body().jsonPath();
    }

    public static JsonPath convertSubtaskToChildCard(int subtaskId, int cardId, String title, String description, int ownerId,
                                                     int priority, String color, int position, int columnId, int laneId) {
        return given()
                .body(JsonPayload.updateCardConvertSubtaskToChildCard(subtaskId, cardId, title,
                        description, ownerId, priority, color, position, columnId, laneId))
                .pathParam("cardId", cardId)
                .when().patch(UPDATE_CARD_RESOURCE)
                .then().assertThat().statusCode(200)
                .extract().response().body().jsonPath();
    }

    public static void deleteCard(int cardId) {
        given()
                .pathParam("cardId", cardId)
                .when().delete(DELETE_CARD_RESOURCE)
                .then().assertThat().statusCode(204);
    }
}
