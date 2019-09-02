# 1.线程专业术语  
    
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
    
# 2.非线程安全仅针对实例变量，对方法内部的变量不做比较，因为方法内部的变量是私有的。
    
    静态方法锁或者同步代码块指针对类，不针对实例变量，实例变量的锁和类的锁不是同一把锁
    简单来说，只要针对的不是同一实例变量，且加锁的对象不相同，程序就是异步运行，仅针对同步方法和同步代码块。
# 3.volatile关键字的作用是强制从公共堆栈中获取变量的值，而不是从线程私有数据栈中获取变量的值。
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
