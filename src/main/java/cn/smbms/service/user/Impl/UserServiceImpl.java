package cn.smbms.service.user.Impl;

import cn.smbms.entity.Pager;
import cn.smbms.entity.SmbmsUser;
import cn.smbms.mapper.user.UserMapper;
import cn.smbms.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name="userMapper")
    public UserMapper userMapper;

    public SmbmsUser login(String userCode, String userPassword) {
        return userMapper.login(userCode, userPassword);
    }

    /**
     * 分页查询
     *
     * @param currentPageNo//当前页码
     * @param pageNo//每页行数
     * @param user//用户对象
     * @return
     */
    public Pager<SmbmsUser> getUserList(int currentPageNo, int pageNo, SmbmsUser user) {
        //new一个分页实体接收
        Pager<SmbmsUser> pager = new Pager<SmbmsUser>();
        pager.setCurrentPageNo(currentPageNo);//设置当前页面
        pager.setPageNo(pageNo);//每页行数
        //先查询行数,再一起设置总行数
        pager.setTotalRows(userMapper.queryRows(user));
        //总页数(总行数+页大小-1)/页大小
        pager.setTotalPageCount((pager.getTotalRows() + pageNo - 1) / pageNo);
        //存到分页数据集合（当前页-1）*每页行数
        pager.setDatas(userMapper.getUserList((currentPageNo - 1) * pageNo, pageNo, user));
        //返回结果
        return pager;
    }

    /**
     * 根据userCode查询出User
     *
     * @param userCode
     * @return
     */
    public int selectUserCodeExist(String userCode) {
        int i = userMapper.selectUserCodeExist(userCode);
        return i;
    }

    /**
     * 添加
     *
     * @param user
     * @return
     */
    public int addUser(SmbmsUser user) {
        return userMapper.addUser(user);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public SmbmsUser queryById(int id) {
        return userMapper.queryById(id);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    public int uapateUser(SmbmsUser user) {
        return userMapper.uapateUser(user);
    }

    /**
     * 查询密码是否存在
     *
     * @param user
     * @return
     */
    public int upatePwdUser(SmbmsUser user) {

        return userMapper.upatePwdUser(user);
    }


}
