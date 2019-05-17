package cn.smbms.Action;

import cn.smbms.entity.*;
import cn.smbms.service.Buill.BuillService;
import cn.smbms.service.Buill.Impl.BuillServiceImpl;
import cn.smbms.service.Provider.Impl.ProviderServiceImpl;
import cn.smbms.service.Provider.ProviderService;
import cn.smbms.service.Role.Impl.RoleServiceImpl;
import cn.smbms.service.Role.RoleService;
import cn.smbms.tools.Constants;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
@Controller
public class BuillAction extends ActionSupport {

    @Resource(name="buillService")
    public BuillService buillService ;//订单业务层
    @Resource(name="providerService")
    public ProviderService providerService;//供应商业务层


    private String queryProductName;//商品名称
    private String queryProviderId;//供应商
    private String queryIsPayment;//是否付款
    private String billid;//商品id
    private String pageIndex;
    private String billCode;//订单编码
    private String productName;//商品名称
    private String productUnit;//商品单位
    private String productCount;//商品数量
    private String totalPrice;//总金额
    private String providerId;//供应商
    private String isPayment;//是否付款
    private  String id;


    public SmbmsBill buill = new SmbmsBill();

    /**
     * 查询全部商品模糊查询
     *
     * @return
     */
    public String queryBuill() {
        //设置页面容量
        int pageNo = 5;
        //当前页码
        int currentPageNo = 1;
        //商品名称不等于空
        if (queryProductName != null && queryProductName != "") {
            buill.setProductName(queryProductName);
        }
        //供应商id不等等于空
        if (queryProviderId != null) {
            buill.setProviderId(Integer.parseInt(queryProviderId));
        }
        //是否付款
        if (queryIsPayment != null) {
            buill.setIsPayment(Integer.parseInt(queryIsPayment));
        }
        if (pageIndex != null) {
            try {
                currentPageNo = Integer.valueOf(pageIndex);
            } catch (NumberFormatException e) {
                return "query";
            }
        }
        //查询全部的用户+模糊查询
        Pager<SmbmsBill> billList = buillService.getBillList(currentPageNo, pageNo, buill);
        List<SmbmsProvider> roleList = providerService.getProviderList();
        //保存到session
        ServletActionContext.getRequest().getSession().setAttribute("billList", billList.getDatas());
        ServletActionContext.getRequest().getSession().setAttribute("providerList", roleList);
        ServletActionContext.getRequest().getSession().setAttribute("totalPageCount", billList.getTotalPageCount());//总页数
        ServletActionContext.getRequest().getSession().setAttribute("currentPageNo", billList.getCurrentPageNo()); //当前页码
        ServletActionContext.getRequest().getSession().setAttribute("totalCount", billList.getTotalRows());//总行数
        return "bquery";
    }

    /**
     * 根据id查询
     *
     * @return
     */
    public String queryByIdBuill() {
        buill = buillService.queryById(Integer.parseInt(billid));
        if (buill != null) {
            ServletActionContext.getRequest().getSession().setAttribute("bill", buill);
        }
        return "queryId";
    }

    /**
     * 根据id删除
     *
     * @return
     */
    public String deleteByIdBuill() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        StringBuffer sb = new StringBuffer();
        try {
            int i = buillService.deleteByIdBill(Integer.parseInt(billid));
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
     * json
     *
     * @return
     */
    public String JsonBuill() {

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        List<SmbmsProvider> getproviderlist = providerService.getProviderList();
        try {
            PrintWriter writer1 = response.getWriter();
            writer1.print(JSONArray.toJSON(getproviderlist));
            writer1.flush();
            writer1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改查询赋值上去
     * @return
     */
    public String upateBuill() {
        buill = buillService.queryById(Integer.parseInt(billid));
        if (buill != null) {
            ServletActionContext.getRequest().getSession().setAttribute("bill",buill);
        }


        return "uqate";
    }

    /**
     * 修改
     * @return
     */
    public String upatesBuill(){
        buill.setId(Integer.parseInt(id));
        if(billCode!=null){
            this.buill.setBillCode(billCode);//订单编码
        }
        buill.setProductName(productName);//商品名称
        buill.setProductUnit(productUnit);//商品单位
        buill.setProductCount(Double.valueOf(productCount));//商品数量
        buill.setTotalPrice(Double.valueOf(totalPrice));//总金额
        buill.setProviderId(Integer.parseInt(providerId));//供应商
        buill.setIsPayment(Integer.parseInt(isPayment));//是否付款
        int i = buillService.uapateBill(buill);
        if(i>0){
            return  "buillquery";
        }

        return  "buillquery";
    }

    /**
     * 添加
     * @return
     */
    public String addBuill(){

        if(billCode!=null){
            this.buill.setBillCode(billCode);//订单编码
        }
        buill.setProductDesc(productName);
        buill.setProductName(productName);//商品名称
        buill.setProductUnit(productUnit);//商品单位
        buill.setProductCount(Double.valueOf(productCount));//商品数量
        buill.setTotalPrice(Double.valueOf(totalPrice));//总金额
        buill.setProviderId(Integer.parseInt(providerId));//供应商
        buill.setIsPayment(Integer.parseInt(isPayment));//是否付款
        buill.setCreationDate(new Date());
        SmbmsUser CreatedBy = (SmbmsUser) ServletActionContext.getRequest().getSession().getAttribute(Constants.USER_SESSION);
        buill.setCreatedBy(CreatedBy.getId());
        int i = buillService.addBill(buill);
        if (i>0){
            return "add";
    }
        return "add";
    }



    public String getQueryProductName() {
        return queryProductName;
    }

    public void setQueryProductName(String queryProductName) {
        this.queryProductName = queryProductName;
    }

    public String getQueryProviderId() {
        return queryProviderId;
    }

    public void setQueryProviderId(String queryProviderId) {
        this.queryProviderId = queryProviderId;
    }

    public String getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(String pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getQueryIsPayment() {
        return queryIsPayment;
    }

    public void setQueryIsPayment(String queryIsPayment) {
        this.queryIsPayment = queryIsPayment;
    }

    public ProviderService getProviderService() {
        return providerService;
    }

    public String getBillid() {
        return billid;
    }

    public void setBillid(String billid) {
        this.billid = billid;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(String isPayment) {
        this.isPayment = isPayment;
    }
    public SmbmsBill getBuill() {
        return buill;
    }

    public void setBuill(SmbmsBill buill) {
        this.buill = buill;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
