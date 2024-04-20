package dev.glimpse.reactions.user.entity;

import java.net.URI;

public record UserProfile(
        UserId userId,
        String firstName,
        String secondName,
        URI avatarLink
) {
}
