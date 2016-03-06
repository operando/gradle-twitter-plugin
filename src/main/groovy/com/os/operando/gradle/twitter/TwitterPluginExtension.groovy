package com.os.operando.gradle.twitter

class TwitterPluginExtension {
    def String message = "＼(^o^)／"
    def String oauthConsumerKey
    def String oauthConsumerSecret
    def String oauthAccessToken
    def String oauthAccessTokenSecret
    def List<Object> dependsOnTasks

    void dependsOnTasks(Object... paths) {
        this.dependsOnTasks = Arrays.asList(paths)
    }
}
