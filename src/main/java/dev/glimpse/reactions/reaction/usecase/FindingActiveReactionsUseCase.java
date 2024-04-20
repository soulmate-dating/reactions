package dev.glimpse.reactions.reaction.usecase;

import dev.glimpse.reactions.reaction.entity.Reaction;
import dev.glimpse.reactions.reaction.entity.ReactionRepository;
import dev.glimpse.reactions.reaction.entity.ReactionState;
import dev.glimpse.reactions.user.entity.UserId;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindingActiveReactionsUseCase {

    private final ReactionRepository reactionRepository;

    public List<Reaction> execute(@NonNull UserId recipientId) {
        return reactionRepository.findAll(recipientId, ReactionState.ACTIVE);
    }

}
