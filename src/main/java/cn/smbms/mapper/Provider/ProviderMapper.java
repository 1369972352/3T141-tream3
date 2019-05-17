package cn.smbms.mapper.Provider;

import cn.smbms.entity.SmbmsBill;
import cn.smbms.entity.SmbmsProvider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {

    /**
     * 查询所有供应商
     * @return
     */
    List<SmbmsProvider> getProviderList();

    /**
     * 分页查询查询全部商品
     *
     * @param currentPageNo
     * @param pageNo
     * @return
     */
    public List<SmbmsProvider> getProviderLists(@Param("currentPageNo") int currentPageNo,
                                       @Param("pageNo") int pageNo,
                                       @Param("provider") SmbmsProvider provider);
    /**
     * 查询总行数
     *
     * @param provider
     * @return
     */
    public int queryRows(@Param("provider") SmbmsProvider provider);

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
     * @param proid
     * @return
     */
    public int queryBillRows(int proid);

    /**
     * 删除
     * @return
     */
    public int deleteProvider(int proid);

}
