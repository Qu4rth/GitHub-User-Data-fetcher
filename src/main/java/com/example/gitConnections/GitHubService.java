package com.example.gitConnections;

import com.example.gitConnections.dto.BranchData;
import com.example.gitConnections.dto.CommitData;
import com.example.gitConnections.dto.RepositoryData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.ArrayList;

@RegisterRestClient(configKey = "github-api")
@ApplicationScoped
public interface GitHubService {

    @GET
    @Path("/users/{login}")
    @Produces(MediaType.APPLICATION_JSON)
    Response checkIfUserExists(@PathParam("login") String login);

    @GET
    @Path("/users/{login}/repos")
    @Produces(MediaType.APPLICATION_JSON)
    ArrayList<RepositoryData> getUserRepositories(@PathParam("login") String login);

    @GET
    @Path("/repos/{login}/{repo}/branches")
    @Produces(MediaType.APPLICATION_JSON)
    ArrayList<BranchData> getBranches(@PathParam("login") String login, @PathParam("repo") String repo);

    @GET
    @Path("/repos/{login}/{repo}/commits")
    @Produces(MediaType.APPLICATION_JSON)
    ArrayList<CommitData> getCommits(@PathParam("login") String login, @PathParam("repo") String repo, @QueryParam("sha") String sha);

}
