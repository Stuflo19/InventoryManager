package com.stuflo.inventorymanager.repository;

import com.stuflo.inventorymanager.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> { }
