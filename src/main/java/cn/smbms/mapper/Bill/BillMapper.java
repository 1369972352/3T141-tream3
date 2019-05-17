package cn.smbms.mapper.Bill;

import cn.smbms.entity.SmbmsBill;
import cn.smbms.entity.SmbmsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {


    /**
     * 分页查询查询全部商品
     *
     * @param currentPageNo
     * @param pageNo
     * @return
     */
    public List<SmbmsBill> getBillList(@Param("currentPageNo") int currentPageNo,
                                       @Param("pageNo") int pageNo,
                                       @Param("bill") SmbmsBill bill);

    /**
     * 查询总行数
     *
     * @param bill
     * @return
     */
    public int queryRows(@Param("bill") SmbmsBill bill);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public SmbmsBill queryById(@Param("id") int id);

    /**
     * 删除
     *
     * @return
     */
    public int deleteByIdBill(@Param("id") int id);

    /**
     * 修改
     *
     * @param bill
     * @return
     */
    public int uapateBill(SmbmsBill bill);

    /**
     * 增加
     * @param bill
     * @return
     */
    public int addBill(SmbmsBill bill);
}

