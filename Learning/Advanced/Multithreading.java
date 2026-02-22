package Learning.Advanced;

/**
 * Multithreading in Java
 * Threads: Lightweight processes that can run concurrently
 * Methods: Extend Thread or Implement Runnable
 * 
 * KEY INFO:
 * Creating Threads:
 * 1. Extend Thread: class MyThread extends Thread
 * 2. Implement Runnable: class MyTask implements Runnable (preferred)
 * 3. Lambda: new Thread(() -> { code }).start();
 * 
 * Thread Methods: start(), run(), sleep(), join(), interrupt()
 * Synchronization: synchronized keyword prevents race conditions
 * Deadlock: Two threads waiting for each other's locks
 * Thread Pool: ExecutorService - reuse threads efficiently
 */

public class Multithreading {
    
    public static void main(String[] args) {
        System.out.println("=== Single-threaded Execution ===\n");
        executeSequential();
        
        System.out.println("\n=== Multi-threaded Execution ===\n");
        executeParallel();
        
        System.out.println("\n=== Thread Priority ===\n");
        threadPriority();
        
        System.out.println("\n=== Synchronized Methods ===\n");
        synchronizedExample();
    }
    
    // Method 1: Extend Thread class
    static class PrintThread extends Thread {
        private String name;
        
        public PrintThread(String name) {
            this.name = name;
        }
        
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println(name + " - Count: " + i + 
                    " (Thread: " + Thread.currentThread().getName() + ")");
                try {
                    Thread.sleep(500); // Sleep for 500ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // Method 2: Implement Runnable interface
    static class RunnableTask implements Runnable {
        private String taskName;
        
        public RunnableTask(String taskName) {
            this.taskName = taskName;
        }
        
        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Task: " + taskName + ", Step: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // Synchronized Counter
    static class Counter {
        private int count = 0;
        
        public synchronized void increment() {
            count++;
        }
        
        public synchronized int getCount() {
            return count;
        }
        
        public synchronized void reset() {
            count = 0;
        }
    }
    
    // Thread-safe Counter using Lock
    static class ThreadSafeCounter {
        private int count = 0;
        
        public synchronized void increment() {
            count++;
        }
        
        public synchronized void incrementByValue(int value) {
            for (int i = 0; i < value; i++) {
                count++;
                try {
                    Thread.sleep(1); // Simulate work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
        public synchronized int getCount() {
            return count;
        }
    }
    
    public static void executeSequential() {
        long startTime = System.currentTimeMillis();
        
        // Sequential execution
        PrintThread thread1 = new PrintThread("Task-1");
        PrintThread thread2 = new PrintThread("Task-2");
        PrintThread thread3 = new PrintThread("Task-3");
        
        thread1.start();
        try {
            thread1.join(); // Wait for thread1 to complete
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        thread2.start();
        try {
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        thread3.start();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Sequential execution took: " + (endTime - startTime) + "ms");
    }
    
    public static void executeParallel() {
        long startTime = System.currentTimeMillis();
        
        // Parallel execution (concurrent)
        PrintThread thread1 = new PrintThread("Task-1");
        PrintThread thread2 = new PrintThread("Task-2");
        PrintThread thread3 = new PrintThread("Task-3");
        
        thread1.start();
        thread2.start();
        thread3.start();
        
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Parallel execution took: " + (endTime - startTime) + "ms");
    }
    
    public static void threadPriority() {
        Thread thread1 = new Thread(new RunnableTask("High Priority"), "T1");
        Thread thread2 = new Thread(new RunnableTask("Normal Priority"), "T2");
        Thread thread3 = new Thread(new RunnableTask("Low Priority"), "T3");
        
        thread1.setPriority(Thread.MAX_PRIORITY); // 10
        thread2.setPriority(Thread.NORM_PRIORITY); // 5
        thread3.setPriority(Thread.MIN_PRIORITY); // 1
        
        System.out.println("Thread priorities: T1=" + thread1.getPriority() + 
                         ", T2=" + thread2.getPriority() + 
                         ", T3=" + thread3.getPriority());
        
        thread1.start();
        thread2.start();
        thread3.start();
    }
    
    public static void synchronizedExample() {
        ThreadSafeCounter counter = new ThreadSafeCounter();
        
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 starting");
            counter.incrementByValue(5);
            System.out.println("Thread 1 done. Count: " + counter.getCount());
        });
        
        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2 starting");
            counter.incrementByValue(5);
            System.out.println("Thread 2 done. Count: " + counter.getCount());
        });
        
        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Final count: " + counter.getCount() + " (Expected: 10)");
    }
}

