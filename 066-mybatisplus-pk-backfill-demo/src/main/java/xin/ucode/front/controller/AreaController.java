package xin.ucode.front.controller;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.ucode.front.domain.Area;
import xin.ucode.front.mapper.AreaMapper;
import xin.ucode.front.service.IAreaService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/front/area")
public class AreaController {
    @Autowired
    private IAreaService areaService;
    @Resource
    private AreaMapper areaMapper;
    
    
    /**
     * service实现
     */
    @PostMapping("/add1")
    public AjaxResult add1() {
        String name = IService.class.getSimpleName()+System.currentTimeMillis();
        Area area = new Area(name);
        areaService.save(area);
        return AjaxResult.success(area);
    }
    
    /**
     * Mapper层实现
     */
    @PostMapping("/add2")
    public AjaxResult add2() {
        String name = BaseMapper.class.getSimpleName()+System.currentTimeMillis();
        Area area = new Area(name);
        areaMapper.insert(area);
        return AjaxResult.success(area);
    }
    
    /**
     * Model
     */
    @PostMapping("/add3")
    public AjaxResult add3() {
        String name = Model.class.getSimpleName()+System.currentTimeMillis();
        Area area = new Area(name);
        area.insert();
        return AjaxResult.success(area);
    }
    
}
