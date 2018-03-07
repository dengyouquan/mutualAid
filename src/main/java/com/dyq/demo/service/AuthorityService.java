package com.dyq.demo.service;

import com.dyq.demo.domain.Authority;
import com.dyq.demo.domain.User;

import java.util.List;

public interface AuthorityService {
    /**
     * 根据id获取 Authority
     * @param Authority
     * @return
     */
    Authority getAuthorityById(Long id);
    Authority saveAuthority(Authority authority);
    void removeAuthority(Long id);
    Authority updateAuthority(Authority authority);
}
