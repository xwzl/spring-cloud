# 1. 线程专业术语  
    
    非线程安全：非线程安全主要是指多个线程对同一个对象中的 同一个变量实例变量进行操作是会出现值被更改，值不同步的情况，进而影响程序的执行流程。
    
    在Java中有三种方法可以终止正在运行的线程：
      1.使用退出标志，使线程正常退出，也就是当run方法完成后线程终止。
      2.使用stop方法强行终止线程，但是不推荐使用这个方法，因为Stop和Susped及resume一样，都是过期作废的方法，使用它们可能产生不可预料的结果。
      3.使用interrupt方法中断线程，但是interrupt方法不像for-break的使用效果那么明显，马上就停止循环。调用interrupt方法仅仅是在当前线程中打了一个停止标记，并不是真的停止线程。
    
    yield()方法的作用是放弃当前的CPU资源，将它让给其他的任务去占用CPU执行时间。但是放弃的时间不确定，有可能刚刚放弃，马上又获得CPU时间片。
    非线程安全其实就算是多个线程对同一个对象中的实例变量进行并发访问时发生，产生的后果就是脏读，取到的数据是被改过得数据，而线程安全就是获得是实例变量的值经过同步处理的。
    
    
    某个线程中创建的新线程优先级与是否为守护线程，与创建的这个线程的线程属性一致。
    When code running in some thread creates a new <code>Thread</code> object, the new thread has its priority initially set equal to the priority 
    of the creating thread, and is a daemon thread if and only if the  creating thread is a daemon.
    
# 2. 非线程安全仅针对实例变量，对方法内部的变量不做比较，因为方法内部的变量是私有的。
    
    静态方法锁或者同步代码块指针对类，不针对实例变量，实例变量的锁和类的锁不是同一把锁
    简单来说，只要针对的不是同一实例变量，且加锁的对象不相同，程序就是异步运行，仅针对同步方法和同步代码块。
# 3. volatile关键字的作用是强制从公共堆栈中获取变量的值，而不是从线程私有数据栈中获取变量的值。
    volatile关键字的作用是使变量在多线程间可见。
    
    使用volatile关键字增加了实例变量在多个线程之间的可见性。但是volatile关键字最致命的缺点是不支持原子性:
      1.关键字volatile是线程同步的轻量级实现，所以volatile性能肯定比synchronized要好，并且volatile执行修饰变量，而synchronized
    可以修饰方法。以及代码块。随着JDK新版本的发布，synchronized关键字在执行效率上得到很大提升，在开发中使用synchronized的比率还是
    比较大的。
      2.多线程访问volatile不会发生阻塞，而synchronized会发生阻塞。
      3.volatile保证了数据的可见性，但不保证原子性。而synchronized可以保证原子性，也可以间接保证可见性。因为它会将私有内存和公共内存中
    的数据做同步。
      4.再次重申一次，关键字volatile是解决变量在多个线程之间的可见性。而synchronized关键字解决的是多个线程之间访问资源的同步性。
    线程安全包含原子性和可见性两个方面，Java的同步机制都是围绕这两个方面来确定线程安全的。
    
    如果对于测试的变量值count1,在测试方法中加了synchronized关键字，变量前面就不用加volatile关键字
    关键字volatile主要使用场合是在多个线程中可以感知实例变量被更改了，并且可以获得最新的值使用，也就是多线程读取
    共享变量时可以获取最新值使用。
    关键字volatie提示线程每次从共享内存中读取变量，而不是从私有内存中读取变量，这样就保证了同步数据的可见性。
    但在这里需要注意的是：如果实例变量中的数据，比如i++,也就是i+=1;这样的操作其实并不是一个原子操作，就是非线程安
    全的，表示i++的操作步骤分解如下：
      1.从内存中取出i的值。
      2.计算i的值。
      3.将i的值写到内存中。
    假如在第二步计算过程中，另外一个线程也修了i的值，这个时候就出现了脏读（脏读就是指当一个事务正在访问数据，并且对
    数据进行了修改，而这种修改还没有提交到数据库中，这时，另外一个事务也访问这个数据，然后使用了这个数据。因为这个数
    据是还没有提交的数据，那么另外一个事务读到的这个数据是脏数据，依据脏数据所做的操作可能是不正确的）。解决方法就是
    使用synchronized,所以volatile本身不处理数据的原子性，而是强制对数据的读写及时响应到主机内存中。
    
# 4. 线程池构建对象参数说明

参数 workQueue 值被提交但未执行的任务队列，她是一个 Blocking 接口的对象，仅用于存放 Runnable 对象。根据队列功能分类，在
ThreadPoolExecutor 类的构造函数汇总可使用以下几种 BlockingQueue 接口：

- 直接提交的队列：该功能有 SynchronousQueue 对象提供。SynchronousQueue 是一个特殊的 BlockingQueue.SynchronousQueue 
没有容量，每一个插入操作都要等待一个相应的删除操作，反之，每一个删除操作都要等待对应的插入操作。如果使用 SynchronousQueue ,
则提交的任务不会被真实地保存，而总是将新任务给线程执行，如果没有空闲的进程，则尝试创建新的进程，如果进程数量已经达到最大值，
则执行拒绝策略。因此，使用 SynchronosQueue 队列，通常要设置很大的 maximumPoolSize 值，否则很容易执行拒绝策略。
- 有界的任务队列：有界的任务队列可以使用 ArrayBlockQueue 类实现。ArrayBlockingQueue 类的构造函数必须带有一个容量的参数,
表示该队列的最大容量：public ArrayBlockingQueue(int capacity) 
    - **当使用有界的任务队列时，若有新的任务需要执行，如果线程池的实际线程数小于 corePoolSize,则会有限创建新的线程，若大于 
    corePoolSize ，则会将新任务加入等待队列。若等待队列已满，无法加入，则总线程数大于 maximumPoolSize 的前提下，创建新的
    进程执行任务。若大于 maximumPoolSize ，则执行拒绝策略**。可见，有界队列仅当在任务队列装满时，才可能将线程数提升到 corePoolSize
    以上，换言之，除非系统繁忙，否则要确保核心线程数维持在 corePoolSize.
- 无界的任务队列：无界任务队列可以通过 LinkedBlockingQueue 类实现。与有界队列相比，除非系统资源好近，否则无界的任务队列不存
在任务入队失败的情况。当有新的任务到来，系统的线程数小于 corePoolSize 是，线程池会生成新的线程执行任务，但当系统的线程数达到
corePoolSize 后，就不会继续增加了。若后续仍有新的任务加入，而又没有空闲的线程资源，则任务直接进入队列等待。若任务创建和处理的
速度差异很大，无界队列会保持快速增长，直到耗尽系统内存。
- 优先任务队列：优先任务队列是带有执行优先级的队列。它通过 PriorityBlockingQueue 类实现，可以控制任务的执行先后顺序。它是一个
特殊的无界队列。无论是有界队列 ArrayBlockingQueue 类，还是未指定大小的无界队列 LinkedBlockingQueue 类都是按照先进先出算法处理
任务的。而 PriorityBlockingQueue 类则可以根据任务自身的优先级顺序先后执行，在确保系统性能的同时，也能有很好的质量保证。

回顾 newFixedThreadPool() 方法的实现，它返回了一个 corePoolSize 和 maximumPoolSize 大小一样的，并且使用了 LinkedBlockingQueue
任务队列的线程池。因为对于固定大小的线程池而言，不存在线程数量的动态变化，因此 corePoolSize 和 maximumPoolSize 可以相等。同时，它
使用无界队列存放无法立即执行的任务，当任务提交非常频繁的时候，该队列可能迅速膨胀，从耗尽系统资源。

newSingleThreadExecutor() 方法返回的单线程线程池，是 newFixedThreadPool() 方法的一种退化，只是简单地将线程池数量设置为 1.

newCacheThreadPool()方法返回 corePoolSize 为0，maximumPoolSize  无穷大的线程池，这意味着在没有任务时，该线程池内无线程，而当
任务被提交时，该线程池会使用空闲的线程执行任务，若无空闲线程，则将任务加入 SynchronousQueue 队列，而 SychronousQueue 队列是一种直接
提交的队列，它总会迫使线程池增加新的线程执行任务。当任务执行完毕后，由于 corePoolSize 为 0 ， 因此空闲线程又会在指定时间内被回收。

对于 newCachedThreadPool()方法，如果同时有大量任务被提交，而任务的执行又不那么快时，那么系统便会开启等量的线程处理，这样做可能会耗尽
系统的资源。

# 5. 线程池拒绝策略

- AbortPolicy: 该策略直接抛出异常，阻止系统正常工作。
- CallerRunsPolicy 策略：只要线程池未关闭，该策略直接在调用者线程中，运行当前被丢弃的任务。显然这样做不会真的丢弃任务，但是，任务提价线程
的性能极可能会急剧下降。
- DiscardOldestPolicy 策略：该策略将丢弃最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务。
- DiscardPolicy 策略：该策略默默地丢弃无法处理的任务，不予任何处理。

### 关闭线程池

可以通过调用线程池的shutdown或shutdownNow方法来关闭线程池。

原理都是遍历线程池中的工作线程，然后逐个调用线程的interrupt方法来中断线程，所以无法响应中断的任务可能永远无法终止。

只要调用了这两个关闭方法中的任意一个，isShutdown方法就会返回true。

当所有的任务都已关闭后，才表示线程池关闭成功，这时调用isTerminaed方法会返回true。

至于应该调用哪一种方法来关闭线程池，应该由提交到线程池的任务特性决定，通常调用shutdown方法来关闭线程池，如果任务不一定要执行完，则可以调用shutdownNow方法。

实际项目中线程池需要关闭的情况还是较少的，就像数据库连接池打开之后不会去关闭一样。

线程池中的线程执行任务，Runnable的run方法执行完了，表示任务执行完了，线程也就不再是活动状态了。

### shutdown

public void shutdown()：按过去执行已提交任务的顺序发起一个有序的关闭
之前提交的任务会被执行（包含正在执行的，工作队列中的），但新任务会被拒绝
如果已经关闭，则调用没有任何作用。

### shutdownNow

public List<Runnable> shutdownNow()：尝试停止所有活动的正在执行的任务，停止等待任务的处理，并返回正在等待被执行的任务列表
shutdownNow会强制停止所有正在执行的任务

### 监控线程池

如果在系统中大量使用线程池，则有必要对线程池进行监控，方便在出现问题时，可以根据线程池的使用状况快速定位问题。
可以通过线程池提供的参数进行监控，在监控线程池的时候可以使用以下属性

1. taskCount：线程池需要执行的任务数量，是个近似值。
2. completedTaskCount：线程池在运行过程中已完成的任务数量，小于或等于taskCount，是个近似值。
3. largestPoolSize：线程池里曾经创建过的最大线程数量。通过这个数据可以知道线程池是否曾经满过。如该数值等于线程池的最大大小，则表示线程池曾经满过。
4. poolSize：线程池当前的线程数总量，包括活动的线程与闲置的线程。
5. activeCount：获取活动的线程数。

# 6. 有助于提高锁性能的几点建议

- 减少锁持有的时间：减少锁持有的时间有助于降低锁冲突的可能性，进而提高系统的并发能力。
- 减小锁粒度：所谓减小锁粒度，就是指缩小锁定对象的范围，从而降低锁冲突的可能性，进而提高系统的并发能力。
- 用读写分离锁来替换独占锁：在读多写少的场合使用读写锁可以有效提升系统的并发能力
- 锁分离
- 锁粗化：保证代码逻辑的情况下，如果再次进行减小锁粒度带来系统开销并没有取得相应的效果，就不要进行锁优化。

# 7. Java 虚拟机对锁优化所做的努力

在 JDK 内部也想尽一切办法提供并发时的系统吞吐量

### 7.1 锁偏向

锁偏向是一种针对加锁操作的优化手段。它的核心思想是：如果一个线程获得了锁，那么锁就进入了偏向模式。当这个线程再次请求锁时，
无须再做任何同步操作。这样接节省了大量有关锁申请的操作，从而提高了程序性能。因此，对于几乎没有所竞争的场合，偏向锁有比较好
的优化效果，因为连续多次极有可能是同一个线程请求相同的锁。而对于锁竞争比较激烈的场合，其效果不佳。因为在竞争激烈的场合，最
有可能的情况时每次都是不同的线程请求相同的锁。这样偏向锁模式会失效，因此还不如不启动偏向锁。使用 Java 虚拟机参数 
-XX:+UseBiasedLocking 可以开启偏向锁。

### 7.2 轻量级锁

如果偏向锁失败，那么虚拟机并不会立即挂起线程，它还会使用一种称为轻量级锁的优化手段。轻量级锁的操作也很方便，它只是简单地将对象
头部作为指针指向持有锁的线程堆栈的内部，来判断一个线程是否持有对象锁。如果线程获得轻量级锁成功，则可以顺利进入临界区。如果轻量
级锁加锁失败，则表示其他线程抢先争夺到了锁，那么当期线程的锁请求就会膨胀为重量级锁。

### 7.3 自旋锁

锁膨胀后，为了避免线程真实地在操作系统层面挂起，虚拟机还会做最后的努力 -- 自旋锁。当前线程暂时无法获得锁，而且什么时候可以获得
锁是一个未知数，也许在几个 CPU 四种周期后就可以得到锁。如果这样，简单粗暴地挂起线程可能是一种得不偿失的操作。系统会假设在不久的
将来，线程可以得到这把锁。因此，虚拟机会让当前线程做几个空循环，在经过若干次循环后，如果可以得到锁，那么久顺序进入临界区。如果还
不能获得锁，才会真的将线程在操作系统层面挂起。

### 7.4 锁消除

锁消除是一种更彻底的锁优化。Java 虚拟机在 JIT 编译时，通过对运行上下文的扫描，去除不可能存在共享资源竞争的锁。通过锁消除，可以节
省毫无意义的清秋锁时间。

如果不存在尽在，为什么程序员还要加上锁呢？这是因为在 Java 软件开发过程汇总，我们必然会使用一些JDK的内置API,比如StringBuffer,Vector等。
你在使用这些类的时候，也许根本不会考虑这些对象到底内部是如何实现的。

# 8. 无锁

对于并发控制而言，锁是一种悲观的策略。他总是假设每一次的临界区操作会产生冲突，因此，必须对每次操作小心翼翼。如果有多个线程同时需要访问
临界区资源，则宁可牺牲性能让线程进行等待，所以说锁会阻塞线程执行。而无锁是一种乐观的策略，它会假设会对资源的访问时没有冲突的。既然没有
冲突，自然不需要等待，所以所有的线程都可以在不停顿的状态下持续执行。那遇到冲突怎么办呢？无锁的策略使用一种叫作比较交换（CAS,Compare And 
Swap） 的技术来鉴别线程冲突，一旦检测到冲突产生，就重试当前操作直到没有冲突为止。

### 8.1 与众不同的锁并发策略：比较交换

与锁相比，使用比较交换会使程序看起来更加复杂一些，但由于其非阻塞性，它对死锁问题天生免疫，并且线程间的相互影响远远比基于锁的方式要小。更为
重要的是，使用无锁的方式完全没有锁竞争带来的系统开销，也没有线程间频繁调度带来的开销，因此，它要比基于锁的方式拥有更优越的性能。

CAS 算法的过程是：它包含三个参数 CAS(V,E,N)，其中 V 表示要更新的变量,E 表示预期值，N 表示新值。仅当 V 值等于 E 值时，才会将 V 的值设为 N,
如果 V 值和 E 值不同，说明已经有其他线程做了更新，则当前线程什么都不做。最后，CAS 放回当前 V 的真实值。CAS 操作是抱着乐观的态度进行的，它总是
认为自己可以成功完成操作。当多个线程同时使用 CAS 操作一个变量时，只有一个会胜出，并成功更新，其余均会失败。失败的线程不会被挂起，仅是被告知失败，
并且允许再次尝试，当然也允许失败的线程放弃操作。基于这样的原理，CAS 操作即使没有锁，也可以发现其他线程对当前线程的干扰，并进行恰当的处理。

简单地说，CAS 需要你额外给出一个期望值，也就是你认为这个变量现在应该是什么样子的。如果变量不是你想象的那样，则说明它已经被别人修改过了。你就重新
读取，再次尝试修改就好了。

在硬件层面，大部分的现代处理器都已经支持原子化的 CAS 指令。在 JDK 5 以后，虚拟机便可以使用这个指令来实现并发操作和并发数据结构，并且这种操作
在虚拟机可以说是无处不存的。

### 8.2 无锁的线程安全整数：AtomicInteger

### 8.3 无锁的引用对象：AtomicReference

AtomicReference 和 AtomicInteger 非常类似，不同之处就在于 AtomicInteger 是对整数的封装，而 AtomicReference 则是对应普通的对象引用。
也就是它可以保证你在修改对象引用时的线程安全性。在介绍 AtomicReference 的同时，我希望同时提出一个原子操作的逻辑上的不足。

之前我们说过，线程判断被修改对象是否可以正确写入的条件是对象的当前值和期望值是否一致。这个逻辑从一般意义上来说是正确的。但有可能出现一个小小
的例外，就是当你获得对象数据前后，在准备修改为新值前，对象的值被其他线程连续修改了两次，而经过这两次修改后，对象的值有恢复为旧值。这样，当前
线程就无法正确判断这个对象究竟是否被修改过。

![](http://xuweizhi.gitee.io/image/2019/09/09/CAS%20%E5%AF%B9%E8%B1%A1%E4%BF%AE%E6%94%B9.png)

一般来说，发生这种情况的概率很小，即使发生了，可能也不是什么大问题，比如，我们只是简单地要做一个数值加法，即使在取的期望值后，这个数字不断地被
修改，只要它最终改回了我的期望值，我的加法计算就不会出错。也就是说，当你修改的对象没有过程的状态信息时，所有的信息都只保存于对象的数值本身。

但是，在现实中，还可能存在另外一种场景，就是我们是否能修改对象的值，不仅取决于当前值，还和对象的过程变换有关，这是，AtomicReference 就无能为
力了。

打一个比方，有一家蛋糕店，为了挽留客服，决定为贵宾卡里余额小于 20 元的客服一次性赠送 20 元，刺激客服充值和消费，但条件是每一位客户只能被赠送
一次。

![](http://xuweizhi.gitee.io/image/2019/09/09/AtomicRefrence%20%E5%AF%B9%E8%B1%A1%E4%BB%A5%E5%90%8E%E7%94%A8.png)

### 带有时间戳的对象引用：AtomicStampedReference 

AtomicReference 无法解决上述问题的根本原因是，对象在修改过程汇总丢失了状态信息，对象值本身与状态被画上了等号。因此，我们只能够记录对象
在修改过程中的状态值，就可以很好地解决对象被反复修改导致线程无法正确判断对象状态的对象。

AtomicStampedReference 正是这么做的。它内部不仅维护了对象值,还维护了一个时间戳（我这里把它称为时间戳，实际上它可以使任何一个整数来表示状态值）。
当 AtomicStampedReference 对应的数值被修改时，除了更新数据本身外，还必须要更新时间戳。当 AtomicStampedReference 设置对象值时，对象值及时间戳
都必须满足期望值，写入才会成功。因此，即使对象值反复读写，写返回值，只要时间戳发生变化，就能防止不恰当的写入。

# 9 不变模式

不变模式的主要使用场景需要满足一下两个条件：
- 当对象创建后，其内部状态和数据不再发生任何变化。
- 对象需要被共享，被多线程频繁访问

在 Java 语言中，不变模式的实现很简单。为确保被创建后，不发生任何变化，并保证不变模式正常工作，值需要注意以下几点：
- 去除 setter 方法以及所有修改自身属性的方法
- 将所有属性设置为私有，并用 final 标记，确保其不可修改
- 确保没有子类可以重载修改它的行为
- 有一个可以创建完整对象对象的构造函数。

由于基本数据类型和String类型在实际的软件开发中应用及其广泛，使用不变模式后，所有实例的方法均不需要进行同步操作，保证他们
在多线程环境下的性能。

注意：不变模式通过回避而不是解决问题的态度来处理多线程并发访问控制，不变对象是不需要进行同步操作。由于并发同步会对性能
产生不良的影响，因此，在需求允许的情况下，不变模式可以提高系统的并发性能和并发量。

# 10 生产者-消费模式

生产者-消费者模式是一个经典的多线程设计模式，它为多线程间的协作提供了良好的解决方案。在生产者-消费者模式中，通常有两类
线程，即若干个生产者线程和若干个消费者线程。生产者负责提交用户请求，消费者线程则负责具体处理生产者提交的任务。生产者和
消费者之间通过共享内存缓冲区进行通信。

生产者线程将任务提交到共享内存缓冲区，消费者线程并不直接与生产者线程通信，而是在共享内存缓冲区中获取任务，并进行处理。

注意：生产者-消费者模式中的内存缓冲区的主要功能是数据在多线程间的共享，此外，通过该缓冲区，可以缓解生产者和消费者的性
能差。

生产者-消费者模式的核心组件是共享内存缓冲区，它作为生产者和消费者间的通信桥梁，避免了生产者和消费者直接通信，从而将生产
者和消费者直接通信，从而将生产者和消费者进行解耦。生产者不需要知道消费者的存在，消费者也不需要知道生产者的存在。

同时，由于内存缓冲区的存在，允许生产者和消费者在执行速度上存在时间差，无论是生产者在某一局部时间内的速度高于消费者的速度，
还是消费者在局部时间内的速度高于生产者的速度，都可以通过共享内存缓冲区得到缓解，确保系统正常运行。

### 生产者-消费者的主要角色


角色 | 作用
---|---
生产者 | 用于提交用户请求，提取用户任务，并装入内存缓冲区
消费者 | 在内存缓冲区中提交并出来任务
内存缓冲区 | 换出生产者提交的任务或数据，供消费者使用
任务 | 生产者向内存缓冲区提交的数据结构
Main | 使用生产者和消费者的客户端





