package com.emojichallenges.game.payloads;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ChallengePayload {

    @NotBlank
    private String name;

    @NotBlank
    private String solution;

    private List<Long> emojis;
}
