package com.example.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class GitBranchData {
    @JsonProperty
    private String name;
    @JsonProperty
    private String lastCommitSha;

    public GitBranchData(String name, String lastCommitSha) {
        this.name = name;
        this.lastCommitSha = lastCommitSha;
    }

    public String getName() {
        return name;
    }

    public String getLastCommitSha() {
        return lastCommitSha;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastCommitSha(String lastCommitSha) {
        this.lastCommitSha = lastCommitSha;
    }
}
