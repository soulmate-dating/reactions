package dev.glimpse.reactions.reaction.usecase;

import dev.glimpse.reactions.message.entity.MessageContent;
import dev.glimpse.reactions.message.usecase.SendingMessageUseCase;
import dev.glimpse.reactions.reaction.entity.Reaction;
import dev.glimpse.reactions.reaction.entity.ReactionComment;
import dev.glimpse.reactions.reaction.entity.ReactionId;
import dev.glimpse.reactions.reaction.entity.ReactionRepository;
import dev.glimpse.reactions.user.entity.UserId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ReplyingToReactionUseCase {

    private final ReactionRepository reactionRepository;
    private final SendingMessageUseCase sendingMessageUseCase;

    @Transactional
    public void execute(@NonNull ReactionId reactionId, @NonNull UserId userId, @NonNull MessageContent messageContent) {
        Reaction reaction = reactionRepository.find(reactionId)
                .orElseThrow(() -> new IllegalArgumentException("Reaction not found"));

        sendingMessageUseCase.execute(reaction.getSenderId(), userId, MessageContent.of(reaction.getComment().getValue()), reactionId);
        sendingMessageUseCase.execute(userId, reaction.getSenderId(), messageContent, reactionId);

        reaction.reply(ReactionComment.of(messageContent.value()));
    }

}
