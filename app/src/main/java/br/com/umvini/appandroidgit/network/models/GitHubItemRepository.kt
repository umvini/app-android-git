package br.com.umvini.appandroidgit.network.models

import com.google.gson.annotations.SerializedName

data class GitHubItemRepository(
    @SerializedName("total_count")
    val totalCount: Int,

    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,

    @SerializedName("items")
    val items: List<RepositoryItem>
)


data class RepositoryItem (
    @SerializedName("id")
    val id: Int,

    @SerializedName("node_id")
    val nodeID: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("private")
    val private: Boolean,

    @SerializedName("owner")
    val owner: Owner,

    @SerializedName("html_url")
    val htmlURL: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("fork")
    val fork: Boolean,

    @SerializedName("url")
    val url: String,

    @SerializedName("forks_url")
    val forksURL: String,

    @SerializedName("keys_url")
    val keysURL: String,

    @SerializedName("collaborators_url")
    val collaboratorsURL: String,

    @SerializedName("teams_url")
    val teamsURL: String,

    @SerializedName("hooks_url")
    val hooksURL: String,

    @SerializedName("issue_events_url")
    val issueEventsURL: String,

    @SerializedName("events_url")
    val eventsURL: String,

    @SerializedName("assignees_url")
    val assigneesURL: String,

    @SerializedName("branches_url")
    val branchesURL: String,

    @SerializedName("tags_url")
    val tagsURL: String,

    @SerializedName("blobs_url")
    val blobsURL: String,

    @SerializedName("git_tags_url")
    val gitTagsURL: String,

    @SerializedName("git_refs_url")
    val gitRefsURL: String,

    @SerializedName("trees_url")
    val treesURL: String,

    @SerializedName("statuses_url")
    val statusesURL: String,

    @SerializedName("languages_url")
    val languagesURL: String,

    @SerializedName("stargazers_url")
    val stargazersURL: String,

    @SerializedName("contributors_url")
    val contributorsURL: String,

    @SerializedName("subscribers_url")
    val subscribersURL: String,

    @SerializedName("subscription_url")
    val subscriptionURL: String,

    @SerializedName("commits_url")
    val commitsURL: String,

    @SerializedName("git_commits_url")
    val gitCommitsURL: String,

    @SerializedName("comments_url")
    val commentsURL: String,

    @SerializedName("issue_comment_url")
    val issueCommentURL: String,

    @SerializedName("contents_url")
    val contentsURL: String,

    @SerializedName("compare_url")
    val compareURL: String,

    @SerializedName("merges_url")
    val mergesURL: String,

    @SerializedName("archive_url")
    val archiveURL: String,

    @SerializedName("downloads_url")
    val downloadsURL: String,

    @SerializedName("issues_url")
    val issuesURL: String,

    @SerializedName("pulls_url")
    val pullsURL: String,

    @SerializedName("milestones_url")
    val milestonesURL: String,

    @SerializedName("notifications_url")
    val notificationsURL: String,

    @SerializedName("labels_url")
    val labelsURL: String,

    @SerializedName("releases_url")
    val releasesURL: String,

    @SerializedName("deployments_url")
    val deploymentsURL: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("pushed_at")
    val pushedAt: String,

    @SerializedName("git_url")
    val gitURL: String,

    @SerializedName("ssh_url")
    val sshURL: String,

    @SerializedName("clone_url")
    val cloneURL: String,

    @SerializedName("svn_url")
    val svnURL: String,

    @SerializedName("homepage")
    val homepage: String,

    @SerializedName("size")
    val size: Int,

    @SerializedName("stargazers_count")
    val stargazersCount: Int,

    @SerializedName("watchers_count")
    val watchersCount: Int,

    @SerializedName("language")
    val language: String,

    @SerializedName("has_issues")
    val hasIssues: Boolean,

    @SerializedName("has_projects")
    val hasProjects: Boolean,

    @SerializedName("has_downloads")
    val hasDownloads: Boolean,

    @SerializedName("has_wiki")
    val hasWiki: Boolean,

    @SerializedName("has_pages")
    val hasPages: Boolean,

    @SerializedName("forks_count")
    val forksCount: Int,

    @SerializedName("mirror_url")
    val mirrorURL: String? = null,

    @SerializedName("archived")
    val archived: Boolean,

    @SerializedName("disabled")
    val disabled: Boolean,

    @SerializedName("open_issues_count")
    val openIssuesCount: Int,

    @SerializedName("license")
    val license: License,

    @SerializedName("allow_forking")
    val allowForking: Boolean,

    @SerializedName("is_template")
    val isTemplate: Boolean,

    @SerializedName("topics")
    val topics: List<String>,

    @SerializedName("visibility")
    val visibility: String,

    @SerializedName("forks")
    val forks: Int,

    @SerializedName("open_issues")
    val openIssues: Int,

    @SerializedName("watchers")
    val watchers: Int,

    @SerializedName("default_branch")
    val defaultBranch: String,

    @SerializedName("score")
    val score: Int
)


data class License (

    @SerializedName("key")
    val key: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("spdx_id")
    val spdxID: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("node_id")
    val nodeID: String
)


data class Owner (

    @SerializedName("login")
    val login: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("node_id")
    val nodeID: String,

    @SerializedName("avatar_url")
    val avatarURL: String,

    @SerializedName("gravatar_id")
    val gravatarID: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("html_url")
    val htmlURL: String,

    @SerializedName("followers_url")
    val followersURL: String,

    @SerializedName("following_url")
    val followingURL: String,

    @SerializedName("gists_url")
    val gistsURL: String,

    @SerializedName("starred_url")
    val starredURL: String,

    @SerializedName("subscriptions_url")
    val subscriptionsURL: String,

    @SerializedName("organizations_url")
    val organizationsURL: String,

    @SerializedName("repos_url")
    val reposURL: String,

    @SerializedName("events_url")
    val eventsURL: String,

    @SerializedName("received_events_url")
    val receivedEventsURL: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("site_admin")
    val siteAdmin: Boolean
)
