package cn.smbms.Action;

import cn.smbms.entity.Pager;
import cn.smbms.entity.SmbmsBill;
import cn.smbms.entity.SmbmsProvider;
import cn.smbms.entity.SmbmsUser;
import cn.smbms.service.Provider.Impl.ProviderServiceImpl;
import cn.smbms.service.Provider.ProviderService;
import cn.smbms.tools.Constants;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
@Controller
public class ProviderAction extends ActionSupport {
    @Resource(name="providerService")
    public ProviderService providerService;
    SmbmsProvider provider = new SmbmsProvider();
    private String queryProCode;//供应商编码
    private String queryProName;//供应商名称

    private String proid;//供应商id
    private String pageIndex;
    private String proCode;//供应商编码
    private String proName;//供应商名称
    private String proContact;//联系人
    private String proPhone;//联系电话
    private String proAddress;//联系地址
    private String proFax;//传真
    private String proDesc;//描述

    /**
     * 分页加模糊查询
     *
     * @return
     */
    public String queryProvider() {
        //设置页面容量
        int pageNo = 5;
        //当前页码
        int currentPageNo = 1;
        provider.setProCode(queryProCode);
        provider.setProName(queryProName);
        if (pageIndex != null) {
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                return "query";
            }
        }
        //查询
        Pager<SmbmsProvider> providerLists = providerService.getProviderLists(currentPageNo, pageNo, provider);
        ServletActionContext.getRequest().getSession().setAttribute("providerLists", providerLists.getDatas());
        ServletActionContext.getRequest().getSession().setAttribute("totalPageCount", providerLists.getTotalPageCount());//总页数
        ServletActionContext.getRequest().getSession().setAttribute("currentPageNo", providerLists.getCurrentPageNo()); //当前页码
        ServletActionContext.getRequest().getSession().setAttribute("totalCount", providerLists.getTotalRows());//总行数
        return "query";
    }
    /**
     * 查询全部
     *
     * @return
     */
    public String viewProvider() {
        provider = providerService.queryById(Integer.parseInt(proid));
        ServletActionContext.getRequest().getSession().setAttribute("provider", provider);
        return "view";
    }

    /**
     * 修改查询
     *
     * @return
     */
    public String upateViewProvider() {
        provider = providerService.queryById(Integer.parseInt(proid));
        ServletActionContext.getRequest().getSession().setAttribute("provider", provider);
        return "upateView";
    }

    /**
     * 修改
     *
     * @return
     */
    public String upateProvider() {
        provider = (SmbmsProvider) ServletActionContext.getRequest().getSession().getAttribute("provider");
        this.provider.setId(provider.getId());
        this.provider.setProCode(proCode);//供应商编码
        this.provider.setProName(proName);//供应商名称
        this.provider.setProAddress(proAddress);//联系地址
        this.provider.setProFax(proFax);//传真
        this.provider.setProPhone(proPhone);//联系电话
        this.provider.setProDesc(proDesc);//描述
        this.provider.setProContact(proContact);//联系人
        int i = providerService.upateProvider(this.provider);
        if (i > 0) {
            return "upate";
        }
        return "upate";
    }

    /**
     * 添加
     *
     * @return
     */
    public String addProvider() {
        provider.setProCode(proCode);//供应商编码
        provider.setProName(proName);//供应商名称
        provider.setProAddress(proAddress);//联系地址
        provider.setProFax(proFax);//传真
        provider.setProPhone(proPhone);//联系电话
        provider.setProDesc(proDesc);//描述
        provider.setProContact(proContact);//联系人
        SmbmsUser CreatedBy = (SmbmsUser) ServletActionContext.getRequest().getSession().getAttribute(Constants.USER_SESSION);
        provider.setCreatedBy(CreatedBy.getId());//创建人
        provider.setCreationDate(new Date());//创建时间
        int i = providerService.addProvider(provider);
        if (i > 0) {
            return "add";
        }
        return "add";
    }

    /**
     * 查询供应商有没有商品
     * @return
     */
    public String RowsProvider() throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        StringBuffer sb = new StringBuffer();
        //查询
        int i = providerService.queryBillRows(Integer.parseInt(proid));
        if(i==0){
            //删除的方法
            int provider = providerService.deleteProvider(Integer.parseInt(proid));
            if(provider>0){
                sb.append("{\"delResult\":\"true\"}");
            }else{
                sb.append("{\"delResult\":\"false\"}");
            }
        }else{
            //有订单就
            int count=i;
            sb.append("{\"delResult\":\"+"+count+"+\"}");

        }
        PrintWriter writer1 = response.getWriter();
        writer1.print(sb.toString());
        writer1.flush();
        writer1.close();
        return "delete";
    }

        public String getQueryProCode() {
        return queryProCode;
    }

    public void setQueryProCode(String queryProCode) {
        this.queryProCode = queryProCode;
    }

    public String getQueryProName() {
        return queryProName;
    }

    public void setQueryProName(String queryProName) {
        this.queryProName = queryProName;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }


    public SmbmsProvider getProvider() {
        return provider;
    }

    public void setProvider(SmbmsProvider provider) {
        this.provider = provider;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProContact() {
        return proContact;
    }

    public void setProContact(String proContact) {
        this.proContact = proContact;
    }

    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }

    public String getProAddress() {
        return proAddress;
    }

    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }

    public String getProFax() {
        return proFax;
    }

    public void setProFax(String proFax) {
        this.proFax = proFax;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }
}
