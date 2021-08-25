# Blocking queue

Write an implementation of a blocking queue where you have some fixed size capacity of elements.

The blocking queue is the queue that implies the thread which wants to put a new element to wait if the queue is already full.
The thread which wants to take an element should also wait if the queue is empty and there is no available element.

Implement the methods in `BlockingQueue` class using `wait()` and `notify()` methods.
The first thing is to think about the conditions under which you want to wait in your methods. 
You can check your solution running `main()` method.

Note that you need to put the conditions that you are checking in a `while` loop, rather than in an `if` statement, 
due to a problem known as spurious wake-ups. A waiting thread can sometimes be re-activated without `notify()` being called. 
Putting the check in a `while` loop will ensure that if a spurious wake-up occurs, the condition will be re-checked, 
and the thread will call `wait()` again.
