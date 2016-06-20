#### MVP for Android

第一次接触MVP是通过同事了解的，当时对设计模式还没什么概念，所以业务都写在同一个Activity中，导致每个Activity都很臃肿，并且耦合性超级严重，甚至每个页面都有独立的网络请求的方法，并没有太多的封装。

接着稍微有了些面向对象的思维，学会把工具属性的方法封装在各自的类中，但代码依旧看着难受，总觉得怪怪的。于是了解了一些简单的设计模式，发现原来代码根本不是那样写的。我陷入了沉思，因为缺乏经验和代码量的我，无法找到一条能够踏上设计模式之路的方法。书看了一些，也学着跟别人一样来实现。渐渐觉得设计模式的重要性。

##### MVC (Model - View - Controller)

了解MVP之前得先说说MVC：

- Model : 对业务逻辑的处理
- View : UI层，用于用户交互
- Controller : 如其名为控制器，专门负责M与V之间的媒介

![MVC的流程图]()

不难看出，在Android中Activity就是View和Controller的典型代表，引用大神张鸿阳的[博文](http://blog.csdn.net/lmj623565791/article/details/46596109)来解释吧：

> 看起来的确像那么回事，但是细细的想想这个View对应于布局文件，其实能做的事情特别少，实际上关于该布局文件中的数据绑定的操作，事件处理的代码都在Activity中，造成了Activity既像View又像Controller（当然了Data-Binder的出现，可能会让View更像View吧）。
>
> > Most of the modern Android applications just use View-Model architecture，everything is connected with Activity.

。。。。。

。。。。。

待编辑

。。。。。

。。。。。

MVC不在此做更多的说明了，请大家自行查阅资料吧，接下来说说重点的MVP。

##### MVP (Model - View - Presenter)

刚开始接触Android开发的我那时常常会把所有的业务逻辑写在同一个Activity或者Fragment中，导致一个重量级的Activity上了3000+行代码！现在看到那坨翔一样的代码实在无力吐槽，想要重构却因为涉及到项目的业务核心不能随意乱搞，没有经验的我欲罢不能啊。不然出了Bug自己还得背锅，于是重构就只能一天天慢慢的进行。所以突然有一天我决定不能再这样写代码了，胡乱的乱塞业务只会导致以后一个小问题需要花费更多时间来进行修改和调试，在看了一些MVP的Demo后我就仿着写了起来，因为只是我自己的揣测和推敲，理解不一定正确，还正在学习中，在此如果发现任何问题欢迎大家纠正～

参考的博客有：

- [**androidmvp**](https://github.com/antoniolg/androidmvp)
- [**AndroidMVPSample**](https://github.com/WuXiaolong/AndroidMVPSample)

本次我演示的是一个短信验证码发送和认证的Demo，业务流程如下：

![验证码发送及验证业务流程图]()

说说我对此Demo应用MVP的理解：

- Model ：验证码发送、验证
- View ：显示当前的UI、验证提示
- Presenter ：View的事件传递给Model层的中间人

举个例子：

我感觉和说媒一样，大(hei)美(mu)女(er)**View**一发话，想娶我就拿礼金100万，媒婆**Presenter**把美女的要求传话给屌丝男**Model**，屌丝男为了娶美女当然得想办法凑钱啊，所以赶快凑钱去（网络请求/数据处理等），完事了以后通知媒婆我已经把钱凑齐了，请帮我把钱给美女。于是媒婆收了小费以后把钱给美女，美女笑得嘿～嘿～嘿～

也不知道我的理解对不对，接下来上代码：

先看项目目录结构：

![目录结构图]()

我定义了5个文件夹为基本文件夹：

- model : 存放处理业务逻辑的文件
- iview : 存放处理activity的UI交互的文件，如progress的loading状态，手机号、验证码的输入提示等
- presenter : 一切业务传递的Presenter文件都在于此
- listener : 各类事务的监听，如发送成功/失败，验证码验证成功/失败的监听等
- activity : 其实我想把activity放在view文件夹中的，但感觉还是单独放比较清晰





​	