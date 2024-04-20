package dev.glimpse.reactions.reaction.adapter.converter;

import dev.glimpse.reactions.reaction.entity.Reaction;
import dev.glimpse.reactions.reaction.presentation.dto.ReactionDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;

@Component
public class ReactionToReactionDtoConverter implements Converter<Reaction, ReactionDto> {

    @Override
    public ReactionDto convert(Reaction source) {
        return new ReactionDto()
                .id(source.getId().getValue())
                .senderId(source.getSenderId().getValue())
                .recipientId(source.getRecipientId().getValue())
                .comment(source.getComment().getValue())
                .promptId(source.getPromptId().getValue())
                .sentAt(source.getSentAt().atOffset(ZoneOffset.UTC))
                .state(ReactionDto.StateEnum.valueOf(source.getState().toString()));
    }
}
