package com.emojichallenges.game.tests.repositories;

import com.emojichallenges.game.entities.Emoji;
import com.emojichallenges.game.repositories.EmojiRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmojiRepositoryTest {
    @Autowired
    private EmojiRepository emojiRepository;

    Emoji emojiMock = Emoji.builder().name("test").unicode("unicode").build();

    @Test
    public void existsByNameReturnsTrue() {
        emojiRepository.save(emojiMock);
        Assertions.assertThat(emojiRepository.existsByNameIgnoreCase(emojiMock.getName())).isTrue();
        Assertions.assertThat(emojiRepository.existsByNameIgnoreCase(emojiMock.getName().toUpperCase())).isTrue();
        Assertions.assertThat(emojiRepository.existsByNameIgnoreCase(emojiMock.getName().toLowerCase())).isTrue();
    }

    @Test
    public void existsByNameReturnsFalse() {
        Assertions.assertThat(emojiRepository.existsByNameIgnoreCase(emojiMock.getName())).isFalse();
        Assertions.assertThat(emojiRepository.existsByNameIgnoreCase(emojiMock.getName().toUpperCase())).isFalse();
        Assertions.assertThat(emojiRepository.existsByNameIgnoreCase(emojiMock.getName().toLowerCase())).isFalse();
    }

    @Test
    public void findByNameReturnsTrue() {
        emojiRepository.save(emojiMock);
        Assertions.assertThat(emojiRepository.findByNameIgnoreCase(emojiMock.getName()).isPresent()).isTrue();
        Assertions.assertThat(emojiRepository.findByNameIgnoreCase(emojiMock.getName().toUpperCase()).isPresent()).isTrue();
        Assertions.assertThat(emojiRepository.findByNameIgnoreCase(emojiMock.getName().toLowerCase()).isPresent()).isTrue();
    }

    @Test
    public void findByNameReturnsFalse() {
        Assertions.assertThat(emojiRepository.findByNameIgnoreCase(emojiMock.getName()).isPresent()).isFalse();
        Assertions.assertThat(emojiRepository.findByNameIgnoreCase(emojiMock.getName().toUpperCase()).isPresent()).isFalse();
        Assertions.assertThat(emojiRepository.findByNameIgnoreCase(emojiMock.getName().toLowerCase()).isPresent()).isFalse();
    }
}
