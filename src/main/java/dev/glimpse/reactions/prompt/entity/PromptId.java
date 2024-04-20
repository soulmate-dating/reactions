package dev.glimpse.reactions.prompt.entity;

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
public class PromptId implements Serializable {

    @Column(name = "prompt_id")
    private UUID value;

    public static PromptId of(UUID id) {
        return new PromptId(id);
    }

}
