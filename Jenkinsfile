pipeline {
    agent any

    tools {
        maven 'Maven 3'
        jdk 'JDK 17'
    }

    environment {
            GITHUB_URL = 'https://github.com/VladimirRadan/SeleniumAutomationFramework.git'
            BRANCH = 'main'
            CREDENTIALS_ID = 'github-credentials' // ID koji ćete postaviti u Jenkins-u
        }

    stages {
        stage('Checkout') {
                    steps {

                        git branch: "${BRANCH}",
                            url: "${GITHUB_URL}",
                            credentialsId: "${CREDENTIALS_ID}"
                    }
                }

        stage('Clean') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'mvn install -DskipTests=true'
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    try {
                        sh 'mvn test'
                    } catch (Exception e) {
                        currentBuild.result = 'FAILURE'
                        throw e
                    }
                }
            }
        }
    }

    post {
        always {
            // Publikuj TestNG rezultate
            step([$class: 'TestNGResultArchiver', reportFilenamePattern: '**/testng-results.xml'])

            // Sačuvaj test reportove
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/surefire-reports',
                reportFiles: 'index.html',
                reportName: 'TestNG Report'
            ])

            // Očisti workspace
            cleanWs()
        }

        success {
            echo 'Testovi su uspešno izvršeni!'
        }

        failure {
            echo 'Testovi nisu prošli!'

            // Opciono: Dodaj email notifikaciju
            /*
            emailext (
                subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                body: "Something is wrong with ${env.BUILD_URL}",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']]
            )
            */
        }
    }
}