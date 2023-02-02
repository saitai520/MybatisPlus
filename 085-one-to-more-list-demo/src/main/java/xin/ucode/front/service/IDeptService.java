package xin.ucode.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xin.ucode.front.domain.Dept;
import xin.ucode.front.entity.DeptVo;

import java.util.List;

public interface IDeptService extends IService<Dept> {
    DeptVo selectDeptList1(Long deptId);
    
    DeptVo selectDeptList2(Long deptId);
    
    List<DeptVo> selectDeptList3();
    
    List<DeptVo> selectDeptList4();
    
    List<DeptVo> selectDeptList5();
}
