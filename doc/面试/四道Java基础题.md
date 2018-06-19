一、==符的使用

首先看一段比较有意思的代码

	Integer a = 1000,b=1000;
	Integer c = 100,d=100;    
	public void mRun(final String name){
        new Runnable() {
            
            public void run() {
                System.out.println(name);
                System.out.println(a==b);
				System.out.println(c==d);
            }
        };
    }

 运行代码，我们会得到 false  true。我们知道==比较的是两个对象的引用，这里的abcd都是新建出来的对象，按理说都应该输入false才对。
 这就是这道题的有趣之处，无论是面试题还是论坛讨论区，这道题的出场率都很高。原理其实很简单，我们去看下Integer.java这个类就了然了。

  	public static Integer valueOf(int i) {
        return  i >= 128 || i < -128 ? new Integer(i) : SMALL_VALUES[i + 128];
    }

    /**
     * A cache of instances used by {@link Integer#valueOf(int)} and auto-boxing
     */
    private static final Integer[] SMALL_VALUES = new Integer[256];

    static {
        for (int i = -128; i < 128; i++) {
            SMALL_VALUES[i + 128] = new Integer(i);
        }
    }

当我们声明一个Integer c = 100;的时候。此时会进行自动装箱操作，简单点说，也就是把基本数据类型转换成Integer对象，
而转换成Integer对象正是调用的valueOf方法，可以看到，Integer中把-128-127 缓存了下来
。官方解释是小的数字使用的频率比较高，所以为了优化性能，把这之间的数缓存了下来。这就是为什么这道题的答案回事false和ture了。
当声明的Integer对象的值在-128-127之间的时候，引用的是同一个对象，所以结果是true。

二、String

 		String s1 = "abc";
		String s2 = "abc";
		String s3 = new String("abc");
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);

程序的运行结果确实true、false。第二个输出false可以理解。
一些基本类型的变量和对象的引用变量都是在函数的栈内存中分配，而堆内存中则存放new 出来的对象和数组。然而除此之外还有一块区域叫做常量池。
像我们通常想String s1 = "abc"; 这样申明的字符串对象，其值就是存储在常量池中。
当我们创建String s1 = "abc"这样一个对象之后，"abc"就存储到了常量池（也可叫做字符串池）中，当我们创建引用String s2  = "abc" 的时候，
Java底层会优先在常量池中查找是否存在"abc"，如果存在则让s2指向这个值，不会重新创建，如果常量池中没有则创建并添加的池中。这就是为什么答案是true 和false的原因。

三、final关键字

	public void mRun(final String name){
		new Runnable() {
			public void run() {
                try {
                  Thread.sleep(1000);
               } catch (InterruptedException e) {
                 // TODO Auto-generated catch block
                e.printStackTrace();
               }  
               System.out.println(name);
			}
		}.start();
	}

这种代码相信大家写过很多，当内部类访问局部变量的时候，需要在局部变量前加final修饰符，不然编译器就会报错。通常我们也是这么干的。
为什么要加final修饰符？。
首先内部类的生命周期是成员级别的，而局部变量的生命周期实在方法体之类。也就是说会出现这样一种情况，当mRun方法执行，new 的线程运行，新线程里面会睡一秒。
主线程会继续执行，mRun执行完毕，name属性生命周期结束。1秒之后，Syetem.out.printh(name)执行。然而此时name已经寿终正寝，不在内存中了。
Java就是为了杜绝这种错误，严格要求内部类中方位局部变量，必须使用final关键字修饰。
局部变量被final修饰之后，此时会在内存中保有一份局部变得的复制品，当内部类访问的时候其实访问的是这个复制品。这就好像是把局部变量的生命周期变长了。
说到底还是Java工程师提前把这个坑给我们填了，不然不知道又会有多少小伙伴会为了内部类局部变量而发愁了。

四、Integer与int那些事

看下面代码

      Integer a = new Integer(1000);
		int b = 1000;
		Integer c = new Integer(10);
		Integer d = new Integer(10);
		System.out.println(a == b);
		System.out.println(c == d);

这道题是继第一题的后续，如果这道题你能很快速的得出答案，那么恭喜你，==比较符你就算掌握的比较透彻了。

-----------分割线------------
正确答案： true  、false 

按第一题来说Integer不是把-128-127缓存起来了吗？这不是应该是true嘛，但是你仔细看，这里的Integer是我们自己new出来的，并不是用的缓存，所以结果是false。 
现在来看第一个为啥又是true了呢？ 首先这里的值为1000，肯定和我们所知的Integer缓存没有关系。既然和缓存没有关系，a是新new出来的对象，按理说输入应该是false才对。
但是b这里是int类型。当int和Integer进行==比较的时候，Java会把Integer进行自动拆箱，也就是把Integer转成int类型，所以这里进行比较的是int类型的值，所以结果为true。