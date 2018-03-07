package com.dyq.demo.service;

import com.dyq.demo.domain.Authority;
import com.dyq.demo.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Authority getAuthorityById(Long id) {
        return authorityRepository.findOne(id);
    }

    @Override
    public Authority saveAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    @Override
    public void removeAuthority(Long id) {
        authorityRepository.delete(id);
    }

    @Override
    public Authority updateAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }
}
