pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('your-dockerhub-credentials') // Replace with your DockerHub credentials ID
//         DOCKER_USERNAME = 'your-dockerhub-username'
//         DOCKER_PASSWORD = 'your-dockerhub-password'
        DOCKER_REGISTRY = 'kaushal15' // Replace with your DockerHub username
//         K8S_NAMESPACE = 'your-k8s-namespace' // Replace with your Kubernetes namespace
//         KUBECONFIG_CREDENTIALS_ID = 'kubeconfig-credentials-id' // Jenkins credentials ID for kubeconfig
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/HealthCare-Microservices/backend_services.git']])
                }
            }
        }
//         stage('Login to DockerHub') {
//                 steps {
//                     sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
//                 }
//         }
        stage('Build JAR Files') {
            steps {
                script {
                    def services = ['patient-service', 'doctor-service', 'appointment-service']
                    services.each { service ->
                        dir(service) {
                            echo "Building JAR for ${service}"
                            sh 'mvn clean package -DskipTests'
                        }
                    }
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    def services = ['patient-service', 'doctor-service', 'appointment-service']
                    services.each { service ->
                        dir(service) {
                            echo "Building Docker image for ${service}"
                            sh """
                                docker build -t ${DOCKER_REGISTRY}/${service}:latest .
                                docker push ${DOCKER_REGISTRY}/${service}:latest
                            """
                        }
                    }
                }
            }
        }

//         stage('Apply Kubernetes Manifests') {
//             steps {
//                 script {
//                     withCredentials([file(credentialsId: KUBECONFIG_CREDENTIALS_ID, variable: 'KUBECONFIG_FILE')]) {
//                         sh """
//                             export KUBECONFIG=${KUBECONFIG_FILE}
//                             kubectl apply -f k8s-manifests/
//                         """
//                     }
//                 }
//             }
//         }
//
//         stage('Verify Deployment') {
//             steps {
//                 script {
//                     withCredentials([file(credentialsId: KUBECONFIG_CREDENTIALS_ID, variable: 'KUBECONFIG_FILE')]) {
//                         sh """
//                             export KUBECONFIG=${KUBECONFIG_FILE}
//                             kubectl get pods -n ${K8S_NAMESPACE}
//                         """
//                     }
//                 }
//             }
//         }

        stage('Clean Up Docker Images') {
            steps {
                script {
                    sh 'docker image prune -f'
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed. Please check the logs."
        }
    }
}
