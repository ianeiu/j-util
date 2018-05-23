package com.wm.demo.learn.base.d1tod4;

class Fu {
	public int num = 100;

	public void show() {
		System.out.println("show Fu");
	}
	
	public static void function() {
		System.out.println("function Fu");
	}
}

class Zi extends Fu {
	public int num = 1000;
	public int num2 = 200;

	public void show() {
		System.out.println("show Zi");
	}
	
	public void method() {
		System.out.println("method zi");
	}
	
	public static void function() {
		System.out.println("function Zi");
	}
}

class Demo04DuoTai {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		//要有父类引用指向子类对象。
		//父 f =  new 子();
		Fu f = new Zi();
		System.out.println(f.num);
		//找不到符号
		//System.out.println(f.num2);
		
		f.show();
		//找不到符号
		//f.method();
		f.function();
	}
}