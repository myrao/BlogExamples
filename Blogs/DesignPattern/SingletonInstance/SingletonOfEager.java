/**
* 单例模式之饿汉模式
*/
publc class SingletonOfEager {
  	// 刚开始就进行初始化
	private static SingletonOfEager sInstance = new SingletonOfEager();
	
	private SingletonOfEager(){
		// do nothing...
	}
  
  	// 仅能靠getInstance()获取实例
	public static SingletonOfEager getInstance(){
		return sInstance;
	}
}