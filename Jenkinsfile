def code

pipeline {
    agent any

    stages {
        stage('Load') {
            code = load 'tests.groovy'                       
        }
        stage('Run') {
            code.bashtest()
        }
    }
}