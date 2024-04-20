package dev.glimpse.reactions.reaction.adapter;

import dev.glimpse.reactions.message.entity.MessageContent;
import dev.glimpse.reactions.prompt.entity.PromptId;
import dev.glimpse.reactions.reaction.adapter.converter.ReactionWithUserProfile;
import dev.glimpse.reactions.reaction.entity.Reaction;
import dev.glimpse.reactions.reaction.entity.ReactionComment;
import dev.glimpse.reactions.reaction.entity.ReactionId;
import dev.glimpse.reactions.reaction.presentation.api.ReactionApi;
import dev.glimpse.reactions.reaction.presentation.dto.ReactionDto;
import dev.glimpse.reactions.reaction.presentation.dto.ReceivedReactionDto;
import dev.glimpse.reactions.reaction.presentation.dto.ReplyToReactionRequestDto;
import dev.glimpse.reactions.reaction.presentation.dto.SendingReactionDto;
import dev.glimpse.reactions.reaction.usecase.CreatingReactionUseCase;
import dev.glimpse.reactions.reaction.usecase.FindingActiveReactionsUseCase;
import dev.glimpse.reactions.reaction.usecase.ReplyingToReactionUseCase;
import dev.glimpse.reactions.reaction.usecase.SkipReactionUseCase;
import dev.glimpse.reactions.user.adapter.FindingUserProfilesUseCase;
import dev.glimpse.reactions.user.entity.UserId;
import dev.glimpse.reactions.user.entity.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReactionApiImpl implements ReactionApi {

    private final CreatingReactionUseCase creatingReactionUseCase;
    private final FindingActiveReactionsUseCase findingActiveReactionsUseCase;
    private final ReplyingToReactionUseCase replyingToReactionUseCase;
    private final SkipReactionUseCase skipReactionUseCase;
    private final FindingUserProfilesUseCase findingUserProfilesUseCase;
    private final ConversionService conversionService;

    @Override
    public ResponseEntity<List<ReceivedReactionDto>> getReactions(UUID id) {
        List<Reaction> reactions = findingActiveReactionsUseCase.execute(UserId.of(id));
        Set<UserId> ids = reactions.stream().map(Reaction::getSenderId).collect(Collectors.toSet());
        Map<UserId, UserProfile> profiles = findingUserProfilesUseCase.execute(ids);

        return ResponseEntity.ok(reactions.stream()
                .map(reaction -> new ReactionWithUserProfile(reaction, profiles.getOrDefault(reaction.getSenderId(), null)))
                .map(reactionWithUserProfile -> conversionService.convert(reactionWithUserProfile, ReceivedReactionDto.class))
                .toList());
    }

    @Override
    public ResponseEntity<Void> replyToReaction(UUID id, UUID reactionId, ReplyToReactionRequestDto replyToReactionRequestDto) {
        replyingToReactionUseCase.execute(ReactionId.of(reactionId), UserId.of(id), MessageContent.of(replyToReactionRequestDto.getMessage()));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ReactionDto> sendReaction(UUID id, SendingReactionDto sendingReactionDto) {
        UserId senderId = UserId.of(id);
        UserId recipientId = UserId.of(sendingReactionDto.getRecepientId());
        ReactionComment comment = ReactionComment.of(sendingReactionDto.getComment());
        PromptId promptId = PromptId.of(sendingReactionDto.getPromptId());
        Reaction reaction = creatingReactionUseCase.execute(senderId, recipientId, comment, promptId);
        return ResponseEntity.ok(conversionService.convert(reaction, ReactionDto.class));
    }

    @Override
    public ResponseEntity<Void> skipReaction(UUID id, UUID reactionId) {
        skipReactionUseCase.execute(ReactionId.of(reactionId), UserId.of(id));
        return ResponseEntity.ok().build();
    }
}
