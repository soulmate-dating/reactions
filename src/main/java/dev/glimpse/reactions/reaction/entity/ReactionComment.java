package dev.glimpse.reactions.reaction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ReactionComment {

    @Column(name = "comment")
    private String value;

    public static ReactionComment of(String comment) {
        return new ReactionComment(comment);
    }

}
