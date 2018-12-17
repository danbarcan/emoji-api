package com.emojichallenges.game.payloads;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EmojiPayload {

    @NotBlank
    private String name;
}
