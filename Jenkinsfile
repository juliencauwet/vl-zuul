pipeline {
    agent any
    tools{
        maven 'Maven 3.6.0'
    }
    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/juliencauwet/vl-zuul.git'
            }
        }

        stage('Build'){
            steps {
                    sh 'mvn clean install -DskipTests'
            }

        }
        stage('Tests') {
                    steps {
                        sh 'mvn test'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/**/*.xml'
                        }
                        failure {
                            error 'The tests failed'
                        }
                    }
        }

        stage('Deploy'){
            steps {
                sh '''
                    docker-compose down
                    docker-compose up -d
                    docker ps -a
                '''
            }
        }

    }

}
