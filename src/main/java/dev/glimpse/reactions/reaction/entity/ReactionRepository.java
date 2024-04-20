package dev.glimpse.reactions.reaction.entity;

import dev.glimpse.reactions.user.entity.UserId;

import java.util.List;
import java.util.Optional;

public interface ReactionRepository {

    void save(Reaction reaction);

    Optional<Reaction> find(ReactionId id);

    List<Reaction> findAll(UserId recipientId, ReactionState state);

}
