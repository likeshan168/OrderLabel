Usage:
        Generate a working app into app.dir (target/app by default):
        
            mvn clean package
            (You can now launch the app with java -jar fxlauncher.jar from target/app)
        
        Deploy the application artifacts via scp:
        
            mvn exec:exec@deploy-app
            相当于把target/app 下所有的文件拷贝到服务器上
            (Auto update is now available. You can also run java -jar fxlauncher.jar)
        
        Build a native installer for the platform you are on (only needed once)
            mvn install
            下面就是本地运行的文件，会去检查刚才拷贝到服务器的更新
            (Look in target/installer/bundles for your native installer)