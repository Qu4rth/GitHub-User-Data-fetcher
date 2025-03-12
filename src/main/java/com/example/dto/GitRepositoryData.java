package com.example.dto;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GitRepositoryData {
    @JsonProperty
    private String ownerLogin;
    @JsonProperty
    private String repositoryName;
    @JsonProperty
    private ArrayList<GitBranchData> branches;

    public GitRepositoryData(String ownerLogin, String repositoryName, ArrayList<GitBranchData> branches) {
        this.ownerLogin = ownerLogin;
        this.repositoryName = repositoryName;
        this.branches = branches;
    }

    public ArrayList<GitBranchData> getBranches() {
        return branches;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setBranches(ArrayList<GitBranchData> branches) {
        this.branches = branches;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    @Override
    public String toString() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("\"repositoryName\": \"").append(this.getRepositoryName()).append("\",\n");
        jsonBuilder.append("\"ownerLogin\": \"").append(this.getOwnerLogin()).append("\",\n");
        jsonBuilder.append("\"branches\": [");
        if(branches != null && !branches.isEmpty()) {
            ArrayList<String> branchesString = new ArrayList<>();
            for(GitBranchData branch : branches) {
                String branchStringBuilder = "\n{\n" +
                        "\"branchName\":" + branch.getName() + "\",\n" +
                        "\"lastCommitSha\": \"" + branch.getLastCommitSha() + "\"\n" +
                        "}";
                branchesString.add(branchStringBuilder);
            }
            jsonBuilder.append(String.join(",", branchesString));
            jsonBuilder.append("\n");
            jsonBuilder.append("]");
        } else {
            jsonBuilder.append("]");
        }


        return jsonBuilder.toString();
    }
}
