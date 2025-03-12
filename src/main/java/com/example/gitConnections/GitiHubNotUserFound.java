package com.example.gitConnections;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class GitiHubNotUserFound extends WebApplicationException {
    public GitiHubNotUserFound() {
        super(
                Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"status\": 404, \"message\": \"User could not be found\"}")
                        .type(MediaType.APPLICATION_JSON)
                        .build()
        );
    }
}
