package dev.glimpse.reactions.reaction.usecase;

import dev.glimpse.reactions.prompt.entity.PromptId;
import dev.glimpse.reactions.reaction.entity.Reaction;
import dev.glimpse.reactions.reaction.entity.ReactionComment;
import dev.glimpse.reactions.reaction.entity.ReactionRepository;
import dev.glimpse.reactions.user.entity.UserId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatingReactionUseCase {

    private final ReactionRepository reactionRepository;

    public Reaction execute(@NonNull UserId senderId,
                            @NonNull UserId recipientId,
                            ReactionComment comment,
                            @NonNull PromptId promptId) {
        Reaction reaction = Reaction.builder()
                .senderId(senderId)
                .recipientId(recipientId)
                .comment(comment)
                .promptId(promptId)
                .build();
        reactionRepository.save(reaction);
        return reaction;
    }

}
