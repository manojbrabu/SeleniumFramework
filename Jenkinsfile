pipeline {
    agent any

    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    // This dynamically injects Maven into the execution path for all steps
    environment {
        PATH = "C:\\apache-maven-3.9.16\\bin;${env.PATH}"
    }


    parameters {
        choice(
            name: 'EXECUTION',
            choices: ['remote', 'local'],
            description: 'Run tests on Selenium Grid or local Jenkins agent browser.'
        )
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox'],
            description: 'Browser used by BaseTest.'
        )
        string(
            name: 'APP_URL',
            defaultValue: 'https://opensource-demo.orangehrmlive.com/',
            description: 'Application URL under test.'
        )
        string(
            name: 'SELENIUM_GRID_URL',
            defaultValue: 'http://REMOTE_MACHINE_IP:4444',
            description: 'Selenium Grid URL. Required when EXECUTION=remote.'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Environment Check') {
            steps {
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Run Tests') {
            steps {
                withEnv([
                    "EXECUTION=${params.EXECUTION}",
                    "BROWSER=${params.BROWSER}",
                    "APP_URL=${params.APP_URL}",
                    "SELENIUM_GRID_URL=${params.SELENIUM_GRID_URL}"
                ]) {
                    bat 'mvn clean test'
                }
            }
        }
    }

    post {
        always {
            junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
            archiveArtifacts allowEmptyArchive: true, artifacts: 'Report/**/*.html, Extend_20Report/screenshots/**/*.png, target/surefire-reports/**/*'
        }
    }
}
