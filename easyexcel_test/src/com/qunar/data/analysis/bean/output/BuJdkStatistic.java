package com.qunar.data.analysis.bean.output;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 2:53 PM
 * description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuJdkStatistic {

    private String owner;

    private String thirdBu;

    private String forthBu;

    @Builder.Default
    private Set<String> jdk17Apps = Sets.newHashSet();

    @Builder.Default
    private Set<String> jdk11Apps = Sets.newHashSet();

    @Builder.Default
    private Set<String> jdk1_8Apps = Sets.newHashSet();

    @Builder.Default
    private Set<String> jdk1_7Apps = Sets.newHashSet();

    @Builder.Default
    private Set<String> jdk1_6Apps = Sets.newHashSet();

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
                owner, thirdBu, forthBu,
                jdk17Apps.size(),
                jdk11Apps.size(),
                jdk1_8Apps.size(),
                jdk1_7Apps.size(),
                jdk1_6Apps.size()
        );
    }
}
