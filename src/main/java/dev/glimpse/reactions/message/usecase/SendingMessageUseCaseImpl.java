package dev.glimpse.reactions.message.usecase;

import dev.glimpse.reactions.message.entity.MessageContent;
import dev.glimpse.reactions.message.presentation.api.MessageApiClient;
import dev.glimpse.reactions.message.presentation.dto.MessageTagDto;
import dev.glimpse.reactions.message.presentation.dto.SendMessageDto;
import dev.glimpse.reactions.reaction.entity.ReactionId;
import dev.glimpse.reactions.user.entity.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendingMessageUseCaseImpl implements SendingMessageUseCase {

    private final MessageApiClient messageApiClient;

    @Override
    public void execute(UserId userId, UserId companionId, MessageContent content, ReactionId reactionId) {
        MessageTagDto messageTagDto = new MessageTagDto(MessageTagDto.NameEnum.REACTION, reactionId.getValue());
        SendMessageDto sendMessageDto = new SendMessageDto(companionId.getValue(), content.value());
        sendMessageDto.setTag(messageTagDto);
        messageApiClient.sendMessage(userId.getValue(), sendMessageDto);
    }

}
