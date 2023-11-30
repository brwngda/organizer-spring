package com.gbarwinski.organizerspring.repository;

import com.gbarwinski.organizerspring.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}