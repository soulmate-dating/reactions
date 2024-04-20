package dev.glimpse.reactions.reaction.infrastructure;

import dev.glimpse.reactions.reaction.entity.Reaction;
import dev.glimpse.reactions.reaction.entity.ReactionId;
import dev.glimpse.reactions.reaction.entity.ReactionState;
import dev.glimpse.reactions.user.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaReactionRepository extends JpaRepository<Reaction, ReactionId> {

    List<Reaction> findAllByRecipientIdAndState(UserId recipientId, ReactionState state);

}
