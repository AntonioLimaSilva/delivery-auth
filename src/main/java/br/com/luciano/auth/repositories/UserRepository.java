package br.com.luciano.auth.repositories;

import br.com.luciano.auth.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author luciano lima
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select distinct u from UserEntity u join fetch u.roles r where u.username = :username")
    List<UserEntity> findByUsername(String username);
}
