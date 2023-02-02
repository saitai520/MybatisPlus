package xin.ucode.front.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xin.ucode.front.domain.Dept;
import xin.ucode.front.entity.DeptVo;

public interface IDeptService extends IService<Dept> {
    /**
     * 取出部门DO
     * @return
     */
    Page<Dept> selectDeptPage1();
    
    IPage<DeptVo> selectDeptPage2();
    
    IPage<DeptVo> selectDeptPage3();
    
    /**
     * 优化版 一行代码完成userList属性注入
     *
     * @return
     */
    IPage<DeptVo> selectDeptPage4();
}
