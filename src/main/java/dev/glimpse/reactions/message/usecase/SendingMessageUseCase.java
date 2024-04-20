package dev.glimpse.reactions.message.usecase;

import dev.glimpse.reactions.message.entity.MessageContent;
import dev.glimpse.reactions.reaction.entity.ReactionId;
import dev.glimpse.reactions.user.entity.UserId;

public interface SendingMessageUseCase {

    void execute(UserId userId, UserId companionId, MessageContent content, ReactionId reactionId);

}
