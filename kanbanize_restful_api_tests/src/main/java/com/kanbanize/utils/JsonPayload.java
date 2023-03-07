package com.kanbanize.utils;

public class JsonPayload {

    public static String createCard(String title, int colId, int laneId, int position, int ownerId, int size, int priority, String color, String description, String deadline) {
        return "{\n" +
                "    \"title\": \"" + title + "\",\n" +
                "    \"column_id\": " + colId + ",\n" +
                "    \"lane_id\": " + laneId + ",\n" +
                "    \"position\": " + position + ",\n" +
                "    \"owner_user_id\": " + ownerId + ",\n" +
                "    \"size\": " + size + ",\n" +
                "    \"priority\": " + priority + ",\n" +
                "    \"color\": \"" + color + "\",\n" +
                "    \"description\": \"" + description + "\",\n" +
                "    \"deadline\": \"" + deadline + "\"\n" +
                "}";
    }

    public static String updateCardColumn(int newColumnId) {
        return "{\n" +
                "    \"column_id\": " + newColumnId + "\n" +
                "}";
    }

    public static String addSubtask(String description, int ownerId) {
        return "{\n" +
                "  \"description\": \"" + description + "\",\n" +
                "  \"owner_user_id\": " + ownerId + "\n" +
                "}";
    }

    public static String updateCardConvertSubtaskToChildCard(int subtaskId, int cardId, String title, String description, int ownerId,
                                                             int priority, String color, int position, int columnId, int laneId) {
        return "{\n" +
                "    \"subtasks_to_convert_into_cards\": [\n" +
                "        {\n" +
                "            \"subtask_id\": \"" + subtaskId + "\",\n" +
                "            \"title\": \"" + title + "\",\n" +
                "            \"description\": \"" + description + "\",\n" +
                "            \"owner_user_id\": " + ownerId + ",\n" +
                "            \"priority\": " + priority + ",\n" +
                "            \"color\": \"" + color + "\",\n" +
                "            \"position\": " + position + ",\n" +
                "            \"column_id\": " + columnId + ",\n" +
                "            \"lane_id\": " + laneId + ",\n" +
                "            \"links_to_existing_cards_to_add_or_update\": [\n" +
                "                {\n" +
                "                    \"linked_card_id\": \"" + cardId + "\",\n" +
                "                    \"link_type\": \"parent\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
    }

    public static String updateCardSize(int newSize) {
        return "{\n" +
                "    \"size\": " + newSize + "\n" +
                "}";
    }
}
