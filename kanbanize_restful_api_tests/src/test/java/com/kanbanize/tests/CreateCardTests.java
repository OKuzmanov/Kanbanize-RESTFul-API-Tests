package com.kanbanize.tests;

import com.kanbanize.entities.rest.api.CardRestApi;
import com.kanbanize.pojos.CardPojo;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateCardTests extends BaseTest {

    @Test
    public void test_createCard_ValidData() {
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

        JsonPath getCardJsp = CardRestApi.getCard(createCardPojo.getCardId());

        CardPojo fetchedCardPojo = getCardJsp.getObject("data", CardPojo.class);

        Assert.assertEquals(fetchedCardPojo.getWorkflowId(), createCardPojo.getWorkflowId());
        Assert.assertEquals(fetchedCardPojo.getLaneId(), createCardPojo.getLaneId());
        Assert.assertEquals(fetchedCardPojo.getColumnId(), createCardPojo.getColumnId());
        Assert.assertEquals(fetchedCardPojo.getPosition(), createCardPojo.getPosition());
        Assert.assertEquals(fetchedCardPojo.getColor(), createCardPojo.getColor());
        Assert.assertEquals(fetchedCardPojo.getPriority(), createCardPojo.getPriority());
    }

    @Test
    public void test_createCard_InvalidDeadline() {
        String title = "Demo Card From Rest Assured";
        int colId = 2;
        int laneId = 1;
        int position = 1;
        int ownerId = 2;
        int size = 50;
        int priority = 250;
        String color = "795548";
        String description = "A demo card created for testing purposes";
        String invalidDeadline = "1969-12-31T13:27:29.097Z";

        JsonPath createCardErrJsp = CardRestApi.createCardWithError(title, colId, laneId, position, ownerId, size, priority, color, description, invalidDeadline);

        String errMsg = "The parameters in the request body did not pass validation.";
        Assert.assertEquals(createCardErrJsp.getString("error.message"), errMsg);

        String deadlineErrMsg = "The date and time must be after 1970-01-01 00:00:00.";
        Assert.assertEquals(createCardErrJsp.getString("error.details.deadline[0]"), deadlineErrMsg);
    }
}
