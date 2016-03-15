package com.os.operando.gradle.twitter

class TwitterPluginExtension {
    String message = "＼(^o^)／"
    String oauthConsumerKey
    String oauthConsumerSecret
    String oauthAccessToken
    String oauthAccessTokenSecret
    List<Object> dependsOnTasks

    void dependsOnTasks(Object... paths) {
        this.dependsOnTasks = Arrays.asList(paths)
    }
}
