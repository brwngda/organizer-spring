package com.gbarwinski.organizerspring.service;

import com.gbarwinski.organizerspring.model.Progress;
import com.gbarwinski.organizerspring.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProgressService {

    private final ProgressRepository progressRepository;

    public List<Progress> findAllProgress() {
        List<Progress> allProjectProgress = progressRepository.findAll();
        boolean empty = allProjectProgress.isEmpty();
        LinkedHashMap<Integer, Progress> linkedMap = new LinkedHashMap<>();
        if (empty) {
            linkedMap.put(1, new Progress("Backlog"));
            linkedMap.put(2, new Progress("Ready"));
            linkedMap.put(3, new Progress("In progress"));
            linkedMap.put(4, new Progress("Testing"));
            linkedMap.put(5, new Progress("Finished"));
            for (Map.Entry<Integer, Progress> integerProgressEntry : linkedMap.entrySet()) {
                progressRepository.save(integerProgressEntry.getValue());
            }
        }
        return allProjectProgress;
    }
}