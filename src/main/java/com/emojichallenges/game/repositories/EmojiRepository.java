package com.emojichallenges.game.repositories;

import com.emojichallenges.game.entities.Emoji;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmojiRepository extends JpaRepository<Emoji, Long> {

    Boolean existsByNameIgnoreCase(String name);

    Optional<Emoji> findByNameIgnoreCase(String name);

    List<Emoji> findAllByChallenges(Long challengeId);

    List<Emoji> findAllByIdIn(List<Long> emojis);
}
