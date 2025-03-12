package com.example.loadUserRepoData;

import com.example.dto.GitBranchData;
import com.example.dto.GitRepositoryData;
import com.example.gitConnections.GitHubFetcher;
import com.example.gitConnections.dto.RepositoryData;

import java.util.ArrayList;

public class LoadDataOfReposFromGit {
    protected static ArrayList<GitRepositoryData> loadRepositoriesDataOfUser(String login, GitHubFetcher fetcher) {
        ArrayList<GitRepositoryData> repos = new ArrayList<>();
        for(RepositoryData repo : fetcher.fetchUserRepos(login)){
            if(!repo.isFork()){
                ArrayList<GitBranchData> branchesOfRepo = LoadDataOfBranchesFromGit.loadDataOfBranchesForRepo(login,repo.getName(),fetcher);
                repos.add(new GitRepositoryData(repo.getOwner().getLogin(),repo.getName(),branchesOfRepo));
            }
        }
        return repos;
    }
 }
