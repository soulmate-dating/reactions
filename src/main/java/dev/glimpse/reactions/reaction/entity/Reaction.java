package dev.glimpse.reactions.reaction.entity;

import dev.glimpse.reactions.prompt.entity.PromptId;
import dev.glimpse.reactions.user.entity.UserId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "reactions", schema = "reactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reaction {

    @EmbeddedId
    private ReactionId id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "sender_id"))
    private UserId senderId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "recipient_id"))
    private UserId recipientId;

    @Embedded
    private ReactionComment comment;

    @Embedded
    private PromptId promptId;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private ReactionState state;

    @Builder
    private Reaction(UserId senderId, UserId recipientId, ReactionComment comment, PromptId promptId) {
        this.id = ReactionId.of();
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.comment = comment;
        this.promptId = promptId;
        this.state = ReactionState.ACTIVE;
        this.sentAt = LocalDateTime.now();
    }

    public void skip() {
        if (this.state != ReactionState.ACTIVE) {
            throw new IllegalStateException("Cannot skip a reaction that is not active");
        }
        this.state = ReactionState.SKIPPED;
    }

    public void reply(ReactionComment comment) {
        if (this.state != ReactionState.ACTIVE) {
            throw new IllegalStateException("Cannot reply to a reaction that is not active");
        }
        this.state = ReactionState.REPLIED;
        this.comment = comment;
    }

}
