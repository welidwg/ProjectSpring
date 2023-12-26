package com.projetspring.springporject.service;

import com.projetspring.springporject.entity.Status;
import com.projetspring.springporject.repository.StatusRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ServiceStatus implements IServiceStatus{
    @Autowired
    StatusRepository statusRepository;
    @Override
    public void saveStatus(Status s) {
        Status status = statusRepository.findStatusByLabel(s.getLabel());
        if(status!=null)
            throw new RuntimeException("Status exists");
        statusRepository.save(s);
    }

    @Override
    public List<Status> getStatuses() {
        return statusRepository.findAll();
    }


    @Override
    public Status getStatusById(Long id) {
        return statusRepository.findById(id).orElse(null);
    }

    @Override
    public Status getStatusByLabel(String label) {
        return statusRepository.findStatusByLabel(label);
    }

    @Override
    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }
}
