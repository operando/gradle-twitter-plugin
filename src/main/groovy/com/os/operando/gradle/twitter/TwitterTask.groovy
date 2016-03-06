package com.os.operando.gradle.twitter

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class TwitterTask extends DefaultTask {

//    @Input
//    String hogehoge = 'hogehoge'

    TwitterTask() {
//        description = 'Hogehoge task.'
//        group = 'Help'
    }

    @TaskAction
    def todo() {
//        println "hoge2"
//        println hogehoge()
        println "todo"
    }

//    String hogehoge() { System.properties.get('hogehoge', hogehoge) }
}