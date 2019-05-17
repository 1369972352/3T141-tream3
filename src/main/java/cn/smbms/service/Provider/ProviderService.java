package cn.smbms.service.Provider;

import cn.smbms.entity.Pager;
import cn.smbms.entity.SmbmsBill;
import cn.smbms.entity.SmbmsProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderService {
    /**
     * 查询所有供应商
     * @return
     */
    List<SmbmsProvider> getProviderList();

    /**
     * 分页查询
     * @param currentPageNo//当前页码
     * @param pageNo//每页行数
     * @param provider//用户对象
     * @return
     */
    Pager<SmbmsProvider> getProviderLists(int currentPageNo,
                                 int pageNo,
                                     SmbmsProvider provider);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public SmbmsProvider queryById(@Param("id")int id);

    /***
     * 修改
     * @param provider
     * @return
     */
    public int upateProvider(SmbmsProvider provider);
    /**
     * 添加
     * @param provider
     * @return
     */
    public int addProvider(SmbmsProvider provider);

    /**
     * 查询供应商有没有商品
     * @param bill
     * @return
     */
    public int queryBillRows(int bill);

    /**
     * 删除
     * @return
     */
    public int deleteProvider(int proid);
}
