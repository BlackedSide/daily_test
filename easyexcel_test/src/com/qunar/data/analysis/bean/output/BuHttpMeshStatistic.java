package com.qunar.data.analysis.bean.output;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 7:10 PM
 * description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuHttpMeshStatistic {

    private String owner;

    private String thirdBu;

    private String forthBu;

    @Builder.Default
    private Set<String> dockerJavaApps = Sets.newHashSet();

    @Builder.Default
    private Set<String> dockerDomainJavaApps = Sets.newHashSet();

    @Builder.Default
    private Set<String> p1p2DockerDomainJavaApps = Sets.newHashSet();

    @Builder.Default
    private Set<String> p1p2DockerDomainJavaAppsOver10 = Sets.newHashSet();

    @Builder.Default
    private Set<String> p1p2DockerDomainJavaAppsOver5 = Sets.newHashSet();

    @Builder.Default
    private Set<String> p1p2DockerDomainJavaAppsBetween0to5 = Sets.newHashSet();

    @Builder.Default
    private Set<String> p1p2DockerDomainJavaAppsEqual0 = Sets.newHashSet();

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
                owner, thirdBu, forthBu,
                dockerJavaApps.size(),
                dockerDomainJavaApps.size(),
                p1p2DockerDomainJavaApps.size(),
                p1p2DockerDomainJavaAppsOver10.size(),
                p1p2DockerDomainJavaAppsOver5.size(),
                p1p2DockerDomainJavaAppsBetween0to5.size(),
                p1p2DockerDomainJavaAppsEqual0.size()
        );
    }
}
