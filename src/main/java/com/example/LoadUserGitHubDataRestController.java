package com.example;

import com.example.dto.GitRepositoryData;
import com.example.gitConnections.GitHubFetcher;
import com.example.loadUserRepoData.LoadUserRepoData;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.annotations.Stream;

@Path("/")
public class LoadUserGitHubDataRestController {
    @Inject
    GitHubFetcher fetcher;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/loadUserRepositoriesFromGit")
    @Stream(Stream.MODE.RAW)
    public Multi<GitRepositoryData> loadUserDataOfRepositories(@QueryParam("login") String login) {
        return LoadUserRepoData.loadUserRepoData(login,fetcher);
    }
}
