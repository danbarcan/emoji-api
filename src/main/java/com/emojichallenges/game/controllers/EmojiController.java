package com.emojichallenges.game.controllers;

import com.emojichallenges.game.entities.Emoji;
import com.emojichallenges.game.payloads.ApiResponse;
import com.emojichallenges.game.payloads.EmojiPayload;
import com.emojichallenges.game.repositories.ChallengeRepository;
import com.emojichallenges.game.repositories.EmojiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class EmojiController {
    @Autowired
    EmojiRepository emojiRepository;

    @Autowired
    ChallengeRepository challengeRepository;

    @GetMapping("/emoji")
    public ResponseEntity<Emoji> getEmojiById(@RequestParam Long id) {
        Optional<Emoji> emoji = emojiRepository.findById(id);

        return ResponseEntity.of(emoji);
    }

    @GetMapping("/emojiByName")
    public ResponseEntity<Emoji> getEmojiByName(@RequestParam String name) {
        Optional<Emoji> emoji = emojiRepository.findByName(name);

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

    @GetMapping("/loadEmojis")
    public ResponseEntity<ApiResponse> loadEmojis() throws IOException {
        File emojis = new ClassPathResource("emojis.csv").getFile();
        challengeRepository.deleteAll();
        emojiRepository.deleteAll();
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(emojis.getAbsolutePath()))) {

            stream.forEach(line -> {
                emojiRepository.save(Emoji.builder().name(line.substring(0, line.lastIndexOf(';')))
                        .unicode(line.substring(line.lastIndexOf(';') + 1, line.length() - 3))
                        .build());
            });

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse(false, "Err parsing csv file"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "File successfully parsed"));
    }
}
