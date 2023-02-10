package com.qunar.data.analysis.bean.output;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author jinpeng.fan
 * @date 2023/2/10 4:17 PM
 * description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuDubboMeshStatistic {

    private String owner;

    private String thirdBu;

    private String forthBu;

    @Builder.Default
    private Set<String> canDubboMeshAppCodes = Sets.newHashSet();

    @Builder.Default
    private Set<String> p1p2Apps = Sets.newHashSet();

    @Builder.Default
    private Set<String> p1p2Over5Apps = Sets.newHashSet();

    @Builder.Default
    private Set<String> p1p2Between0to5Apps = Sets.newHashSet();

    @Builder.Default
    private Set<String> p1p2Equal0Apps = Sets.newHashSet();

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
                owner, thirdBu, forthBu,
                canDubboMeshAppCodes.size(),
                p1p2Apps.size(),
                p1p2Over5Apps.size(),
                p1p2Between0to5Apps.size(),
                p1p2Equal0Apps.size()
        );
    }
}
