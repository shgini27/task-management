package org.ttweb.taskmanagement.infrastructure.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.ttweb.taskmanagement.domain.model.user.User;
import org.ttweb.taskmanagement.domain.model.user.UserRepository;
import org.ttweb.taskmanagement.infrastrucure.repository.HibernateUserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class HibernateUserRepositoryTest {
    private UserRepository repository;

    @Autowired
    public HibernateUserRepositoryTest(UserRepository repository){
        this.repository = repository;
    }

    @TestConfiguration
    public static class UserRepositoryTestContextConfiguration {
        @Bean
        public UserRepository userRepository(EntityManager entityManager) {
            return new HibernateUserRepository(entityManager);
        }
    }

    @Test
    public void save_nullUsernameUser_shouldFail() {
        User invalidUser = User.create(
                null, "sunny@taskagile.com", "Test", "Test", "MyPassword!");

        Exception exception = assertThrows(PersistenceException.class, () -> {
            repository.save(invalidUser);
        });

        assertTrue(exception.getMessage().contains("exception"));
    }
}
