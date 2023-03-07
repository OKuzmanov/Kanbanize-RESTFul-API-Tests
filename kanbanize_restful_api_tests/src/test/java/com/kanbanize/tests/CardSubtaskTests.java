package com.kanbanize.tests;

import com.kanbanize.entities.rest.api.CardRestApi;
import com.kanbanize.pojos.CardPojo;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CardSubtaskTests extends BaseTest {

    @Test
    public void test_addSubtaskAndConvertToChildCard_validData() {
        String title = "Demo Card From Rest Assured";
        int colId = 2;
        int laneId = 1;
        int position = 1;
        int ownerId = 2;
        int size = 50;
        int priority = 250;
        String color = "795548";
        String description = "A demo card created for testing purposes";
        String deadline = "2025-03-07T09:18:20Z";

        JsonPath createCardJsp = CardRestApi.createCard(title, colId, laneId, position, ownerId, size, priority, color, description, deadline);

        CardPojo createCardPojo = createCardJsp.getObject("data[0]", CardPojo.class);

        String subtaskDesc = "A demo subtask.";
        JsonPath addSubtaskJsp = CardRestApi.addSubtask(createCardPojo.getCardId(), subtaskDesc, ownerId);

        Assert.assertEquals(addSubtaskJsp.getString("data.description"), subtaskDesc);
        Assert.assertEquals(addSubtaskJsp.getInt("data.owner_user_id"), ownerId);

        int subtaskId = addSubtaskJsp.getInt("data.subtask_id");

        JsonPath convertSubtaskJsp = CardRestApi.convertSubtaskToChildCard(subtaskId, createCardPojo.getCardId(), "Card From Subtask",
                "A New Card From Subtask", ownerId, priority, color, position, colId, laneId);

        int convertedCardId = convertSubtaskJsp.getInt("data[0].linked_cards[0].card_id");

        Assert.assertEquals(convertSubtaskJsp.getString("data[0].linked_cards[0].link_type"), "child");

        JsonPath getCardJsp = CardRestApi.getCard(convertedCardId);

        CardPojo fetchedCardPojo = getCardJsp.getObject("data", CardPojo.class);

        Assert.assertEquals(fetchedCardPojo.getColumnId(), colId);
        Assert.assertEquals(fetchedCardPojo.getLaneId(), laneId);
        Assert.assertEquals(getCardJsp.getInt("data.linked_cards[0].card_id"), createCardPojo.getCardId());
        Assert.assertEquals(getCardJsp.getString("data.linked_cards[0].link_type"), "parent");

        CardRestApi.deleteCard(convertedCardId);

        JsonPath getDeletedCardJsp = CardRestApi.getCardWithError(convertedCardId);

        String errMsg = "A card with id " + convertedCardId + " does not exist.";
        Assert.assertEquals(getDeletedCardJsp.getString("error.message"), errMsg);
    }
}
