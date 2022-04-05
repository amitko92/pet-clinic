package com.petClinic.petClinic.service;

import com.petClinic.petClinic.entity.Owner;
import com.petClinic.petClinic.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository){
        this.ownerRepository = ownerRepository;
    }

    public void addOwner(Owner owner){
        ownerRepository.addOwner(owner);
    }
}
