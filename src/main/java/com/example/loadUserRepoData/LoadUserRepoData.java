package com.example.loadUserRepoData;

import com.example.gitConnections.GitiHubNotUserFound;
import com.example.gitConnections.GitHubFetcher;
import io.smallrye.mutiny.Multi;
import com.example.dto.GitRepositoryData;

import java.util.ArrayList;

public class LoadUserRepoData {
    public static Multi<GitRepositoryData> loadUserRepoData(String login, GitHubFetcher fetcher) {
        if(fetcher.checkIfUserExists(login)) {
            ArrayList<GitRepositoryData> repositories = LoadDataOfReposFromGit.loadRepositoriesDataOfUser(login, fetcher);
            return Multi.createFrom().items(repositories.stream());
        } else {
            throw new GitiHubNotUserFound();
        }
    }
}
