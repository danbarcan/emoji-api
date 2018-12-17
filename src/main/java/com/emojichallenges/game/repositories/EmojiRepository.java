package com.emojichallenges.game.repositories;

import com.emojichallenges.game.entities.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmojiRepository extends JpaRepository<Emoji, Long> {

    Boolean existsByName(String name);

    List<Emoji> findAllByChallenges(Long challengeId);

    List<Emoji> findAllByIdIn(List<Long> emojis);
}
