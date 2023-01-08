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
            mail to: "zauchka007@gmail.com",
            subject: "Test Email",
            body: "Test"
        }
    }
}