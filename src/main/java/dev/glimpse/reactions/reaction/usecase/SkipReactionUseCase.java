package dev.glimpse.reactions.reaction.usecase;

import dev.glimpse.reactions.reaction.entity.Reaction;
import dev.glimpse.reactions.reaction.entity.ReactionId;
import dev.glimpse.reactions.reaction.entity.ReactionRepository;
import dev.glimpse.reactions.user.entity.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SkipReactionUseCase {

    private final ReactionRepository reactionRepository;

    @Transactional
    public void execute(ReactionId reactionId, UserId userId) {
        reactionRepository.find(reactionId)
                .ifPresentOrElse(Reaction::skip,
                        () -> {
                            throw new IllegalArgumentException("Reaction not found");
                        });
    }

}
