package com.kanbanize.utils;

public class JsonPayload {

    public static String createCard(String title, int colId, int laneId, int position, int ownerId, int size, int priority, String color, String description, String deadline) {
        return "{" +
                "    \"title\": \"" + title + "\"," +
                "    \"column_id\": " + colId + "," +
                "    \"lane_id\": " + laneId + "," +
                "    \"position\": " + position + "," +
                "    \"owner_user_id\": " + ownerId + "," +
                "    \"size\": " + size + "," +
                "    \"priority\": " + priority + "," +
                "    \"color\": \"" + color + "\"," +
                "    \"description\": \"" + description + "\"," +
                "    \"deadline\": \"" + deadline + "\"" +
                "}";
    }

    public static String updateCardColumn(int newColumnId) {
        return "{" +
                "    \"column_id\": " + newColumnId +
                "}";
    }

    public static String addSubtask(String description, int ownerId) {
        return "{" +
                "  \"description\": \"" + description + "\"," +
                "  \"owner_user_id\": " + ownerId +
                "}";
    }

    public static String updateCardConvertSubtaskToChildCard(int subtaskId, int cardId, String title, String description, int ownerId,
                                                             int priority, String color, int position, int columnId, int laneId) {
        return "{" +
                "    \"subtasks_to_convert_into_cards\": [" +
                "        {" +
                "            \"subtask_id\": \"" + subtaskId + "\"," +
                "            \"title\": \"" + title + "\"," +
                "            \"description\": \"" + description + "\"," +
                "            \"owner_user_id\": " + ownerId + "," +
                "            \"priority\": " + priority + "," +
                "            \"color\": \"" + color + "\"," +
                "            \"position\": " + position + "," +
                "            \"column_id\": " + columnId + "," +
                "            \"lane_id\": " + laneId + "," +
                "            \"links_to_existing_cards_to_add_or_update\": [" +
                "                {" +
                "                    \"linked_card_id\": \"" + cardId + "\"," +
                "                    \"link_type\": \"parent\"" +
                "                }" +
                "            ]" +
                "        }" +
                "    ]" +
                "}";
    }

    public static String updateCardSize(int newSize) {
        return "{" +
                "    \"size\": " + newSize +
                "}";
    }
}
