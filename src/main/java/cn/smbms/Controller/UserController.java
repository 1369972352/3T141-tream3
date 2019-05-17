package cn.smbms.Controller;

import cn.smbms.entity.Pager;
import cn.smbms.entity.SmbmsRole;
import cn.smbms.entity.SmbmsUser;
import cn.smbms.service.Role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "roleService")
    private RoleService roleService;
    /**
     * 登录
     *
     * @return
     */
    @RequestMapping(value = "login.jsp",method = RequestMethod.POST)
    public String loginUser(@RequestParam String userCode,
                            @RequestParam String userPassword) {
        SmbmsUser user = userService.login(userCode, userPassword);
        if (user != null) {
            ServletActionContext.getRequest().getSession().setAttribute(Constants.USER_SESSION, user);
            /*redirect重定向*/
            return "redirect:/jsp/frame.jsp";
        }
        //页面跳转（login.jsp）带出提示信息--转发
        ServletActionContext.getRequest().getSession().setAttribute("error", "用户名或密码不正确");
        return "login";
    }
}
