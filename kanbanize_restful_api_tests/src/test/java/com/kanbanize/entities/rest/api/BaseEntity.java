package com.kanbanize.entities.rest.api;

public class BaseEntity {
    protected static final String CREATE_CARD_RESOURCE = "/cards";
    protected static final String GET_CARD_RESOURCE = "/cards/{cardId}";
    protected static final String UPDATE_CARD_RESOURCE = "/cards/{cardId}";
    protected static final String DELETE_CARD_RESOURCE = "/cards/{cardId}";
    protected static final String ADD_SUBTASK_RESOURCE = "/cards/{cardId}/subtasks";
}
