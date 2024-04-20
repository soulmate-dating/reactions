package dev.glimpse.reactions.user.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserId implements Serializable {

    private UUID value;

    public static UserId of(UUID id) {
        return new UserId(id);
    }

}
