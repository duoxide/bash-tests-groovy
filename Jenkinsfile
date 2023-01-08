def code

pipeline {
    agent any

    stages {
        stage('Load') {
            steps {
                script {
                    code = load 'tests.groovy'
                }
            }
        }
        stage('Run') {
            steps {
                script {
                    code.bashtest()
                }
            }
        }
    }
    post {
        always{
            archiveArtifacts artifacts: '/var/jenkins_home/workspace/bash-tests_master/output.csv', onlyIfSuccessful: true
            emailext to: "zauchka007@gmail.com",
            subject: "jenkins build:${currentBuild.currentResult}: ${env.JOB_NAME}",
            body: "${currentBuild.currentResult}: Job ${env.JOB_NAME}\nMore Info can be found here: ${env.BUILD_URL}",

            attachmentsPattern: '/var/jenkins_home/workspace/bash-tests_master/output.csv'
        }
    }
}