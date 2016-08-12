public class SingletonOfInnerClass {

	private SingletonOfInnerClass(){}

	public static SingletonOfInnerClass getInstance(){
		return SingletonHolder.sInstance;
	}

	private static class SingletonHolder {
		private static final SingletonOfInnerClass sInstance = new SingletonOfInnerClass();
	}
}