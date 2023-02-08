/*
 *
 * Copyright (c) 2020-2022, Java知识图谱 (http://www.altitude.xin).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package xin.altitude.spring.mybatisplus.joinquery.one2more.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.spring.mybatisplus.joinquery.one2more.service.IUserService;

/**
 * @author explore
 * @since 2021/05/24 15:03
 **/
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private IUserService userService;
    
    @GetMapping("/detail/{userId}")
    public AjaxResult detail(@PathVariable Integer userId) {
        return AjaxResult.success(userService.getOneUser(userId));
    }
    
    
    @GetMapping("/list")
    public AjaxResult list() {
        return AjaxResult.success(userService.getUserByList());
    }
    
    
    @GetMapping("/page")
    public AjaxResult page() {
        return AjaxResult.success(userService.getUserByPage(new Page<>()));
    }
    
    
}
