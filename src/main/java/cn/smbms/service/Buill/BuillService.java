package cn.smbms.service.Buill;

import cn.smbms.entity.Pager;
import cn.smbms.entity.SmbmsBill;
import cn.smbms.entity.SmbmsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BuillService {
    /**
     * 分页查询
     * @param currentPageNo//当前页码
     * @param pageNo//每页行数
     * @param bill//用户对象
     * @return
     */
    Pager<SmbmsBill> getBillList(int currentPageNo,
                                 int pageNo,
                                 SmbmsBill bill);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public SmbmsBill queryById(@Param("id") int id);

    /**
     * 删除
     * @return
     */
    public int deleteByIdBill(int id );

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
