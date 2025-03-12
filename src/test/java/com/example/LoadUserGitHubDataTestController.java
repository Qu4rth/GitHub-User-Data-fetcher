package com.example;


import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;


@QuarkusTest
public class LoadUserGitHubDataTestController {

    @Test
    public void testGitHubHappyPath(){
        given()
                .queryParam("login","octocat")
                .when().get("/loadUserRepositoriesFromGit")
                .then()
                .statusCode(200)
                .body("[0].ownerLogin",equalTo("octocat"))
                .body("[0].repositoryName",equalTo("git-consortium"))
                .body("[0].branches",hasSize(1))
                .body("[0].branches",hasItem(hasKey("lastCommitSha")))
                .body("[0].branches",hasItem(hasKey("name")));
        given()
                .queryParam("login","")
                .when().get("/loadUserRepositoriesFromGit")
                .then()
                .statusCode(404);
    }

}
