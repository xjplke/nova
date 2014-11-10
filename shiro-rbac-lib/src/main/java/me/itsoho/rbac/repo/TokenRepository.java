package me.itsoho.rbac.repo;

import java.util.UUID;

import me.itsoho.rbac.domain.Token;

import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, UUID> {

}
