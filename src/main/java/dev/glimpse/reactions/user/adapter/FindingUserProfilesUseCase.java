package dev.glimpse.reactions.user.adapter;

import dev.glimpse.reactions.user.entity.UserId;
import dev.glimpse.reactions.user.entity.UserProfile;

import java.util.Map;
import java.util.Set;

public interface FindingUserProfilesUseCase {

    Map<UserId, UserProfile> execute(Set<UserId> ids);

}
