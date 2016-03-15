package com.os.operando.gradle.twitter

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.execution.TaskExecutionListener
import org.gradle.api.logging.StandardOutputListener
import org.gradle.api.tasks.TaskState
import twitter4j.Status
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken

class TwitterPlugin implements Plugin<Project> {

    TwitterPluginExtension extension
    StringBuilder taskLogBuilder

    @Override
    void apply(Project project) {
        taskLogBuilder = new StringBuilder()
        extension = project.extensions.create("twitter", TwitterPluginExtension)
//        project.task("hogehoge") << {
//            println project.hogehoge.message
//        }
        project.afterEvaluate {
            project.getGradle().getTaskGraph().addTaskExecutionListener(new TaskExecutionListener() {
                @Override
                void beforeExecute(Task task) {
                    task.logging.addStandardOutputListener(new StandardOutputListener() {
                        @Override
                        void onOutput(CharSequence charSequence) {
                            taskLogBuilder.append(charSequence)
                        }
                    })
                }

                @Override
                void afterExecute(Task task, TaskState state) {
                    handleTaskFinished(task, state)
                }
            })
        }
//        project.tasks.create('hoge2', HogehogeTask)
    }

    void handleTaskFinished(Task task, TaskState state) {
        Throwable failure = state.getFailure()
        boolean shouldTweet = failure != null || shouldMonitorTask(task);

        if (shouldTweet) {
            Twitter twitter = new TwitterFactory().getInstance();
            twitter.setOAuthConsumer(extension.oauthConsumerKey, extension.oauthConsumerSecret)
            AccessToken token = new AccessToken(extension.oauthAccessToken, extension.oauthAccessTokenSecret)
            twitter.setOAuthAccessToken(token)
            Status status = twitter.updateStatus(extension.message)
            println "Successfully updated the status to [" + status.getText() + "]."
        }
    }

    boolean shouldMonitorTask(Task task) {
        for (dependentTask in extension.dependsOnTasks) {
            if (task.getName().equals(dependentTask)) {
                return true
            }
        }
        return false
    }
}