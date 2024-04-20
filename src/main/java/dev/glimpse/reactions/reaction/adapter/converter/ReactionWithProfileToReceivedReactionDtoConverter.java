package dev.glimpse.reactions.reaction.adapter.converter;

import dev.glimpse.reactions.reaction.presentation.dto.ReactionSenderDto;
import dev.glimpse.reactions.reaction.presentation.dto.ReceivedReactionDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;

@Component
public class ReactionWithProfileToReceivedReactionDtoConverter implements Converter<ReactionWithUserProfile, ReceivedReactionDto> {

    private static ReceivedReactionDto.StateEnum convertState(ReactionWithUserProfile source) {
        return ReceivedReactionDto.StateEnum.valueOf(source.reaction().getState().toString());
    }

    @Override
    public ReceivedReactionDto convert(ReactionWithUserProfile source) {
        return new ReceivedReactionDto()
                .id(source.reaction().getId().getValue())
                .sender(convertReactionSenderDto(source))
                .comment(source.reaction().getComment().getValue())
                .promptId(source.reaction().getPromptId().getValue())
                .sentAt(source.reaction().getSentAt().atOffset(ZoneOffset.UTC))
                .state(convertState(source));
    }

    private ReactionSenderDto convertReactionSenderDto(ReactionWithUserProfile source) {
        return new ReactionSenderDto()
                .id(source.profile().userId().getValue())
                .firstName(source.profile().firstName())
                .secondName(source.profile().secondName())
                .avatarLink(source.profile().avatarLink().toString());
    }
}
