package cn.smbms.service.Role.Impl;

import cn.smbms.entity.SmbmsRole;
import cn.smbms.mapper.Role.RoleMapper;
import cn.smbms.service.Role.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource(name="roleMapper")
    public RoleMapper roleMapper;
    public List<SmbmsRole> getRoleList() {
        return roleMapper.getRoleList();
    }
}
