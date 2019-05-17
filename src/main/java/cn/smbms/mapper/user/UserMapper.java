package cn.smbms.mapper.user;

import cn.smbms.entity.SmbmsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /**
     * 查询用户名跟密码
     * @param userCode
     * @Param("")多个参数使用注解
     * @return
     */
    public SmbmsUser login(@Param("userCode") String userCode,@Param("userPassword") String userPassword);

    /**
     * 根据id修改密码
     * @param id
     * @param pwd
     * @return
     */
    int updatePwd(@Param("id")int id,@Param("pwd")String pwd);

    /**
     * 分页查询查询全部用户
     * @param currentPageNo
     * @param pageNo
     * @return
     */
    public  List<SmbmsUser> getUserList(@Param("currentPageNo")int currentPageNo,
                                @Param("pageNo") int pageNo,
                                @Param("user") SmbmsUser user);

    /**
     * 查询总行数
     * @param user
     * @return
     */
    public int queryRows(@Param("user") SmbmsUser user);

    /**
     * 添加
     * @param user
     * @return
     */
    public int addUser(SmbmsUser user);
    /**
     * 根据userCode查询出User
     * @param userCode
     * @return
     */
    public int selectUserCodeExist(String userCode);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public SmbmsUser queryById(@Param("id") int id);

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteUser(@Param("id") int id);

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
