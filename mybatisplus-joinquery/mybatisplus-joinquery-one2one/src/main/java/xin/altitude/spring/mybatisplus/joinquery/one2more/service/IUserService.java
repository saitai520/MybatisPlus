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

package xin.altitude.spring.mybatisplus.joinquery.one2more.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xin.altitude.spring.mybatisplus.joinquery.one2more.domain.User;
import xin.altitude.spring.mybatisplus.joinquery.one2more.entity.vo.UserVo;

import java.util.List;

/**
 * @author explore
 * @since 2022/03/02 15:45
 **/
public interface IUserService extends IService<User> {
    /**
     * 查询单个学生信息（一个学生对应一个部门）
     */
    UserVo getOneUser(Integer userId);
    
    /**
     * 批量查询学生信息（一个学生对应一个部门）
     */
    List<UserVo> getUserByList();
    
    /**
     * 分页查询学生信息（一个学生对应一个部门）
     */
    IPage<UserVo> getUserByPage(Page<User> page);
}
