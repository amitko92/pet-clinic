package com.petClinic.petClinic.service;

import com.petClinic.petClinic.entity.Owner;
import com.petClinic.petClinic.repository.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<Owner> getOwnerById(int id){
        return ownerRepository.findOwnerByID(id);
    }
}
