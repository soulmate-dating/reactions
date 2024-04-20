package dev.glimpse.reactions.reaction.infrastructure;

import dev.glimpse.reactions.reaction.entity.Reaction;
import dev.glimpse.reactions.reaction.entity.ReactionId;
import dev.glimpse.reactions.reaction.entity.ReactionRepository;
import dev.glimpse.reactions.reaction.entity.ReactionState;
import dev.glimpse.reactions.user.entity.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ReactionRepositoryImpl implements ReactionRepository {

    private final JpaReactionRepository jpaReactionRepository;

    @Override
    public void save(Reaction reaction) {
        jpaReactionRepository.save(reaction);
    }

    @Override
    public Optional<Reaction> find(ReactionId id) {
        return jpaReactionRepository.findById(id);
    }

    @Override
    public List<Reaction> findAll(UserId recipientId, ReactionState state) {
        return jpaReactionRepository.findAllByRecipientIdAndState(recipientId, state);
    }
}
