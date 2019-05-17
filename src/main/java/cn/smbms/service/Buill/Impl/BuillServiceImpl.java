package cn.smbms.service.Buill.Impl;

import cn.smbms.entity.Pager;
import cn.smbms.entity.SmbmsBill;
import cn.smbms.entity.SmbmsUser;
import cn.smbms.mapper.Bill.BillMapper;
import cn.smbms.service.Buill.BuillService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller("buillService")
public class BuillServiceImpl implements BuillService {
    @Resource(name = "billMapper")
    BillMapper billMapper;
    public Pager<SmbmsBill> getBillList(int currentPageNo, int pageNo, SmbmsBill bill) {
        //new一个分页实体接收
        Pager<SmbmsBill>pager=new Pager<SmbmsBill>();
        pager.setCurrentPageNo(currentPageNo);//设置当前页面
        pager.setPageNo(pageNo);//每页行数
        //先查询行数,再一起设置总行数
        pager.setTotalRows(billMapper.queryRows(bill));
        //总页数(总行数+页大小-1)/页大小
        pager.setTotalPageCount((pager.getTotalRows()+pageNo-1)/pageNo);
        //存到分页数据集合（当前页-1）*每页行数
        pager.setDatas(billMapper.getBillList((currentPageNo-1)*pageNo,pageNo,bill));
        //返回结果
        return pager;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public SmbmsBill queryById(int id) {
        return billMapper.queryById(id);
    }

    /**
     * 删除
     * @return
     */
    public int deleteByIdBill(int id ) {
        return billMapper.deleteByIdBill(id);
    }

    /**
     * 修改
     * @param bill
     * @return
     */
    public int uapateBill(SmbmsBill bill) {
        return billMapper.uapateBill(bill);
    }

    /**
     * 增加
     * @param bill
     * @return
     */
    public int addBill(SmbmsBill bill) {
        return billMapper.addBill(bill);
    }
}
