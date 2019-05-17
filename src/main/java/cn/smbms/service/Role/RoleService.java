package cn.smbms.service.Role;


import cn.smbms.entity.SmbmsRole;

import java.util.List;

public interface RoleService {
    /**
     * 查询所有用户角色
     * @return
     */
    List<SmbmsRole> getRoleList();
}
