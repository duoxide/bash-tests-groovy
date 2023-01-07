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
}