    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    git branch: 'develop', url: 'https://github.com/jhbafaws/api-user.git'
                }
            }

            stage('Build') {
                steps {
                    sh 'mvn clean install'
                }
            }

            stage('Test') {
                steps {
                    sh 'mvn test'
                }
            }

            stage('SonarQube Analysis') {
                steps {
                    withCredentials([string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')]) {
                        sh '''
                        mvn sonar:sonar \
                          -Dsonar.login=$SONAR_TOKEN \
                          -Dsonar.projectKey=api-user \
                          -Dsonar.host.url=http://localhost:9000
                        '''
                    }
                }
            }

            stage('Package') {
                steps {
                    sh 'mvn package'
                }
            }

            // Nuevo Stage para el Despliegue
            stage('Deploy to AWS EC2') {
                steps {
                    withCredentials([sshUserPrivateKey(credentialsId: 'ec2-ssh-key', keyFileVariable: 'SSH_KEY')]) {
                        script {
                            // Copiar el archivo JAR a la instancia EC2
                            sh '''
                            scp -i $SSH_KEY target/api-user-0.0.1-SNAPSHOT.jar ec2-user@ec2-3-136-37-113.us-east-2.compute.amazonaws.com:/home/ec2-user/
                            '''

                            // Conectarse por SSH y levantar la aplicación
                            sh '''
                            ssh -i $SSH_KEY ec2-user@ec2-3-136-37-113.us-east-2.compute.amazonaws.com "nohup java -jar /home/ec2-user/api-user-0.0.1-SNAPSHOT.jar > /dev/null 2>&1 &"
                            '''
                        }
                    }
                }
            }
        }

        post {
            always {
                archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
            }
            success {
                echo 'Despliegue completado con éxito'
            }
            failure {
                echo 'El despliegue ha fallado'
            }
        }
    }
