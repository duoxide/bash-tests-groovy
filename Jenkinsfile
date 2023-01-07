def code

pipeline {
    agent any

    stages {
        stage('Load') {
            steps {
                code = load 'tests.groovy'
            }
        }
        stage('Run') {
            steps {
                code.bashtest()
            }
        }
    }
}