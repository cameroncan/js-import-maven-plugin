package org.codehaus.mojo.jsimport;

import java.util.Set;

/**
 * Represents the "shim" configuration (name taken from requirejs) to create a module representation of non-AMD enabled
 * javascript libraries
 */
public class ShimConfig {

    /**
     * The dependencies that this non-AMD library relies on
     */
    private Set<String> dependencies;

    /**
     * Name to export the module as (the name other modules will use to state a dependency on it)
     */
    private String exportName;

    /**
     * The groupId to match this shim to
     */
    private String groupId;

    /**
     * The artifactId to match this shim to
     */
    private String artifactId;

    public Set<String> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Set<String> dependencies) {
        this.dependencies = dependencies;
    }

    public String getExportName() {
        return exportName;
    }

    public void setExportName(String exportName) {
        this.exportName = exportName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
}
