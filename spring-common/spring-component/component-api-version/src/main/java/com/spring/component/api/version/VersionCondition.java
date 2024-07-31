package com.spring.component.api.version;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.mvc.condition.RequestCondition;


public class VersionCondition implements RequestCondition<VersionCondition> {

    private String version;

    public VersionCondition(String version) {
        if (!VersionContent.isVersionWellFormed(version)) {
            throw new RuntimeException(
                "version string " + version + " not matches pattern " + VersionContent.patternStr);
        }
        this.version = version;
    }

    @NonNull
    @Override
    public VersionCondition combine(@NonNull VersionCondition versionCondition) {
        return new VersionCondition(versionCondition.version);
    }

    @NonNull
    @Override
    public VersionCondition getMatchingCondition(@NonNull HttpServletRequest httpServletRequest) {
        String version = httpServletRequest.getHeader(VersionContent.VERSION_HEADER_KEY);
        if (StringUtils.isBlank(version) || !VersionContent.isVersionWellFormed(version)) {
            return null;
        }
        if (VersionContent.calculate(version) >= VersionContent.calculate(this.version)) {
            return this;
        }
        return null;
    }

    @NonNull
    @Override
    public int compareTo(@NonNull VersionCondition other, @NonNull HttpServletRequest httpServletRequest) {
        return VersionContent.calculate(other.version) - VersionContent.calculate(this.version);
    }
}
