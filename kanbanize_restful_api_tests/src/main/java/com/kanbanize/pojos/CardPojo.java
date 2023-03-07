package com.kanbanize.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardPojo {
    @JsonProperty("card_id")
    private int cardId;

    @JsonProperty("board_id")
    private int boardId;

    @JsonProperty("workflow_id")
    private int workflowId;

    private String title;

    @JsonProperty("owner_user_id")
    private int ownerId;

    private String color;

    @JsonProperty("column_id")
    private int columnId;

    @JsonProperty("lane_id")
    private int laneId;

    private int position;

    private int size;

    private int priority;

    private String description;

    private String deadline;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public int getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(int workflowId) {
        this.workflowId = workflowId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
    }

    public int getLaneId() {
        return laneId;
    }

    public void setLaneId(int laneId) {
        this.laneId = laneId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "CardPojo{" +
                "cardId=" + cardId +
                ", boardId=" + boardId +
                ", workflowId=" + workflowId +
                ", title='" + title + '\'' +
                ", ownerId=" + ownerId +
                ", color='" + color + '\'' +
                ", columnId=" + columnId +
                ", laneId=" + laneId +
                ", position=" + position +
                ", size=" + size +
                ", priority=" + priority +
                ", description='" + description + '\'' +
                ", deadline='" + deadline + '\'' +
                '}';
    }
}
