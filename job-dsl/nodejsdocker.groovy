job('NodeJS Docker example') {
    scm {
        git('https://github.com/Luca1299/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Luca1299')
            node / gitConfigEmail('luca.carne99@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('Luca1299/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
