package com.gaohuan.websocket.spring;

/**
 * Created by acer on 2016/6/28.
 */
public class UserCommand {

    private String name;
    private String chatContent;
    private String coordinationId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChatContent() {
        return chatContent;
    }

    public void setChatContent(String chatContent) {
        this.chatContent = chatContent;
    }

    public String getCoordinationId() {
        return coordinationId;
    }

    public void setCoordinationId(String coordinationId) {
        this.coordinationId = coordinationId;
    }

    @Override
    public String toString() {
        return "UserCommand{" +
                "name='" + name + '\'' +
                ", chatContent='" + chatContent + '\'' +
                ", coordinationId='" + coordinationId + '\'' +
                '}';
    }
}
