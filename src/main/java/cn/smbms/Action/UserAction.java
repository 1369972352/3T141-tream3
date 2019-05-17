package cn.smbms.Action;

import cn.smbms.entity.Pager;
import cn.smbms.entity.ResponseResult;
import cn.smbms.entity.SmbmsRole;
import cn.smbms.entity.SmbmsUser;
import cn.smbms.service.Role.Impl.RoleServiceImpl;
import cn.smbms.service.Role.RoleService;
import cn.smbms.service.user.Impl.UserServiceImpl;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "roleService")
    private RoleService roleService;
    private String userCode;//登录用户

    private String userPassword;//登录密码

    private String queryname;//用户名称

    private String queryUserRole;//用户角色

    private String pageIndex;

    private String userName;//用户名称

    private String gender;//用户性别
    private String birthday;//出生日期
    private String phone;//用户电话
    private String address;//用户地址
    private String userRole;//用户角色
    private String userid;//用户id
    private String oldpassword;//旧密码
    private String reNewPassword;//确认密码
    private String newpassword;//新密码

    private int uid;

    private SmbmsUser smbmsUser = new SmbmsUser();


    /**
     * 登录
     *
     * @return
     */
    public String loginUser() {
        SmbmsUser user = userService.login(userCode, userPassword);
        if (user != null) {
            ServletActionContext.getRequest().getSession().setAttribute(Constants.USER_SESSION, user);
            return SUCCESS;
        }
        //页面跳转（login.jsp）带出提示信息--转发
        ServletActionContext.getRequest().getSession().setAttribute("error", "用户名或密码不正确");
        return LOGIN;
    }


    /**
     * 查询全部用户
     *
     * @return
     */
    public String queryUser() {
        SmbmsUser user = new SmbmsUser();
        //设置页面容量
        int pageNo = 2;
        //当前页码
        int currentPageNo = 1;
        //如果用户名不等于空
        if (queryname != null && queryname != "") {
            user.setUserName(queryname);
        }
        //用户角色不等于空
        if (queryUserRole != null) {
            user.setUserRole(new Integer(queryUserRole));
        }
        if (pageIndex != null) {
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                return "query";
            }
        }
        //查询全部的用户+模糊查询
        Pager<SmbmsUser> userList = userService.getUserList(currentPageNo, pageNo, user);
        //查询用户角色
        List<SmbmsRole> roleList = roleService.getRoleList();
        //保存到session
        ServletActionContext.getRequest().getSession().setAttribute("userLists", userList.getDatas());
        ServletActionContext.getRequest().getSession().setAttribute("roleList", roleList);
        ServletActionContext.getRequest().getSession().setAttribute("totalPageCount", userList.getTotalPageCount());//总页数
        ServletActionContext.getRequest().getSession().setAttribute("currentPageNo", userList.getCurrentPageNo()); //当前页码
        ServletActionContext.getRequest().getSession().setAttribute("totalCount", userList.getTotalRows());//总行数
        return "query";
    }

    /**
     * '退出
     *
     * @return
     */
    public String ExitUser() {
        ServletActionContext.getRequest().getSession().removeAttribute(Constants.USER_SESSION);
        return LOGIN;
    }

    /**
     * 页面判断它的用户编码是否存在
     *
     * @return
     */
    public String userCodeExistUser() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");//设置页面编码格式
        int ucexist = userService.selectUserCodeExist(userCode);

        String msg = "{\"userCode\":\"false\"}";
        if (ucexist > 0) {
            msg = ("{\"userCode\":\"exist\"}");
        }
        PrintWriter writer1 = null;
        try {
            writer1 = response.getWriter();
            writer1.print(msg);
            writer1.flush();
            writer1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加
     *
     * @return
     */
    public String AddUser() {

        smbmsUser.setUserCode(userCode);
        smbmsUser.setUserName(userName);
        smbmsUser.setUserPassword(userPassword);
        if (gender.equals("男")) {
            smbmsUser.setGender(1);
        } else {
            smbmsUser.setGender(2);
        }
        smbmsUser.setPhone(phone);
        smbmsUser.setAddress(address);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date = sdf.parse(birthday);

            smbmsUser.setBirthday(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        smbmsUser.setUserRole(Integer.parseInt(userRole));
        SmbmsUser CreatedBy = (SmbmsUser) ServletActionContext.getRequest().getSession().getAttribute(Constants.USER_SESSION);
        smbmsUser.setCreatedBy(CreatedBy.getId());
        smbmsUser.setCreationDate(new Date());
        int i = userService.addUser(smbmsUser);
        if (i > 0) {
            return "add";
        }
        return "add";
    }

    /**
     * 根据id查询某个用户的信息
     *
     * @return
     */
    public String ViewUser() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        //调用后台方法得到user对象
        SmbmsUser queryById = userService.queryById(uid);
        if (queryById != null) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String timebrith = sdf.format(queryById.getBirthday());
            try {
                date = sdf.parse(timebrith);
                date = java.sql.Date.valueOf(timebrith);
                queryById.setBirthday(date);
                ServletActionContext.getRequest().getSession().setAttribute("user", queryById);
                return "View";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "View";
    }

    /**
     * 删除
     *
     * @return
     */
    public String deleteUser() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        StringBuffer sb = new StringBuffer();
        try {
            int i = userService.deleteUser(uid);
            if (i > 0) {
                sb.append("{\"delResult\":\"true\"}");
            } else {
                sb.append("{\"delResult\":\"false\"}");
            }
            PrintWriter writer1 = response.getWriter();
            writer1.print(sb.toString());
            writer1.flush();
            writer1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询修改的数据
     *
     * @return
     */
    public String ViewsUser() {
        //调用后台方法得到user对象
        SmbmsUser queryById = userService.queryById(uid);
        if (queryById != null) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String timebrith = sdf.format(queryById.getBirthday());
            try {
                date = sdf.parse(timebrith);
                date = java.sql.Date.valueOf(timebrith);
                queryById.setBirthday(date);
                ServletActionContext.getRequest().getSession().setAttribute("user", queryById);
                return "Views";
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "Views";
    }

    /**
     * json输出
     *
     * @return
     */
    public String rolelistUser() {
        List<SmbmsRole> getrolelist = roleService.getRoleList();
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer1 = response.getWriter();
            writer1.print(JSONArray.toJSON(getrolelist));
            writer1.flush();
            writer1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改
     *
     * @return
     */
    public String updateUser() {
        //先查询出来
        SmbmsUser user = userService.queryById(uid);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timebrith = sdf.format(user.getBirthday());
        try {
            date = sdf.parse(timebrith);
            date = java.sql.Date.valueOf(timebrith);
            user.setBirthday(date);
            user.setUserName(userName);
            user.setGender(Integer.parseInt(gender));
            user.setAddress(address);
            user.setPhone(phone);
            user.setUserRole(Integer.parseInt(userRole));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (user != null) {
            int i = userService.uapateUser(user);
            ServletActionContext.getRequest().getSession().setAttribute("user", user);
            if (i > 0) {
                return "upadeUser";
            }

            return "upadeUser";
        }
        return "upadeUser";
    }

    /*
     * 查询密码原密码
     *
     * @return
     */
    public String checkPwdUser() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");//设置页面编码格式
        PrintWriter writer1 = null;
        //查询出当前密码
        SmbmsUser cod = (SmbmsUser) ServletActionContext.getRequest().getSession().getAttribute(Constants.USER_SESSION);
        String msg;
        //把当前密码与自己输入的密码对比 一样就提示成功 不一样就提示错误
        if (cod.getUserPassword().equals(oldpassword)) {
            msg = ("{\"result\":\"true\"}");
        } else {
            msg = ("{\"result\":\"false\"}");
        }

        if (oldpassword == null && oldpassword == "") {
            msg = ("{\"result\":\"error\"}");
        }
        writer1 = response.getWriter();
        writer1.print(msg);
        writer1.flush();
        writer1.close();
        return null;
    }

    /**
     * 修改密码
     *
     * @return
     */
    public String upatepwdUser() {
        SmbmsUser smbmsUser1 = new SmbmsUser();
        //查询出当前密码
        SmbmsUser cod = (SmbmsUser) ServletActionContext.getRequest().getSession().getAttribute(Constants.USER_SESSION);
        smbmsUser1.setId(cod.getId());
        smbmsUser1.setUserPassword(newpassword);
        int i = userService.upatePwdUser(smbmsUser1);
        if (i > 0) {
            return "pwd";
        }

        return "pwd";
    }



    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getQueryname() {
        return queryname;
    }

    public void setQueryname(String queryname) {
        this.queryname = queryname;
    }

    public String getQueryUserRole() {
        return queryUserRole;
    }

    public void setQueryUserRole(String queryUserRole) {
        this.queryUserRole = queryUserRole;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }


    public SmbmsUser getSmbmsUser() {
        return smbmsUser;
    }

    public void setSmbmsUser(SmbmsUser smbmsUser) {
        this.smbmsUser = smbmsUser;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }
}
