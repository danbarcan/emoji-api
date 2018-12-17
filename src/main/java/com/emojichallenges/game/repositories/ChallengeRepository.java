package com.emojichallenges.game.repositories;

import com.emojichallenges.game.entities.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    Boolean existsByName(String name);
}
