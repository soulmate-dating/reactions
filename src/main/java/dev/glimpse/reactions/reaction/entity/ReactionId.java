package dev.glimpse.reactions.reaction.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReactionId implements Serializable {

    @Column(name = "id")
    private UUID value;

    public static ReactionId of() {
        return new ReactionId(UUID.randomUUID());
    }

    public static ReactionId of(UUID id) {
        return new ReactionId(id);
    }

}
