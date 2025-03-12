package com.example.gitConnections;

import com.example.gitConnections.dto.BranchData;
import com.example.gitConnections.dto.CommitData;
import com.example.gitConnections.dto.RepositoryData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;

@ApplicationScoped
public class GitHubFetcher {

    @Inject
    @RestClient
    GitHubService gitHubService;


    public ArrayList<RepositoryData> fetchUserRepos(String login){
        return gitHubService.getUserRepositories(login);
    }

    public ArrayList<BranchData> fetchRepoBranches(String login, String repo){
        return gitHubService.getBranches(login, repo);
    }
    public CommitData fetchNewestCommit(String login, String repo, String sha){
        return gitHubService.getCommits(login,repo,sha).getFirst();
    }
    public boolean checkIfUserExists(String login){
        try (Response response = gitHubService.checkIfUserExists(login)) {
            return response.getStatus() == Response.Status.OK.getStatusCode();
        } catch (Exception e) {
            return false;
        }
    }
}
