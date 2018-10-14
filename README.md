# AndFix
结合阿里推出的AndFix定点热修复，采用指定类名和方法名进行修复，在不更新产品版本的情况下，实现app内容的更新和bug的修复。简单应用：为实现不同时间显示不同的欢迎界面，或者实时更新不同的界面功能。

本项目主要根据阿里实现的AndFix技术实现热修复和热更新，在上一个版本出现漏洞和bug或者需要更新部分功能时，下一个版本还没发布，实现代码的热修复和热替换，避免频繁的版本更新和增加容错力。

每个方法都封装了需要参数，根据方法名结合自身需要传输的参数即可了解需要使用的方法，只需要一行代码进行调用和实现，重写逻辑处理代码即可。

# 最新版本

版本号：[![](https://www.jitpack.io/v/YouAreOnlyOne/AndFix.svg)](https://www.jitpack.io/#YouAreOnlyOne/AndFix)

使用自行替换下面的版本号，以获得最新版本。

# 使用体验

1、首先下载app，并安装，下载地址：https://github.com/YouAreOnlyOne/AndFix/blob/master/source/AndFix.apk 。安装之后，可以点击“计算”按钮，或者点击“修复”之后再点击“计算”，发现程序都会直接崩溃掉。

2、下载修复文件，并放到手机存储的根目录下面，下载地址：https://github.com/YouAreOnlyOne/AndFix/blob/master/source/out.dex 。此时，重新进入app，先点击“修复”，之后，点击“计算”，发现程序一切正常。实现了热修复。

# 使用方法

这里分别介绍在不同项目环境中，如何引用对该库的依赖。

## Android中使用：

方法一：

1.第一步，在项目的build.gradle下配置，注意是项目的build.gradle：

     allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
    
    
2.第二步,在app的build.gradle下添加如下依赖：

    dependencies {
            ...
            implementation 'com.github.YouAreOnlyOne:AndFix:版本号'
            ...
     }
    
    
方法二：
    
 1.第一步，下载依赖的包：https://github.com/YouAreOnlyOne/AndFix/blob/master/source/andfix-release.aar 。并放到项目的 libs 目录下面。
    
 2.第二步,在app的build.gradle下添加如下依赖，注意，两个依赖是平级关系：
    
    repositories {
       flatDir {
           dirs 'libs'
         }
    }
    
    dependencies {
            ...
            compile(name:'andfix-release', ext:'aar')
            ...
    }
 

	
# 使用示例：

1、在项目开发中，某一个类写了如下代码：

	public class Caculator {
    		public int caculate(){
    	  		int i=0,j=100;
      			return j/i;
  		}
	}

此时，项目已经上线，版本已经发布，但是又不能立即发布下一个版本。

2、在项目中新建一个包，重写该类中的方法，如下代码：

	public class Caculator {

   	 	@Replace(clazz = "上面的发生错误的类所在的包名.Caculator",method = "caculate(方法名)")
   	 	public int caculate(){
       			int i=10,j=100;
       			return j/i;
    		}
	}
3、重新编译一次项目，在类似下面的目录中找到需要修复的class文件：F:\AndroidStudioProject\AndFix\andfix\build\intermediates\javac\debug\compileDebugJavaWithJavac\classes\……。使用命令或者编译工具，把需要修复的class文件转换为dex文件。可以把该文件放置到服务器或者其他地方。

4、其实，这一步和第3步应该是在前面发布的版本中完成。只有这一步提前做了，也就是上一个版本能够检测到服务端发布的dex修复文件，在app中才能自动修复。这一步主要内容为：在Android中的Activity中，书写对应的dex文件下载方法，如检测服务端是否发布了dex文件，如果检测到就下载到手机的指定目录文件夹。在需要修复的逻辑代码块中，使用下面的代码进行修复：

	DxManager dxManager=new DxManager(this);
	//方法的参数为文件流，如下方式调用：
        dxManager.loadDex(new File(Environment.getExternalStorageDirectory(),"out.dex"));
	
	
不会写文件下载的，可以参考一下下面的文章：

OkHttp 、Retrofit 、Volley 、RxJava、Novate在Android中Web网络请求一行代码解决。

https://blog.csdn.net/u014374009/article/details/82933127

一行代码实现Ftp文件上传、文件下载、文件删除和进度监听的工具类的使用。

https://blog.csdn.net/u014374009/article/details/82944107

 
 
# 项目用到的权限

  在manifest文件中添加访问的权限：
 

    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

   

 
# 使用指南
 
 更新中……
 
# 相关介绍

一行代码解决AndFix热修复和热跟更新问题，集成了阿里的开源库，修复程序的缺陷bug漏洞和功能页面等.

https://blog.csdn.net/u014374009/article/details/83052178




# 其它信息

1.项目还有很多不完善的地方，欢迎大家指导。

2.项目持续更新开源，有兴趣加入项目或者跟随项目的伙伴，可以邮件联系！ 

3.关注或者喜欢或者尝试使用或者感兴趣的伙伴可以，点击 ~ star ~ 。

# 作者邮箱

ycj52011@outlook.com


