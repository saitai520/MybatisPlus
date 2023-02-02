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

package xin.altitude.spring.mybatisplus.joinquery.more2more.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.altitude.spring.mybatisplus.joinquery.more2more.domain.StuSubRelation;
import xin.altitude.spring.mybatisplus.joinquery.more2more.mapper.StuSubRelationMapper;
import xin.altitude.spring.mybatisplus.joinquery.more2more.service.IStuSubRelationService;

@Service
public class StuSubRelationServiceImpl extends ServiceImpl<StuSubRelationMapper, StuSubRelation> implements IStuSubRelationService {
    @Autowired
    private StuSubRelationMapper stuSubRelationMapper;
}
