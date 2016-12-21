package github;

import com.google.common.collect.ImmutableBiMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("6730683358c0d58149cf45e9619e71fd29711a19");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("VMaria", "Autotests_Training")).commits();
        for (RepoCommit commit: commits.iterate(new ImmutableBiMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }



}
