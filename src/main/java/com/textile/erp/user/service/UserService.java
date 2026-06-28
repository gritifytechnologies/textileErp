package com.textile.erp.user.service;

import com.textile.erp.user.dto.UserDto;
import com.textile.erp.user.dto.UserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto create(UserRequest request);

    UserDto findById(Long id);

    Page<UserDto> findAll(Pageable pageable);

    UserDto update(Long id, UserRequest request);

    void deactivate(Long id);
}
