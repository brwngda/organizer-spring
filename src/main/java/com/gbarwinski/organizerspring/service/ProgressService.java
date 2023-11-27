package com.gbarwinski.organizerspring.service;

import com.gbarwinski.organizerspring.model.Progress;
import com.gbarwinski.organizerspring.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProgressService {

    private final ProgressRepository progressRepository;

    public List<Progress> findAllProgress() {
        return progressRepository.findAll();
    }
}