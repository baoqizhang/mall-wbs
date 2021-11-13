node {
    stage('Checkout') {
        echo "1.Checkout Stage"
        checkout scm
        script {
            build_tag = sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
            project_name = 'mall-wbs'
            registry_url = 'registry.cn-hangzhou.aliyuncs.com'
            registry_ns = 'budbreakjavademo/mall-wbs'
        }
        echo "build tag : ${build_tag}"
    }
    stage('Test') {
        echo "2.Test Stage"
        sh './gradlew clean build'
    }

    stage('Build') {
        echo "3.Build Docker Image Stage"
        sh "docker build -t ${registry_url}/${registry_ns}:${build_tag} ."
    }

    stage('Push') {
        echo "4.Push Docker Image Stage"
        try {
            withCredentials([usernamePassword(credentialsId: 'aliyun-registry-admin', passwordVariable: 'aliRepoPassword', usernameVariable: 'aliRepoUser')]) {
                sh "docker login -u ${aliRepoUser} -p ${aliRepoPassword}  ${registry_url}"
                sh "docker push ${registry_url}/${registry_ns}:${build_tag}"
            }
        } finally {
            echo "Cleanup ..."
            sh "docker rmi ${registry_url}/${registry_ns}:${build_tag} --force"
        }
    }

    stage('Deploy') {
        echo "5. Deploy Stage"
        sh "sed -i 's#{{service}}#${project_name}#g;s#{{namespace}}#${project_name}#g;s#{{IMAGE_URL}}#${registry_url}/${registry_ns}#g;s#{{IMAGE_TAG}}#${build_tag}#g' k8s/deployment.yaml"
        sh "sed -i 's#{{service}}#${project_name}#g;s#{{namespace}}#${project_name}#g' k8s/svc.yaml"

        stage('DeployToDev') {
            withKubeConfig([credentialsId: 'kube_1']) {
                sh "kubectl apply -f k8s/deployment.yaml ."
                sh "kubectl apply -f k8s/svc.yaml ."
            }
        }

        stage('Confirm') {
            input('Do you want to deploy to staging?')
        }

        stage('DeployToStaging') {
        }

        stage('Confirm') {
            input('Do you want to deploy to prod?')
        }

        stage('DeployToProd') {
        }
    }
}
