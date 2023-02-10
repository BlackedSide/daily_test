package com.qunar.data.analysis.bean;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author jinpeng.fan
 * @date 2023/2/10 2:35 PM
 * description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DubboMatchInfo {

    private String service;

    @Builder.Default
    private Set<String> providers = Sets.newHashSet();

    @Builder.Default
    private Set<String> consumers = Sets.newHashSet();
}
