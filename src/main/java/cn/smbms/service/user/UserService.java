package cn.smbms.service.user;

import cn.smbms.entity.Pager;
import cn.smbms.entity.SmbmsUser;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    /**
     * 用户登录
     *
     * @param userCode
     * @param userPassword
     * @return
     */
    public SmbmsUser login(String userCode, String userPassword);

    /**
     * 分页查询
     * @param currentPageNo//当前页码
     * @param pageNo//每页行数
     * @param user//用户对象
     * @return
     */
    Pager<SmbmsUser> getUserList(int currentPageNo,
                                 int pageNo,
                                 SmbmsUser user);

    /**
     * 根据userCode查询出User
     * @param userCode
     * @return
     */
    public int selectUserCodeExist(String userCode);

    /**
     * 添加
     * @param user
     * @return
     */
    public int addUser(SmbmsUser user);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public SmbmsUser queryById(int id);

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteUser(int id);
    /**
     * 修改
     * @param user
     * @return
     */
    public int uapateUser(SmbmsUser user);

    /**
     * 修改密码
     * @param user
     * @return
     */
    public int upatePwdUser(SmbmsUser user);


}
