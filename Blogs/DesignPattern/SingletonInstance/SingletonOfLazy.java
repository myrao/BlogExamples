/**
* 单例模式之懒汉模式
*/
publc class SingletonOfLazy {

	private static SingletonOfLazy sInstance;
	
	private SingletonOfLazy(){
		// do nothing...
	}
  
  	// 仅能靠getInstance()获取实例
	public static synchronized SingletonOfLazy getInstance(){
		// 需要用到的时候再初始化
		if (sInstance == null) {
			sInstance = new SingletonOfLazy();
		}
		return sInstance;
	}
}