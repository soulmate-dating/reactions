package dev.glimpse.reactions.reaction.adapter.converter;

import dev.glimpse.reactions.reaction.entity.Reaction;
import dev.glimpse.reactions.user.entity.UserProfile;

public record ReactionWithUserProfile(
        Reaction reaction,
        UserProfile profile
) {
}
