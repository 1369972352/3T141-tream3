package cn.smbms.mapper.Role;


import cn.smbms.entity.SmbmsRole;

import java.util.List;

public interface RoleMapper {
    /**
     * 查询所有用户角色
     * @return
     */
    List<SmbmsRole> getRoleList();
}
