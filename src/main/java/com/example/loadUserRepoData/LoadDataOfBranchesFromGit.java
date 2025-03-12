package com.example.loadUserRepoData;

import com.example.dto.GitBranchData;
import com.example.gitConnections.GitHubFetcher;
import com.example.gitConnections.dto.BranchData;
import com.example.gitConnections.dto.CommitData;

import java.util.ArrayList;

public class LoadDataOfBranchesFromGit {
    protected static ArrayList<GitBranchData> loadDataOfBranchesForRepo(String login, String repo, GitHubFetcher fetcher){
        ArrayList<GitBranchData> branchesOfRepo = new ArrayList<>();
        for(BranchData branch : fetcher.fetchRepoBranches(login,repo)){
            CommitData commit = fetcher.fetchNewestCommit(login,repo,branch.getName());
            branchesOfRepo.add(new GitBranchData(branch.getName(),commit.getSha()));
        }
        return branchesOfRepo;
    }
}
