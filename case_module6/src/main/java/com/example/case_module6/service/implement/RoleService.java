package com.example.case_module6.service.implement;

import com.example.case_module6.model.Role;
import com.example.case_module6.repository.RoleRepository;
import com.example.case_module6.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
