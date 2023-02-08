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

package xin.altitude.spring.mybatisplus.joinquery.more2more.entity.bo;

import lombok.Data;
import lombok.NoArgsConstructor;
import xin.altitude.spring.mybatisplus.joinquery.more2more.domain.Subject;

/**
 * @author explore
 * @since 2021/10/23 21:25
 **/
@Data
@NoArgsConstructor
public class SubjectBo extends Subject {
    /**
     * 分数
     */
    private Integer score;
    
    public SubjectBo(Subject subject) {
        super(subject);
    }
}
