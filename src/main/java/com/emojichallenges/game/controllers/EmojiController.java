package com.emojichallenges.game.controllers;

import com.emojichallenges.game.entities.Emoji;
import com.emojichallenges.game.payloads.ApiResponse;
import com.emojichallenges.game.payloads.EmojiPayload;
import com.emojichallenges.game.repositories.EmojiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class EmojiController {
    @Autowired
    EmojiRepository emojiRepository;

    @GetMapping("/emoji")
    public ResponseEntity<Emoji> getEmoji(@RequestParam Long id) {
        Optional<Emoji> emoji = emojiRepository.findById(id);

        return ResponseEntity.of(emoji);
    }

    @GetMapping("/emojis")
    public ResponseEntity<List<Emoji>> getEmojis() {
        List<Emoji> emoji = emojiRepository.findAll();

        return ResponseEntity.ok(emoji);
    }

    @PostMapping("/emoji")
    public ResponseEntity<ApiResponse> saveEmoji(@Valid @RequestBody EmojiPayload emojiPayload) {
        if (emojiRepository.existsByName(emojiPayload.getName())) {
            return new ResponseEntity(new ApiResponse(false, "Emoji name already exists!"),
                    HttpStatus.BAD_REQUEST);
        }

        emojiRepository.save(Emoji.builder()
                .name(emojiPayload.getName())
                .build());

        return ResponseEntity.ok(new ApiResponse(true, "Emoji saved successfully"));
    }
}
