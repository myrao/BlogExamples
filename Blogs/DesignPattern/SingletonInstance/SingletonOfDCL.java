/**
 * 单例模式之Double Check Lock模式
 */
public class SingletonOfDCL {
	
	private static SingletonOfDCL sInstance;

	private SingletonOfDCL(){
		// do nothing...
	}

	public static SingletonOfDCL getInstance() {
		if (sInstance == null) {
			synchronized (SingletonOfDCL.class) {
				if (sInstance == null) {
					sInstance = new SingletonOfDCL();
				}
			}
		}
		return sInstance;
	}
}