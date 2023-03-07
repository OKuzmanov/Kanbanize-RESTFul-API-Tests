package com.kanbanize.tests;

import com.kanbanize.entities.rest.api.CardRestApi;
import com.kanbanize.pojos.CardPojo;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class UpdateCardTests extends BaseTest {

    @Test
    public void test_updateCard_ChangeColumn_ValidData() {
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

        int newColumnId = 3;
        JsonPath updateCardJsp = CardRestApi.updateCardColumn( createCardPojo.getCardId(),newColumnId);

        CardPojo updatedCardPojo = updateCardJsp.getObject("data[0]", CardPojo.class);

        Assert.assertEquals(updatedCardPojo.getColumnId(), newColumnId);
    }

    @Test
    public void test_updateCard_ChangeColumn_InValidColumnId() {
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

        int newColumnId = 10 + new Random().nextInt(1000);
        JsonPath errorUpdateCardJsp = CardRestApi.updateCardInvalidColumn(createCardPojo.getCardId(), newColumnId);

        String errorMsg = "A column with id " + newColumnId + " does not exist.";
        Assert.assertEquals(errorUpdateCardJsp.getString("error.message"), errorMsg);
    }

    @Test
    public void test_updateCard_ChangeSize_NonExistingCard() {
        int invalidId = 10000 + new Random().nextInt(1000000);
        int newSize = 50;

        JsonPath errorUpdateCardJsp = CardRestApi.updateCardSizeInvalidCardId(invalidId, newSize);

        String errorMsg = "A card with id " + invalidId + " does not exist.";
        Assert.assertEquals(errorUpdateCardJsp.getString("error.message"), errorMsg);
    }
}
