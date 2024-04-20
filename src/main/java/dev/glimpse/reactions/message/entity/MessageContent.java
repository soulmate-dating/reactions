package dev.glimpse.reactions.message.entity;

public record MessageContent(
        String value
) {

    public static MessageContent of(String value) {
        return new MessageContent(value);
    }

}
