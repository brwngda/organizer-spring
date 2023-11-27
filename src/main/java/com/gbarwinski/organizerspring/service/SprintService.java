package com.gbarwinski.organizerspring.service;

import com.gbarwinski.organizerspring.exception.NoSprintFoundException;
import com.gbarwinski.organizerspring.model.Sprint;
import com.gbarwinski.organizerspring.repository.SprintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SprintService {

    private final SprintRepository sprintRepository;

    public List<Sprint> findAll() {
        return sprintRepository.findAll();
    }

    public Sprint findById(Long id) {
        return sprintRepository.findById(id).orElseThrow(() -> new NoSprintFoundException(id));
    }
}