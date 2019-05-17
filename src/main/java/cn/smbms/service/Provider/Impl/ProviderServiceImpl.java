package cn.smbms.service.Provider.Impl;

import cn.smbms.entity.Pager;
import cn.smbms.entity.SmbmsBill;
import cn.smbms.entity.SmbmsProvider;
import cn.smbms.mapper.Provider.ProviderMapper;
import cn.smbms.service.Provider.ProviderService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
@Controller("providerService")
public class ProviderServiceImpl implements ProviderService {
    @Resource(name = "providerMapper")
    ProviderMapper providerMapper;

    /**
     * 查询所有供应商
     * @return
     */
    public List<SmbmsProvider> getProviderList() {
        return providerMapper.getProviderList();
    }

    /**
     * 分页
     * @param currentPageNo//当前页码
     * @param pageNo//每页行数
     * @param provider//用户对象
     * @return
     */
    public Pager<SmbmsProvider> getProviderLists(int currentPageNo, int pageNo, SmbmsProvider provider) {
        //new一个分页实体接收
        Pager<SmbmsProvider>pager=new Pager<SmbmsProvider>();
        pager.setCurrentPageNo(currentPageNo);//设置当前页面
        pager.setPageNo(pageNo);//每页行数
        //先查询行数,再一起设置总行数
        pager.setTotalRows(providerMapper.queryRows(provider));
        //总页数(总行数+页大小-1)/页大小
        pager.setTotalPageCount((pager.getTotalRows()+pageNo-1)/pageNo);
        //存到分页数据集合（当前页-1）*每页行数
        pager.setDatas(providerMapper.getProviderLists((currentPageNo-1)*pageNo,pageNo,provider));
        //返回结果
        return pager;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public SmbmsProvider queryById(int id) {
        return providerMapper.queryById(id);
    }

    /***
     * 修改
     * @param provider
     * @return
     */
    public int upateProvider(SmbmsProvider provider) {
        return providerMapper.upateProvider(provider);
    }

    /**
     * 添加
     * @param provider
     * @return
     */
    public int addProvider(SmbmsProvider provider) {
        return providerMapper.addProvider(provider);
    }
    /**
     * 查询供应商有没有商品
     * @param proid
     * @return
     */
    public int queryBillRows(int proid) {

        return providerMapper.queryBillRows(proid);
    }

    /**
     * 删除
     * @param proid
     * @return
     */
    public int deleteProvider(int proid) {
        return providerMapper.deleteProvider(proid);
    }
}
