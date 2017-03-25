package com.kaishengit.service.impl;

import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunny on 2017/3/17.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

}