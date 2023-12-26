package com.projetspring.springporject.service;

import com.projetspring.springporject.entity.Status;

import java.util.List;

public interface IServiceStatus {

    public void saveStatus(Status s);
    public List<Status> getStatuses();
    public Status getStatusById(Long id);
    public Status getStatusByLabel(String label);

    public void deleteStatus(Long id);

}
