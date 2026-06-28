package com.textile.erp.user.service;

import com.textile.erp.exception.DuplicateResourceException;
import com.textile.erp.exception.ResourceNotFoundException;
import com.textile.erp.role.entity.Role;
import com.textile.erp.role.repository.RoleRepository;
import com.textile.erp.security.TenantContext;
import com.textile.erp.user.dto.UserDto;
import com.textile.erp.user.dto.UserRequest;
import com.textile.erp.user.entity.User;
import com.textile.erp.user.mapper.UserMapper;
import com.textile.erp.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper     userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository  = userRepository;
        this.roleRepository  = roleRepository;
        this.userMapper      = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto create(UserRequest request) {
        Long tenantId = TenantContext.getTenantId();

        if (userRepository.existsByUsernameAndTenantId(request.getUsername(), tenantId)) {
            throw new DuplicateResourceException("User", "username", request.getUsername());
        }
        if (userRepository.existsByEmailAndTenantId(request.getEmail(), tenantId)) {
            throw new DuplicateResourceException("User", "email", request.getEmail());
        }

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", request.getRoleId()));

        User user = User.builder()
                .tenantId(tenantId)
                .role(role)
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .active(true)
                .build();

        User saved = userRepository.save(user);
        log.info("User [{}] created for tenant [{}]", saved.getUsername(), tenantId);
        return userMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        return userMapper.toDto(getUserOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAllByTenantId(TenantContext.getTenantId(), pageable)
                .map(userMapper::toDto);
    }

    @Override
    public UserDto update(Long id, UserRequest request) {
        User user = getUserOrThrow(id);
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", request.getRoleId()));
        user.setRole(role);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deactivate(Long id) {
        User user = getUserOrThrow(id);
        user.setActive(false);
        userRepository.save(user);
    }

    private User getUserOrThrow(Long id) {
        return userRepository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }
}
